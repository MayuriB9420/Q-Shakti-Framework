package Utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(
                    System.getProperty("user.dir") + "/Test-Output/ExtentReport.html");

            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Functional Testing");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Tester", "QA Engineer");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }

    
    // Manage test lifecycle
    public static void setTest(ExtentTest test) {
        testThread.set(test);
    }
    
    // ✅ Get ExtentTest for current thread
    public static ExtentTest getTest() {
        return testThread.get();
    }

    // Logging Helpers
    public static void logInfo(String message) {
        if (getTest() != null) getTest().log(Status.INFO, message);
    }

    public static void logPass(String message) {
        if (getTest() != null) getTest().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        if (getTest() != null) getTest().log(Status.FAIL, message);
    }

    // TestNG Listeners
    @Override
    public void onStart(ITestContext context) {
        getExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test); // 🔑 set test for current thread
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().pass("Test passed");

        try {
            WebDriver driver = ((BaseClass) result.getInstance()).driver;
            String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName() + "_Success");
            getTest().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getTest().fail(result.getThrowable());

        try {
            WebDriver driver = ((BaseClass) result.getInstance()).driver;
            String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName() + "_Failure");
            getTest().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().skip("Test skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
