import org.junit.After;
import org.junit.Test;

import java.net.MalformedURLException;

public class RegistrationTests extends TestBase{

    EmailService emailService;
    public RegistrationTests() throws MalformedURLException {
        super();
        emailService = new EmailService();
    }

    @Test
    public void testRegistration() throws Exception {
        loginPage.registerNewUser();
        loginPage.isLoginSuccessful();
    }

    @After
    public void tearDown() {
        quitDriver();
    }
}
