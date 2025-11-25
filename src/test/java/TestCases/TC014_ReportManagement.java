package TestCases;

import java.util.List;
import org.testng.annotations.*;
import testBase.BaseClass;
import PageObject.QShakti_Dashboard;
import PageObject.ReportManagementPage;
import Utilities.ExtentReportManager;

public class TC014_ReportManagement extends BaseClass {

	 
	    @Test(dataProvider = "iData",dataProviderClass = Utilities.DataProviders.class)
	    public void testInProcessInspection(String Section, String ProductionOrderNo, String ItemCode, String OperationName, List<String> InspectionParameter) throws InterruptedException {
	    	
	    	/* testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
	         logger.info("Login successful");
	         ExtentReportManager.logInfo("Login successful");
	         Thread.sleep(1000);*/
	         
	         QShakti_Dashboard dp = new QShakti_Dashboard(driver);
	         dp.clickReportMgt();
	         ExtentReportManager.logInfo("Clicked Report Management Module");
	  	     logger.info("Clicked Report Management Module");
	         Thread.sleep(1000);
	         
	  	     ReportManagementPage rm = new ReportManagementPage(driver);
	         rm.InProcessReport();
	         ExtentReportManager.logInfo("Navigated to Inprocess Report Page");
	         logger.info("Navigated to Inprocess Report Page");
	         Thread.sleep(1000);
	         
	         rm.selectSection(Section);
	         logger.info("Selected Section: " + Section);
	         Thread.sleep(1000);
	         
	         rm.selectProductionOrderNo(ProductionOrderNo);
	         logger.info("Selected Production Order No: " + ProductionOrderNo);
	         Thread.sleep(1000);
	         
	         rm.selectItemCode(ItemCode);
	         logger.info("Selected Item Code: " + ItemCode);
	         Thread.sleep(1000);
	         
	        // rm.selectDate(Date);
	        // Thread.sleep(1000);
	         
	         rm.selectOperation(OperationName);
	         logger.info("Selected Operation: " + OperationName);
	         Thread.sleep(1500);
	         
	         rm.selectInspectionParameter(InspectionParameter);
	         logger.info("Selected Inspection Parameter: " + InspectionParameter);
	         Thread.sleep(2000);
	         
	        /* rm.FAIRport();
	         logger.info("Generated FAI Report");
	         Thread.sleep(1500);

	         rm.selectSection(Section);
	         logger.info("Selected Section: " + Section);
	         Thread.sleep(1000);
	         
	         rm.selectProductionOrderNo(ProductionOrderNo);
	         logger.info("Selected Production Order No: " + ProductionOrderNo);
	         Thread.sleep(1000);
	         
	         rm.selectItemCode(ItemCode);
	         logger.info("Selected Item Code: " + ItemCode);
	         Thread.sleep(1000);
	         
	        // rm.selectDate(Date);
	       //  Thread.sleep(1000);
	         
	         rm.selectOperation(OperationName);
	         logger.info("Selected Operation: " + OperationName);
	         Thread.sleep(1500);
	         
	         rm.RMRport();
	         logger.info("Generated RM Report");
	         Thread.sleep(1500);

	         rm.selectSection(Section);
	         logger.info("Selected Section: " + Section);
	         Thread.sleep(1000);
	         
	         rm.selectProductionOrderNo(ProductionOrderNo);
	         logger.info("Selected Production Order No: " + ProductionOrderNo);
	         Thread.sleep(1000);
	         
	         rm.selectItemCode(ItemCode);
	         logger.info("Selected Item Code: " + ItemCode);
	         Thread.sleep(1000);
	         
	        // rm.selectDate(Date);
	       //  Thread.sleep(1000);
	         
	         rm.selectOperation(OperationName);
	         logger.info("Selected Operation: " + OperationName);
	         Thread.sleep(1500);*/
	           
	         assert rm.verifyPageLoaded() : "Page did not load.";
	         logger.info("Report Management Page loaded successfully");
	         ExtentReportManager.logInfo("Report Management Page loaded successfully");

	         QShakti_Dashboard dashMenu = new QShakti_Dashboard(driver);
	         dashMenu.clickQShaktiimg(); 
	         logger.info("Navigated to QShakti_Dashboard");
	         Thread.sleep(1500);
	    
	    }


}
