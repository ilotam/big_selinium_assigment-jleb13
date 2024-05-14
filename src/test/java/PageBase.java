import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PageBase {

    protected String baseUrl = "https://www.arukereso.hu/belepes/";
    protected static WebDriverWait wait;
    protected static WebDriver driver;
    protected static JavascriptExecutor js ;
    protected static final By AFTER_LOGOUT = By.cssSelector("a[href*='muszaki-cikk']");
    protected static final By INPUT_TYPE_EMAIL = By.cssSelector("input[type='email']");
    protected static final By INPUT_TYPE_PASSWORD = By.cssSelector("input[type='password']");
    protected static final By LOGIN_SUBMIT = By.id("login-submit");
    protected static final By REGISTRATION_SUBMIT = By.id("reg-submit");
    protected static final By SAVE_BUTTON = By.className("btn-submit");
    protected static final By OK_ICON = By.className("icon-ok");
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
    protected static final By ACCOUNT_SETTINGS_BUTTON = By.xpath("//a[contains(@href, '#settings-account')]");
    protected static final By INTRODUCTION_BUTTON = By.xpath("//a[text()='Bemutatkozás']");
    protected static final By OUR_CONTACTS_BUTTON = By.xpath("//a[text()='Elérhetőségeink']");
    protected static final By OUR_REWARDS_BUTTON = By.xpath("//a[text()='Díjaink és elismeréseink']");
    protected static final By REGISTRATION_BUTTON = By.xpath("//a[@href='https://www.arukereso.hu/regisztracio/']");
    protected static final By OUR_REWARDS =By.xpath("//div[contains(@class, 'st-1') and .//span[contains(text(), 'Superbrands')]]");
    protected static final By OUR_CONTACTS_DIV = By.xpath("//p[text()='Elérhetőségeink']");
    protected static final By CHECK_ALL_BUTTON = By.xpath("//a[@data-cbparent='checkall']");
    protected static final By MISSION_BUTTON = By.xpath("//div[contains(@class, 'st-1') and .//span[contains(text(), 'Az Árukereső.hu küldetése')]]");
    protected static final By MISSION_DESCRIPTION_VISIBILITY = By.xpath("//div[@class='st-content clearfix' and contains(@style, 'display: block')]");
    protected static final By BODY = By.cssSelector("body");
    protected static final Logger logger = Logger.getLogger(TestBase.class.getName());
    public PageBase(WebDriver driver) {
        PageBase.driver = driver;
        driver.get(baseUrl);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 30);
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected static WebElement waitUntilClickableAndReturnElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    public String getHeadTextTitle() {
        String title = driver.getTitle();
        logger.info("Page title is: " + title);
        return title;
    }

    public static boolean clickAndVerifyElements(Map<By, By> elementsToClickAndCheck) throws InterruptedException {
        for (Map.Entry<By, By> entry : elementsToClickAndCheck.entrySet()) {
            waitUntilPageScrolledDownAndItemIsClickable(entry.getKey());
            logger.info(entry.getKey() + " clicked");
            WebElement elementToCheck = wait.until(ExpectedConditions.visibilityOfElementLocated(entry.getValue()));
            if (elementToCheck.isDisplayed()) {
                logger.info(entry.getValue() + " is displayed as expected.");
            } else {
                logger.severe(entry.getValue() + " is not displayed as expected.");
                return false;
            }
        }
        return true;
    }

    public static void waitUntilPageScrolledDownAndItemIsClickable(By elementLocator) throws InterruptedException {
        WebElement element = waitUntilClickableAndReturnElement(elementLocator);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);
        element.click();
        logger.info(elementLocator + " clicked");
    }

    public boolean isLoginSuccessful() {
        return waitAndReturnElement(By.className("menu-container")).isDisplayed();
    }

    public boolean isLogoutSuccessful() {
        return waitAndReturnElement(AFTER_LOGOUT).isDisplayed();
    }
    public boolean areAccountSettingsAgreementsSaved() {
        try {
            return driver.findElement(OK_ICON).isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isFirstAddressSaveSuccessful(){
        return waitAndReturnElement(ADDRESS_NEW_INPUT).isDisplayed();
    }
    public boolean isMissionBlockDisplayed(){
        return waitAndReturnElement(MISSION_DESCRIPTION_VISIBILITY).isDisplayed();
    }

    public void registerNewUser() throws Exception {
        String email = RandomStringUtils.random(10, true, true) + "@restmail.net";
        String password = RandomStringUtils.random(10, true, true) + "A1";
        logger.info(email);
        logger.info(password);
        waitUntilClickableAndReturnElement(REGISTRATION_BUTTON).click();
        waitAndReturnElement(INPUT_TYPE_EMAIL).sendKeys(email);
        waitAndReturnElement(INPUT_TYPE_PASSWORD).sendKeys(password);
        waitUntilClickableAndReturnElement(REGISTRATION_SUBMIT).click();
        Thread.sleep(10000);
        String registrationUrl = EmailService.fetchLatestRegistrationUrl(email);
        if (registrationUrl != null) {
            driver.get(registrationUrl);
        }
    }

}
