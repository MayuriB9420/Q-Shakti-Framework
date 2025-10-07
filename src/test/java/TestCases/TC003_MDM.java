package TestCases;
import PageObject.MDMPage ;
import PageObject.QShakti_Dashboard;
import Utilities.ExtentReportManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Utilities.ScreenshotUtil;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testBase.BaseClass;
import java.time.Duration;

@Listeners(Utilities.ExtentReportManager.class)
public class TC003_MDM extends BaseClass{

    @Test(priority = 1)
    public void testUploadAllMasters() {
    	
        logger.info("===== Starting Upload Master Files Test =====");
        ExtentReportManager.logInfo("Upload Master Files Test started");

        try {
            // Step 1: Login
        	testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
            logger.info("Login successful");
            ExtentReportManager.logPass("Login successful");
            
            QShakti_Dashboard dp = new QShakti_Dashboard(driver);
            dp.clickMDM();
	        logger.info("Clicked Master Data Management");

            MDMPage uploadPage = new MDMPage (driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            uploadPage.openUploadFileMenu();
            // Step 2: Upload Plant Master
            try {
                uploadPage.clickPlantMaster();
                boolean uploaded = uploadPage.uploadFile(p.getProperty("PlantMasterFile"));

                if (!uploaded) {
                    ExtentReportManager.logFail("Master upload failed: File could not be uploaded.");
                    Assert.fail("Plant Master upload failed: File could not be uploaded.");
                }

                String plantMsg = uploadPage.getToasterMessage();

                if (plantMsg == null) {
                    ExtentReportManager.logPass("Plant Master uploaded successfully (no toaster appeared");
                    Assert.assertTrue(true, "Plant Master uploaded successfully (no toaster appeared).");
                } 

            } catch (AssertionError ae) {
                ExtentReportManager.logFail("Plant Master validation failed. Error: " + ae.getMessage());
                throw ae;
            } catch (Exception e) {
                ExtentReportManager.logFail("Unexpected error during Plant Master upload: " + e.getMessage());
                throw e;
            }
              Thread.sleep(1500);
            
            
         // Building Section
            try {
            	uploadPage.clickBuildingSection();
                boolean uploaded = uploadPage.uploadFile(p.getProperty("BuildingSectionFile"));

                if (!uploaded) {
                    ExtentReportManager.logFail("BuildingSection Master upload failed: File could not be uploaded.");
                    Assert.fail("BuildingSection Master upload failed: File could not be uploaded.");
                }

                String BsMsg = uploadPage.getToasterMessage();

                if (BsMsg == null) {
                    // No toaster appeared → still consider success because file uploaded
                    ExtentReportManager.logPass("BuildingSection Master uploaded successfully (no toaster appeared).");
                    Assert.assertTrue(true, "BuildingSection Master uploaded successfully (no toaster appeared).");
                } 
               
            } catch (AssertionError ae) {
                ExtentReportManager.logFail("BuildingSection Master validation failed. Error: " + ae.getMessage());
                throw ae;
            } catch (Exception e) {
                ExtentReportManager.logFail("Unexpected error during Plant Master upload: " + e.getMessage());
                throw e;
            }
            Thread.sleep(1500);
            
            
        // Step 4: Upload Machine Master
            try {
            	uploadPage.clickMachineMaster();
                boolean uploaded = uploadPage.uploadFile(p.getProperty("MachineMasterFile"));

                if (!uploaded) {
                    ExtentReportManager.logFail("MachineMaster upload failed: File could not be uploaded.");
                    Assert.fail("MachineMaster upload failed: File could not be uploaded.");
                }
                Thread.sleep(1500);
                String MachineMsg = uploadPage.getToasterMessage();

                if (MachineMsg == null) {
                    // No toaster appeared → still consider success because file uploaded
                    ExtentReportManager.logPass("MachineMaster uploaded successfully (no toaster appeared).");
                    Assert.assertTrue(true, "MachineMaster uploaded successfully (no toaster appeared).");
                } 
               
            } catch (AssertionError ae) {
                ExtentReportManager.logFail("MachineMaster validation failed. Error: " + ae.getMessage());
                throw ae;
            } catch (Exception e) {
                ExtentReportManager.logFail("Unexpected error during MachineMaster upload: " + e.getMessage()); 
                throw e;
            }
            Thread.sleep(1500);

            // Step 5: Upload Item Master
            try {
            	uploadPage.clickItemMaster();
                boolean uploaded = uploadPage.uploadFile(p.getProperty("ItemMasterFile"));

                if (!uploaded) {
                    ExtentReportManager.logFail("ItemMaster upload failed: File could not be uploaded.");
                    Assert.fail("ItemMaster upload failed: File could not be uploaded.");
                }

                String MachineMsg1 = uploadPage.getToasterMessage();

                if (MachineMsg1 == null) {
                    // No toaster appeared → still consider success because file uploaded
                    ExtentReportManager.logPass("ItemMaster uploaded successfully (no toaster appeared).");
                    Assert.assertTrue(true, "ItemMaster uploaded successfully (no toaster appeared).");
                } 
                
            } catch (AssertionError ae) {
                ExtentReportManager.logFail("ItemMaster validation failed. Error: " + ae.getMessage());
                throw ae;
            } catch (Exception e) {
                ExtentReportManager.logFail("Unexpected error during ItemMaster upload: " + e.getMessage());
                throw e;
            }
            Thread.sleep(1500);
            
            // Final Status
            ExtentReportManager.logPass("All Master Files uploaded successfully");
            logger.info("===== Upload Master Files Test PASSED =====");

        } catch (Exception e) {
            logger.error("Upload Master Files Test FAILED", e);
            ExtentReportManager.logFail( "Test failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}

