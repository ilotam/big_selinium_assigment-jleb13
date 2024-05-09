import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage extends PageBase{

    public UserPage(WebDriver driver) {
        super(driver);
        this.driver.get(baseUrl);
    }

    public void fillDeliveryAddressForm(){
        System.out.println(MY_DATA_SHEET);
        logger.info("before MY_DATA_SHEET");
        waitUntilClickableAndReturnElement(MY_DATA_SHEET).click();
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
        logger.info("Entered ADDRESS_SUBMIT");
    }

    public void logoutValidUser() {
        try {
            WebElement submitButton  = waitUntilClickableAndReturnElement(LOGOUT_BUTTON);
            submitButton.click();
        } catch (NoSuchElementException e) {
            System.out.println("Button click failed: " + e.getMessage());
        }
    }
}
