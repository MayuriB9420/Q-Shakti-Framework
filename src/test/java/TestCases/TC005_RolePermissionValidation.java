package TestCases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PageObject.ManageAccessPage;
import PageObject.QShakti_Dashboard;
import PageObject.UserManagementPage;
import testBase.BaseClass;
import Utilities.DataProviders;
import Utilities.ExtentReportManager;

	public class TC005_RolePermissionValidation extends BaseClass {

	    @Test(dataProvider ="RolePermissionData", dataProviderClass = DataProviders.class)
	    		public void testRolePermissions(String Role, String Screen , String View, String Add, String Edit,
	                                   String Delete, String Export) throws InterruptedException {
	        logger.info("=== Validating Role: " + Role + " | Screen: " + Screen + " ===");
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	        	// Step 1: Login
	        	testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
	            logger.info("Login successful");
	            ExtentReportManager.logInfo("Login successful");
	           
	            QShakti_Dashboard dp = new QShakti_Dashboard(driver);
	            dp.clickUserSecurity();
	            ExtentReportManager.logInfo("Clicked User and Security Management");
	            logger.info("Clicked User and Security Management");
	            Thread.sleep(1500);

	            UserManagementPage userPage = new UserManagementPage(driver);
	            userPage.navigateToRoleManagement();
	            logger.info("Clicked role Management");
	            Thread.sleep(1500);

	            ManageAccessPage map = new ManageAccessPage(driver);
	            driver.findElement(By.xpath("//button[normalize-space()='Manage Access']")).click();
	           // map.ManageAccessButton();
               logger.info("Clicked manage aceess Management");
	           Thread.sleep(1500); // wait for refresh
		      /*  String role = "Admin"; // Change to "Planner" or "Supervisor" as needed
		       
			 WebElement op= driver.findElement(By.xpath("//input[@placeholder='Choose a role']"));
			 op.sendKeys(role);
			 Thread.sleep(1000);
		        
			 WebElement roleTab= driver.findElement(By.xpath("//span[contains(text(),'" + role + "')]"));
			 roleTab.click();
			   
	        Thread.sleep(1000);
            logger.info("Clicked admin");*/
            
          //  String role = "Admin"; // Change to "Planner" or "Supervisor" as needed

         
         logger.info("Clicked role: " + Role);


	    }
	      

	    private void validatePermission(UserManagementPage userPage, String screen,
	                                    String permission, String expected) {
	        boolean actual = userPage.isPermissionChecked(screen, permission);
	        if (expected.equalsIgnoreCase("Y")) {
	            Assert.assertTrue(actual, permission + " should be enabled for " + screen);
	        } else {
	            Assert.assertFalse(actual, permission + " should NOT be enabled for " + screen);
	        }
	    }
	    
	    
	}

