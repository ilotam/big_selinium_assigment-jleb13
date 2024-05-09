import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends PageBase{

    private String baseUrl = "https://www.arukereso.hu/belepes/"; // Use the actual login URL

    private final By usernameField = By.id("user_login");
    private final By passwordField = By.id("user_pass");
    private final By loginButton = By.id("login_button");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get(baseUrl);
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {
        // Check for a specific element that only appears after login
        return driver.findElement(By.id("login_success_element")).isDisplayed();
    }
    public void loginValidUser(String userName, String password) {
        try {

            System.out.println("nulaaa");

           // waitAndReturnElement(By.id("modal-login-open-desktop")).click();
            /*WebElement signInButton = waitUntilClickableAndReturnElement(By.cssSelector("a[data-toggle='login']"));
            signInButton.click();*/
            WebElement usernameInput = waitAndReturnElement(By.cssSelector("input[type='email']"));
            usernameInput.sendKeys(userName);
            System.out.println("har");
            //Thread.sleep(1000);
            WebElement passwordInput  = waitAndReturnElement(By.cssSelector("input[type='password']"));
            passwordInput.sendKeys(password);
            System.out.println("ys");

            WebElement submitButton  = waitUntilClickableAndReturnElement(By.id("login-submit"));
            submitButton.click();
        }catch (NoSuchElementException e) {
            // The element was not found, log the failure
            System.out.println("Button click failed: " + e.getMessage());
        }
    }

}
