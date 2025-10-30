package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.QShakti_Dashboard;
import PageObject.RM_InspectionPage;
import Utilities.ExtentReportManager;
import testBase.BaseClass;
import java.util.List;

public class TC009_RM_Inspection extends BaseClass {
	
	    @Test(dataProvider = "RMInspectionDataExcel",dataProviderClass = Utilities.DataProviders.class)
	    public void testRMInspection(String section, String IO, List<String> qcMachines, int accepted, int rejected, String remarks) throws InterruptedException {

	    //  Login
        	testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
            logger.info("Login successful");
            ExtentReportManager.logInfo("Login successful");
            
            QShakti_Dashboard dp = new QShakti_Dashboard(driver);
            dp.clickRMManagement();
            ExtentReportManager.logInfo("Clicked RM Management Module");
	        logger.info("Clicked RM Management Module");
            Thread.sleep(1000);
            
	        RM_InspectionPage rm = new RM_InspectionPage(driver);
            rm.navigateToRmInspectionPage();
            ExtentReportManager.logInfo("Navigated to RM Inspection Page");
            Thread.sleep(1000);
            
	        rm.enterSection(section);
            Thread.sleep(1000);
   
	        rm.enterIO(IO);
            Thread.sleep(1000);
            
            rm.parameterWiseInspectionTab();
     	    Thread.sleep(1000);
     	   
	        //rm.selectQCMachines(qcMachines);
            // Thread.sleep(1000);
            
	        rm.enterAccepted(accepted);
            Thread.sleep(1000);

	        rm.enterRejected(rejected);
            Thread.sleep(1000);

	        rm.enterRemarks(remarks);
            Thread.sleep(1000);

	        rm.clickSaveNext();
            Thread.sleep(1000);
            
	        rm.clickSave();
            Thread.sleep(1000);

	        Assert.assertTrue(rm.isSaveSuccessful(), "RM Inspection data not saved successfully!");
	    }

	 
	}

