import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTests {

    private WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        //driver.wait(20);
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(this.driver);
        //Assert.assertTrue(loginPage.getHeadTextTitle().contains("Todoist"));

        loginPage.loginValidUser("ilosvaytomi@gmail.com", "asd123ASD");


        //System.out.println(loginPage.getBodyText());
       //WebElement profileLink = loginPage.waitAndReturnElement(By.cssSelector("a[data-toggle='login']"));
       WebElement profileLink = loginPage.waitAndReturnElement(By.className("menu-container"));
        Assert.assertTrue(profileLink.isDisplayed());

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
