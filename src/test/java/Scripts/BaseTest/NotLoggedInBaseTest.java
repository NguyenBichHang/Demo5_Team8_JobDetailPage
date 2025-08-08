package Scripts.BaseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

public class NotLoggedInBaseTest extends BaseTest {
    static Logger log = LogManager.getLogger("Not Logged In Base Test");

    @BeforeMethod
    public void notLoginSetup() {
        driver.navigate().to("https://demo5.cybersoft.edu.vn/jobDetail/5");
        log.info("Da truy cap trang Job Detail");
    }
}
