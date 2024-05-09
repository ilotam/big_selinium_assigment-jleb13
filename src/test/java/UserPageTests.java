import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

public class UserPageTests extends TestBase{

    public UserPageTests() throws MalformedURLException {
        super();
    }

    @Test
    public void testFillDeliveryAddressForm() {
        loginPage.loginValidUser(EMAIL, PASSWORD);
        userPage.fillDeliveryAddressForm();
        Assert.assertTrue(userPage.isFirstAddressSaveSuccessful());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
