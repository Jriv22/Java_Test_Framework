package page_objects;

import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.cssSelector;

public class TwitterLogin {
    private static final By USER_NAME_FIELD = cssSelector("[name=\"session[username_or_email]\"]");
    private static final By PASSWORD_FIELD = cssSelector("[name=\"session[password]\"]");
    private static final By LOGIN_BUTTON = cssSelector("[data-testid=\"LoginForm_Login_Button\"]");
    private static final By LOGIN_PAGE = cssSelector(".css-1dbjc4n .r-1jgb5lz");

    ChromeDriver driver;
    WebDriverWait wait;
    static final String USER_NAME = "Stephen07689444";
    static final String PASSWORD = "Iarataft2";

    public TwitterLogin(ChromeDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        WaitForLoginPage();
    }

    public void WaitForLoginPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_PAGE));

    }

    public void EnterUsername() {
        driver.findElement(USER_NAME_FIELD).sendKeys(USER_NAME);

    }
    public void EnterPassword(){
        driver.findElement(PASSWORD_FIELD).sendKeys(PASSWORD);
    }

    public TwitterHome clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        driver.findElement(LOGIN_BUTTON).click();
        return new TwitterHome(driver, wait);
    }
}

