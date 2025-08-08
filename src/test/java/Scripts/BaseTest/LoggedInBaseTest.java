package Scripts.BaseTest;

import Pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

public class LoggedInBaseTest extends BaseTest {
    static Logger log = LogManager.getLogger("Logged In Base Test");

    @BeforeMethod
    public void loginSetup() {
        LoginPage loginPage = new LoginPage(driver);
        driver.navigate().to("https://demo5.cybersoft.edu.vn/login");
        loginPage.login("bichhanghbn@gmail.com","bichhang777#");
        log.info("Da login thanh cong");
        driver.navigate().to("https://demo5.cybersoft.edu.vn/jobDetail/5");
        log.info("Da truy cap trang Job Detail");
    }
}
