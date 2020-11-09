package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class TwitterHome {
    private static final By HOME_PAGE = cssSelector("[aria-label=\"Timeline: Your Home Timeline\"]");
    private static final By TWEET= cssSelector("[role=\"article\"]");
    ChromeDriver driver;
    WebDriverWait wait;

    public TwitterHome(ChromeDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        WaitForHomePage();
    }

    private void WaitForHomePage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(HOME_PAGE));

    }
 // method that gets list of tweets
    public List<WebElement> GetTweets(){
        return driver.findElements(TWEET);
    }
    // method that gets tweet by index


    public String GetTweetTextByIndex(List<WebElement>TweetList, int TweetIndex){
     String[] TweetText= TweetList.get(TweetIndex).getText().split("\n");
     return TweetText[TweetText.length-1];
    }
}