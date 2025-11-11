package TestCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.Dashboard1Page;
import PageObject.QShakti_Dashboard;
import Utilities.ExtentReportManager;
import testBase.BaseClass;


public class TC013_DashboardTestCase  extends BaseClass {
	

	   // @Test(dataProvider = "DashboardData", dataProviderClass = Utilities.DataProviders.class)
	    @Test
	   // public void verifyDashboardFilters(String section, String operation, String itemCode,
	                                      // String inspectionParam, String qcMachine) throws InterruptedException {
	    	  public void verifyDashboardFilters() throws InterruptedException {
	    	
	        logger.info("=== Starting Dashboard Validation for Section");
	        ExtentReportManager.logInfo("Starting Dashboard Validation for Section: " );

	        // Login
	        /*testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
	        logger.info("Login successful");*/
	       

	        // Navigate to Dashboard module
	        QShakti_Dashboard dashMenu = new QShakti_Dashboard(driver);
	        dashMenu.clickDashboard();  // Create a method in QShakti_Dashboard if not present
	        Thread.sleep(2000);
	        ExtentReportManager.logInfo("Navigated to Dashboard module");
	        logger.info("Navigated to Dashboard module");
	        
	        
	        // Open dashboard page
	        Dashboard1Page dashboard = new Dashboard1Page(driver);
	        dashboard.openDashboard1();
	        ExtentReportManager.logInfo("Navigated to Dashboard1 module");
	        logger.info("Navigated to Dashboard1 module");
	        Thread.sleep(2000);
	        
	        dashboard.clickInprocessButton();
	        logger.info("Clicked Inprocess Button");
	        Thread.sleep(2000);
	        
	        dashboard.clickRMButton();
	        logger.info("Clicked RM Button");
	        Thread.sleep(2000);
	      
	        dashboard.openDashboard2();
	        ExtentReportManager.logInfo("Navigated to Dashboard2 module");
	        logger.info("Navigated to Dashboard2 module");
	        Thread.sleep(2000);
	        
	        dashboard.clickInprocessButton();
	        logger.info("Clicked Inprocess Button");
	        Thread.sleep(2000);
	        
	        dashboard.clickRMButton();
	        logger.info("Clicked RM Button");
	        Thread.sleep(1000);

	        // Fill filters
	       /* dashboard.selectSectionName(section);
	        Thread.sleep(1000);
	        
	        dashboard.selectOperation(operation);
	        Thread.sleep(1000);
	        
	        dashboard.selectItemCode(itemCode);
	        Thread.sleep(1000);
	        
	        dashboard.selectInspectionParam(inspectionParam);
	        Thread.sleep(1000);
	        
	        dashboard.selectQCMachine(qcMachine);
	        Thread.sleep(1000);
*/
	   //     dashboard.clickSearchOrApply();
	       // Thread.sleep(2000);

	        // Validation
	        boolean loaded = dashboard.verifyDashboardLoaded();
	        Assert.assertTrue(loaded, "Dashboard did not load correctly for Section: " );

	        ExtentReportManager.logPass("Dashboard loaded successfully for:  section B" );
	        logger.info("Dashboard validated for section:B " );
	        
	        QShakti_Dashboard dashMenu1 = new QShakti_Dashboard(driver);
	        dashMenu.clickQShaktiimg();
	        logger.info("Navigated to QShakti_Dashboard");
	        Thread.sleep(1500);
	    }
	}



