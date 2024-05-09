import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    protected WebDriver driver;

    protected LoginPage loginPage;
    protected UserPage userPage;
    protected final String EMAIL = "ilosvaytomi@gmail.com";
    protected final String PASSWORD = "asd123ASD";

    public TestBase() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(this.driver);
        userPage = new UserPage(this.driver);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
