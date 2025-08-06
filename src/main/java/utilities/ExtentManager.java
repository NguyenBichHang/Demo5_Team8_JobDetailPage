package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports createInstance() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-" + timeStamp + ".html";
        String reportPath = "./reports/ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setDocumentTitle("Automation Test Report - Demo5 Project");
        reporter.config().setReportName("Demo5 - Test Results");
        reporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Project", "Demo5");
        extent.setSystemInfo("Report Name", "Automation Test Report");
        extent.setSystemInfo("Tester", "Hang Nguyen");
        extent.setSystemInfo("Environment", "Staging");
        return extent;
    }
}
