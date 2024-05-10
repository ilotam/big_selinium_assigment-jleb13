import org.openqa.selenium.WebDriver;

public class IntroductionPage extends PageBase{
    public IntroductionPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnMissionDescription(){
        waitUntilClickableAndReturnElement(MISSION_BUTTON).click();
        logger.info("MISSION_BUTTON clicked");
    }
}
