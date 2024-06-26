import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage extends PageBase{

    public UserPage(WebDriver driver) {
        super(driver);
        PageBase.driver.get(baseUrl);
    }

    public void fillDeliveryAddressForm() throws InterruptedException {
        if(!areAccountSettingsAgreementsSaved()){
            fillSettingsOfProfile();
            Thread.sleep(100);
        }else{
            System.out.println(MY_DATA_SHEET);
            logger.info("before MY_DATA_SHEET");
            waitUntilClickableAndReturnElement(MY_DATA_SHEET).click();
        }

        System.out.println(MY_DATA_SHEET);
        logger.info("Entered MY_DATA_SHEET");
        waitUntilClickableAndReturnElement(DELIVERY_SETTINGS).click();
        logger.info("Entered DELIVERY_SETTINGS");
        waitAndReturnElement(NAME_INPUT).sendKeys("asd");
        logger.info("Entered NAME_INPUT");
        waitAndReturnElement(CITY_INPUT).sendKeys("asd");
        logger.info("Entered CITY_INPUT");
        waitAndReturnElement(POSTAL_CODE_INPUT).sendKeys("1232");
        logger.info("Entered POSTAL_CODE_INPUT");
        waitAndReturnElement(STREET_INPUT).sendKeys("asd");
        logger.info("Entered STREET_INPUT");
        waitAndReturnElement(ADDRESS_INPUT).sendKeys("asd");
        logger.info("Entered ADDRESS_INPUT");
        waitUntilClickableAndReturnElement(ADDRESS_SUBMIT).click();
        logger.info("Sent ADDRESS");
    }

    public void logoutValidUser() {

        WebElement submitButton  = waitUntilClickableAndReturnElement(LOGOUT_BUTTON);
        submitButton.click();
        logger.info("User logged out.");
    }

    public void clickOnIntroduction() throws InterruptedException {
        //isLoginSuccessful();
        driver.findElement(BODY).sendKeys(Keys.CONTROL, Keys.END);
        Thread.sleep(100);
        waitUntilClickableAndReturnElement(INTRODUCTION_BUTTON).click();
        logger.info("INTRODUCTION_BUTTON clicked");
    }

    public void fillSettingsOfProfile(){

        waitUntilClickableAndReturnElement(MY_DATA_SHEET).click();
        logger.info("MY_DATA_SHEET clicked");
        waitUntilClickableAndReturnElement(ACCOUNT_SETTINGS_BUTTON).click();
        logger.info("ACCOUNT_SETTINGS_BUTTON clicked");
        waitUntilClickableAndReturnElement(CHECK_ALL_BUTTON).click();
        logger.info("CHECK_ALL_BUTTON clicked");
        waitUntilClickableAndReturnElement(SAVE_BUTTON).click();
        logger.info("SAVE_BUTTON clicked");
    }
}
