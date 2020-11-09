package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.TwitterHome;
import page_objects.TwitterLogin;
import rest.TwitterRest;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class tweetCrud {
    public ChromeDriver driver;
    public WebDriverWait wait;
    private TwitterRest request;


    @BeforeEach
    private void setup() {
        // I would normally abstract some of this setup outside of a test, but for a simple demonstration this will work.
        driver = new ChromeDriver();
        this.request = new TwitterRest();

        wait = new WebDriverWait(driver, 10);

    }
    @AfterEach
    public void TearDown() {
        driver.quit();
        request.deleteAllTweets();
        System.out.print("heck yeah");
    }

    @Test
    public void postTweet() {
        //use REST to post tweet
        //login to twitter account and verify tweet was posted
        String tweet = "Hello Twitter!";
        request.PostTweet(tweet);
        driver.get("https://twitter.com/login?lang=en");
        TwitterLogin login = new TwitterLogin(driver, wait);
        login.EnterUsername();
        login.EnterPassword();
        TwitterHome HomePage = login.clickLoginButton();
        List<WebElement> TweetList = HomePage.GetTweets();
        String TweetText = HomePage.GetTweetTextByIndex(TweetList, 0);
        assert tweet.equals(TweetText);
    }

    @Test
    @Order(2)
    public void update_twitter_feed() {
        //add "tweet updated" to tweet
        //login to twitter and verify
        String tweet = "Hello Twitter!";
        request.PostTweet(tweet);
        String tweet2 = "Hello Take2!";
        request.PostTweet(tweet2);
        driver.get("https://twitter.com/login?lang=en");
        TwitterLogin login = new TwitterLogin(driver, wait);
        login.EnterUsername();
        login.EnterPassword();
        TwitterHome HomePage = login.clickLoginButton();
        List<WebElement> TweetList = HomePage.GetTweets();
        assert  TweetList.size() == 2;
    }


}
