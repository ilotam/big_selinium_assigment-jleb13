import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

public class LoginTests extends TestBase{

    public LoginTests() throws MalformedURLException {
        super();
    }
    @Test
    public void testLogin() {
        loginPage.loginValidUser(EMAIL, PASSWORD);
        Assert.assertTrue(loginPage.isLoginSuccessful());
    }

    @Test
    public void testLogoutAfterLogin() {
        loginPage.loginValidUser(EMAIL, PASSWORD);
        userPage.logoutValidUser();
        Assert.assertTrue(loginPage.isLogoutSuccessful());
    }

    @After
    public void tearDown() {
        quitDriver();
    }
}
