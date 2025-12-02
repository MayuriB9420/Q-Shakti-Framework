package TestCases;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import com.aventstack.extentreports.reporter.ExtentReporter;
import PageObject.QShakti_Dashboard;
import PageObject.RM_InspectionPage;
import Utilities.ExtentReportManager;
import testBase.BaseClass;

public class TC009_RM_Inspection extends BaseClass {

	@Test
	//public void testRMInspection(String section, String IO, String remarks, String Machine) throws Exception { (dataProvider = "RMInspectionDataExcel", dataProviderClass = Utilities.DataProviders.class)
	
	public void testRMInspection() throws Exception {


		// Login
		/*
		  testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
		  
		  
		  logger.info("Login successful");
		  ExtentReportManager.logInfo("Login successful");
		 */

		int index = 0; // 1st parameter

		QShakti_Dashboard dp = new QShakti_Dashboard(driver);
		dp.clickRMManagement();
		ExtentReportManager.logInfo("Clicked RM Management Module");
			
		logger.info("Clicked RM Management Module");
		Thread.sleep(1000);
		
		RM_InspectionPage rm = new RM_InspectionPage(driver);
		rm.navigateToRmInspectionPage();
		logger.info("Navigated to RM Inspection Page");
		ExtentReportManager.logInfo("Navigated to RM Inspection Page");
		Thread.sleep(1000);
		
		/*rm.enterSection(section);
		logger.info("Entered Section: " + section);
		Thread.sleep(1000);

		rm.enterIO(IO);
		logger.info("Entered IO: " + IO);
		Thread.sleep(1500);*/

		 logger.info("=== Selecting Section & IO ===");
	        rm.enterSection("CASE 4");
	        Thread.sleep(1000);
	        rm.enterIO("1001 - MIS001");
	        Thread.sleep(1000);

	        
	    	rm.parameterWiseInspectionTab();
			logger.info("Clicked Parameter Wise Inspection Tab");
			ExtentReportManager.logInfo("Clicked Parameter Wise Inspection Tab");
			Thread.sleep(1000);

			/*String machine = "FORD VISCO CUP NO.4";
			Thread.sleep(1000);
			
	        String remarks = "Recheck";
	        Thread.sleep(1000);
	        */
			int totalRows = rm.txtSampleSize.size();

	        // Dynamic remarks & machines according to total rows
	        List<String> remarkList = new ArrayList<>();
	        List<String> machineList = new ArrayList<>();

	        for (int i = 0; i < totalRows; i++) {
	            remarkList.add("Recheck");  // or from Excel
	            Thread.sleep(1500);
	            
	            machineList.add(" FORD VISCO CUP NO.4 " + (i + 1)); // or from Exc e
	            Thread.sleep(1500);

	        }

	        rm.fillInspectionRows(remarkList, machineList);

	        logger.info("RM Inspection automation completed successfully.");
	        
		/*QShakti_Dashboard dashMenu = new QShakti_Dashboard(driver);
		dashMenu.clickQShaktiimg();
		logger.info("Navigated to QShakti_Dashboard");
		ExtentReportManager.logInfo("Navigated to QShakti_Dashboard");
		Thread.sleep(1500);
*/
		// Assert.assertTrue(rm.isSaveSuccessful(), "RM Inspection data not saved
		// successfully!");
		}

}