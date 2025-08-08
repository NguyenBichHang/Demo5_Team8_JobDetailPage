package Listener;

import Scripts.BaseTest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;

import java.io.File;
import java.io.IOException;

public class SimpleListener implements ITestListener {
    static Logger log = LogManager.getLogger("Log");
    //    @Override
    public void onTestStart(org.testng.ITestResult result) { /* compiled code */ }
    //    @Override
    public void onTestSuccess(org.testng.ITestResult result) {
        log.info("Day la testcase chay thanh cong: " + result.getName());
    }
    //    @Override
    public void onTestFailure(org.testng.ITestResult result) {
//        log.error("Day la testcase bi fail" + result.getName());
//        System.out.println("Screenshot captured for test case: " + result.getName());
//
//        Object currentClass = result.getInstance();
//        WebDriver driver = ((BaseTest) currentClass).getDriver();
//
//        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            Date date = new Date();
//            String filePath = "." + File.separator + "screenshots" + File.separator + result.getName() + ".png";
//            File destFile = new File(filePath);
//            destFile.getParentFile().mkdirs();
//
//            FileHandler.copy(srcFile, destFile);
//            System.out.println("Screenshot saved: " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("Screenshot captured for test case: " + result.getName());

        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(srcFile, new File("./screenshots/" + result.getName() + ".png"));
            System.out.println("Screenshot captured for test case: " + result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(org.testng.ITestResult result) { /* compiled code */}
    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult result) { /* compiled code */ }
    public void onTestFailedWithTimeout(org.testng.ITestResult result) { /* compiled code */ }
    //    @Override
    public void onStart(org.testng.ITestContext context) {
        log.info("Bat dau auto test");
    }
    //    @Override
    public void onFinish(org.testng.ITestContext context) {
        log.info("Ket thuc auto test");
    }
}
