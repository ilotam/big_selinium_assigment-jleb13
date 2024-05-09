import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase{

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get(baseUrl);
    }

    public void loginValidUser(String userName, String password) {
        try {
            WebElement usernameInput = waitAndReturnElement(INPUT_TYPE_EMAIL);
            usernameInput.sendKeys(userName);
            WebElement passwordInput  = waitAndReturnElement(INPUT_TYPE_PASSWORD);
            passwordInput.sendKeys(password);
            WebElement submitButton  = waitUntilClickableAndReturnElement(LOGIN_SUBMIT);
            submitButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("Button click failed: " + e.getMessage());
        }
    }



}
