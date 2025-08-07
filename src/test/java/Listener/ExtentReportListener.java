package Listener;

import Scripts.BaseTest.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentManager;
import utilities.ScreenshotManager;

public class ExtentReportListener implements ITestListener {
    public static ExtentReports extent = ExtentManager.createInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test result: PASS " +getTestDescription(result));
    }

    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, getTestDescription(result));
//        test.get().log(Status.FAIL, getTestName(result) + result.getThrowable().toString());
//        String screenshotPath = "./screenshots/" + result.getName() + ".png";
//        test.get().addScreenCaptureFromPath(screenshotPath);
        try {
            Object currentClass = result.getInstance();
            WebDriver driver = ((BaseTest) currentClass).getDriver();
//      Dùng Base64 thay vì file path
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            String customName = "Error_Screenshot_" + result.getMethod().getMethodName();
            test.get().log(Status.FAIL, "Test result: " + result.getThrowable().getMessage());
            test.get().log(Status.INFO, "📸 Screenshot Evidence: " , MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            ScreenshotManager.addDownloadableScreenshot(test.get(), driver, result.getMethod().getMethodName());

//        ScreenshotUtils.addDownloadableScreenshot(test.get(), driver, result.getMethod().getMethodName());
//        ExtentTest screenshotNode = test.get().createNode("Screenshot Evidence");
//        screenshotNode.info("Screenshot captured",
//                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    public void onFinish(ITestContext result) {
//        log.info("End testing " + result.getName());
        //Kết thúc và thực thi Extents Report
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
