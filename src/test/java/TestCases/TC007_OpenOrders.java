package TestCases;

import PageObject.OpenOrdersPage;
import PageObject.QShakti_Dashboard;
import Utilities.ExtentReportManager;
import testBase.BaseClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TC007_OpenOrders extends BaseClass {
	    
		 @Test(priority = 1)
		    public void uploadAndValidateProductionPlanner()throws InterruptedException {
			 
		        logger.info("=== Uploading Production Planner Excel and verifying data ===");

		        //  Login
	        	testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
	            logger.info("Login successful");
	            ExtentReportManager.logInfo("Login successful");
	            Thread.sleep(1000);

	            QShakti_Dashboard dp = new QShakti_Dashboard(driver);
	            dp.clickOrderManagement();
	            ExtentReportManager.logInfo("Clicked Order Management Module");
		        logger.info("Clicked order Management Module");
	            Thread.sleep(1000);
	            
		        OpenOrdersPage openOrdersPage = new OpenOrdersPage(driver);
		        openOrdersPage.clickOpenOrdersPage();
	            Thread.sleep(800);

		        openOrdersPage.clickProductionPlannerTab();
	            Thread.sleep(800);
	            ExtentReportManager.logInfo("Clicked Production Planner tab");
		        logger.info(" Clicked Production Planner tab");
		       		        
		      //  openOrdersPage.clickUpload();
                boolean uploaded = openOrdersPage.uploadFile(p.getProperty("ProductionPlannerFile"));
               // Assert.assertTrue(uploaded, "Production Planner file upload failed");
                
                if (!uploaded) {
                    ExtentReportManager.logFail("Production Planner upload failed: File could not be uploaded.");
                    Assert.fail("Production Planner  upload failed: File could not be uploaded.");
                }
		        logger.info(" Production Planner Excel uploaded");
	            Thread.sleep(1500);
	            
	            String ProplannerMsg = openOrdersPage.getToasterMessage();

                if (ProplannerMsg == null) {
                    ExtentReportManager.logPass("ProductionPlanner uploaded successfully (no toaster appeared");
                    Assert.assertTrue(true, "ProductionPlanner uploaded successfully (no toaster appeared).");
                } 
                else if (ProplannerMsg.contains("Production Planner created: 0")) {
                    ExtentReportManager.logPass(" ProductionPlanner uploaded successfully: " + ProplannerMsg);
                    Assert.assertTrue(true, "Production Planner created: 0");
                    logger.info("ProductionPlanner uploaded successfully:"+ProplannerMsg);
                } 
                else if (ProplannerMsg.contains("Missing columns in Excel: order_number, lot_number, lot_qty, item_code, item_desc, building_id, target_date, status")) {
                    ExtentReportManager.logFail(" ProductionPlanner upload failed due to missing columns: " + ProplannerMsg);
                    Assert.fail("ProductionPlanner upload failed due to missing columns: " + ProplannerMsg);
                } 
                else {
                    ExtentReportManager.logFail("ProductionPlanner upload failed with unexpected toaster: " + ProplannerMsg);
                    Assert.fail("ProductionPlanner upload failed with unexpected toaster: " + ProplannerMsg);
                }
                //  Ensure old toaster doesn’t affect next step
                openOrdersPage.waitForToasterToDisappear();
		        
		        // Optional: Download PDF/Excel
		        openOrdersPage.downloadProductionPlannerPDF();
		        logger.info(" Production Planner PDF downloaded");
		        Thread.sleep(1000);

		        openOrdersPage.downloadProductionPlannerExcel();
		        logger.info(" Production Planner Excel downloaded");		        
		        Thread.sleep(1500);

		        
		        //RM Planner//
		        
		        logger.info("=== Uploading RM Planner Excel and verifying data ===");
		        ExtentReportManager.logInfo("=== Uploading RM Planner Excel and verifying data ===");
		        openOrdersPage.clickRMPlannerTab();
	            Thread.sleep(1000);
		        logger.info(" Clicked RM Planner tab");

		        // Upload file
		       // openOrdersPage.clickUpload();
                boolean uploaded1 = openOrdersPage.uploadFile(p.getProperty("RmPlannerFile"));
               // Assert.assertTrue(uploaded, "RM Planner file upload failed");
                
                if (!uploaded1) {
                    ExtentReportManager.logFail("RM Planner upload failed: File could not be uploaded.");
                    Assert.fail("RM Planner Upload failed: File could not be uploaded.");
                }
		        logger.info(" RM Planner Excel uploaded");
		        Thread.sleep(1000);
		        
		        String RMplannerMsg = openOrdersPage.getToasterMessage();

                if (RMplannerMsg == null) {
                    ExtentReportManager.logPass("RMPlanner uploaded successfully (no toaster appeared");
                    Assert.assertTrue(true, "RMPlanner uploaded successfully (no toaster appeared).");
                } 
                else if (RMplannerMsg.contains("Excel uploaded successfully. Records created, 3")) {
                    ExtentReportManager.logPass("Excel uploaded successfully. Records created, 3 " + RMplannerMsg);
                    Assert.assertTrue(true, "Production Planner created: 0");
                    logger.info("RMPlanner uploaded successfully:"+RMplannerMsg);
                } 
                else if (RMplannerMsg.contains("Missing columns in Excel: mis_no, io_number, start_date, status_action")) {
                    ExtentReportManager.logFail(" RMPlanner upload failed due to missing columns: " + RMplannerMsg);
                    Assert.fail("RMPlanner upload failed due to missing columns: " + RMplannerMsg);
                } 
                else {
                    ExtentReportManager.logFail("RMPlanner upload failed with unexpected toaster: " + RMplannerMsg);
                    Assert.fail("RMPlanner upload failed with unexpected toaster: " + RMplannerMsg);
                }

                openOrdersPage.waitForToasterToDisappear();

		       // Download PDF/Excel
		         openOrdersPage.downloadRMPlannerPDF();
		        logger.info(" RM Planner PDF downloaded");
		        Thread.sleep(1000);
		        
		        openOrdersPage.downloadRMPlannerExcel();
		        logger.info(" RM Planner Excel downloaded");
		        Thread.sleep(2000);
		    }
}