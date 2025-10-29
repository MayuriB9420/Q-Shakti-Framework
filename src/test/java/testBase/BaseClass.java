package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import PageObject.LoginPage;
import Utilities.ExtentReportManager;

public class BaseClass {

    public WebDriver driver;
    public  Logger logger;
    public static Properties p;  //  public & static so accessible everywhere
    public WebDriverWait wait;

    
    @BeforeSuite(alwaysRun = true)
    public void loadConfigAndLogger() throws IOException {
        // Load configuration
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        // Logger setup
        logger = LogManager.getLogger(this.getClass());
    }

     //Initializes browser before each test
     
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {
        logger.info("========== Launching Browser ==========");

        // Set Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        // Disable Chrome password manager
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.popups", 2);

        options.setExperimentalOption("prefs", prefs);

        // Launch browser
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Navigate to app URL
        String appURL = p.getProperty("appURL");
        driver.get(appURL);

        logger.info("========== Application Launched: " + appURL + " ==========");
        
        // Perform login before each test
        //testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
        
        // ✅ Initialize ExtentTest for each test method
        ExtentTest test = ExtentReportManager.getExtentReports().createTest(method.getName());
        ExtentReportManager.setTest(test);

    }
        public void testLoginFunctionality(String username, String password) {
            logger.info("=== Starting Login Functionality Test ===");

            try {
                LoginPage lp = new LoginPage(driver);

                // Enter username & password from properties
                lp.setUsername(p.getProperty("email"));

                // Check that password is masked
                String inputType = lp.getPasswordField().getAttribute("type");
                Assert.assertEquals(inputType, "password", "Password should be hidden while typing");

                lp.setPassword(p.getProperty("password"));
                Thread.sleep(1500);
                lp.clickLogin();

                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//                try {
//                    // Check for dashboard (valid login)
//                    WebElement dashboardElement = wait.until(
//                        ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//h6[normalize-space()='Dashboard']")
//                        ) );
//                    Assert.assertTrue(dashboardElement.isDisplayed(), "Dashboard should be displayed");
//                    logger.info("=== Login Test Passed: Valid Credentials ===");
//
//                } catch (Exception e) {
//                    // If dashboard not found, check for toaster (invalid login)
//                    WebElement toasterMsg = wait.until(
//                        ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("By.xpath(\"By.xpath(\"//div[@role='alert' and contains(@class,'Toastify__toast-body')]//div]")
//                        ) );
//
//                    String message = toasterMsg.getText();
//                    logger.warn("Toaster Message displayed: " + message);
//
//                    Assert.assertTrue(message.contains("Invalid username or password"),
//                            "Expected invalid login toaster message");
//                    logger.info("=== Login Test Passed: Invalid Credentials Handled ===");
//                }
            } catch (Exception e) {
                logger.error("Login Test FAILED", e);
                Assert.fail("Login test failed: " + e.getMessage());
            }
        }

 
    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            logger.info("========== Closing Browser ==========");
            driver.quit();
        }
    }
    
    @AfterSuite(alwaysRun = true)
    public void generateReport() {
        if (ExtentReportManager.getExtentReports() != null) {
            ExtentReportManager.getExtentReports().flush();
        }
    }
}
