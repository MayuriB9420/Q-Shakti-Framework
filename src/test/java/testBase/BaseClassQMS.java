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
import PageObject.Login_QMS_Page;
import Utilities.ExtentReportManager;

	public class BaseClassQMS {

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
	    public void setup(Method method) throws Exception {
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
	        String appURL = p.getProperty("appURLQMS");
	        driver.get(appURL);

	        logger.info("========== Application Launched: " + appURL + " ==========");
	        
	        testLoginFunctionality(p.getProperty("emailqms"), p.getProperty("pwdqms"));
	        logger.info("✅ Successfully logged in to QMS");
	        
	        ExtentTest test = ExtentReportManager.getExtentReports().createTest(method.getName());
	        ExtentReportManager.setTest(test);

	    }

	    public void testLoginFunctionality(String username, String password) throws Exception {
                LoginPage lp = new LoginPage(driver);

                // Enter username & password from properties
                lp.setUsername(p.getProperty("emailqms"));
                lp.setPassword(p.getProperty("pwdqms"));
                Thread.sleep(1500);
                lp.clickLogin();

            }
	
	    
//	    @AfterMethod(alwaysRun = true)
//	    public void teardown() {
//	        if (driver != null) {
//	            logger.info("========== Closing Browser ==========");
//	            driver.quit();
//	        }
//	    }
//	    
	    @AfterSuite(alwaysRun = true)
	    public void generateReport() {
	        if (ExtentReportManager.getExtentReports() != null) {
	            ExtentReportManager.getExtentReports().flush();
	        }
	    }    }
	
