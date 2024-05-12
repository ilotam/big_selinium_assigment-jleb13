import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class GeneralTests extends TestBase{
    public GeneralTests() throws MalformedURLException {
        super();
    }

    @Test
    public void testIntroductionPageDisplayedAfterLogin() throws Exception {
        loginPage.registerNewUser();
        userPage.clickOnIntroduction();
        introductionPage.clickOnMissionDescription();
        Assert.assertTrue(introductionPage.isMissionBlockDisplayed());
    }

    @Test
    public void testPageTitle() {
        Assert.assertEquals("Árukereső.hu bejelentkezés", loginPage.getHeadTextTitle());
    }

    @Test
    public void testMultipleStaticPages() throws InterruptedException {
        Map<By, By> testMap = new HashMap<>();
        testMap.put(PageBase.INTRODUCTION_BUTTON, PageBase.MISSION_BUTTON);
        testMap.put(PageBase.OUR_CONTACTS_BUTTON, PageBase.OUR_CONTACTS_DIV);
        testMap.put(PageBase.OUR_REWARDS_BUTTON, PageBase.OUR_REWARDS);
        Assert.assertTrue(PageBase.clickAndVerifyElements(testMap));
    }

    @Test
    public void testAccountSettingsAgreementsChecked() throws Exception {
        loginPage.registerNewUser();
        userPage.fillSettingsOfProfile();
        Assert.assertTrue(userPage.areAccountSettingsAgreementsSaved());
    }

    @After
    public void tearDown() {
        quitDriver();
    }
}
