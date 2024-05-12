import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

public class UserPageTests extends TestBase{

    public UserPageTests() throws MalformedURLException {
        super();
    }

    @Test
    public void testFillDeliveryAddressForm() throws Exception {
        loginPage.registerNewUser();
        userPage.fillDeliveryAddressForm();
        Thread.sleep(100);
        Assert.assertTrue(userPage.isFirstAddressSaveSuccessful());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
