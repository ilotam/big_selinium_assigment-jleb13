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
        loginPage.registerNewUser("asdASDTESTasd123456@restmail.net", "asd123ASD");
        Thread.sleep(10000);
        String registrationUrl = EmailService.fetchLatestRegistrationUrl("asdASDTESTasd123456@restmail.net");
        if (registrationUrl != null) {
            driver.get(registrationUrl);
        }
        loginPage.isLoginSuccessful();
    }

    @After
    public void tearDown() {
        quitDriver();
    }
}
