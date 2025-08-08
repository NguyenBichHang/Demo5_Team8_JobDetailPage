package Scripts.BaseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    static Logger log = LogManager.getLogger("Base Test");

    public WebDriver getDriver() {
        return driver;
    }
    @BeforeMethod
    public void setUpTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        driver.get("https://demo5.cybersoft.edu.vn/");
        log.info("Da truy cap thanh cong");
    }

//    @AfterMethod
//    public void tearDown () {
//        driver.quit();
//        driver.close();
//    }
}
