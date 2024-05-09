import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PageBase {

    protected String baseUrl = "https://www.arukereso.hu/belepes/";
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected static final By AFTER_LOGOUT = By.cssSelector("a[href*='muszaki-cikk']");
    protected static final By INPUT_TYPE_EMAIL = By.cssSelector("input[type='email']");
    protected static final By INPUT_TYPE_PASSWORD = By.cssSelector("input[type='password']");
    protected static final By LOGIN_SUBMIT = By.id("login-submit");
    protected static final By DELIVERY_SETTINGS = By.cssSelector("a.sub-menu-item[data-menu-content-id='settings-delivery']");
    protected static final By NAME_INPUT = By.cssSelector("input[name='name']");
    protected static final By CITY_INPUT = By.cssSelector("input[name='city']");
    protected static final By POSTAL_CODE_INPUT = By.cssSelector("input[name='postalCode']");
    protected static final By STREET_INPUT = By.cssSelector("input[name='street']");
    protected static final By ADDRESS_INPUT = By.cssSelector("input[name='addressName']");
    protected static final By ADDRESS_SUBMIT = By.xpath("//a[contains(text(), 'Cím hozzáadása')]");
    protected static final By ADDRESS_NEW_INPUT = By.xpath("//a[contains(text(), 'Cím hozzáadása')]");
    protected static final By LOGOUT_BUTTON = By.cssSelector("a[href*='operation=logout'] .icon-logout");
    protected static final By MY_DATA_SHEET = By.xpath("//a[contains(., 'Saját adatlapom') and contains(@href, '#')]");
    protected static final Logger logger = Logger.getLogger(TestBase.class.getName());

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        setupLogger();
    }

    public static void setupLogger() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    protected WebElement waitUntilClickableAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this.driver.findElement(locator);
    }

    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }

    public String getHeadTextTitle() {
       return driver.getTitle();
    }

    public boolean isLoginSuccessful() {
        return waitAndReturnElement(By.className("menu-container")).isDisplayed();
    }

    public boolean isLogoutSuccessful() {
        return waitAndReturnElement(AFTER_LOGOUT).isDisplayed();
    }

    public boolean isFirstAddressSaveSuccessful(){
        return waitAndReturnElement(ADDRESS_NEW_INPUT).isDisplayed();
    }

}
