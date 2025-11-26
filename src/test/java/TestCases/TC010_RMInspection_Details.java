package TestCases;

import org.testng.annotations.Test;
import PageObject.QShakti_Dashboard;
import PageObject.RMInspection_DetailsPage;
import Utilities.ExtentReportManager;
import testBase.BaseClass;

public class TC010_RMInspection_Details extends BaseClass {

	@Test(dataProvider = "RMDetails", dataProviderClass = Utilities.DataProviders.class)
	public void testRMInspection(String section, String IO_No) throws InterruptedException {

		// Login
		/*
		  testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
		  logger.info("Login successful");
		  ExtentReportManager.logInfo("Login successful"); Thread.sleep(1000);
		 */

		QShakti_Dashboard dp = new QShakti_Dashboard(driver);
		Thread.sleep(1000);
		dp.clickRMManagement();
		ExtentReportManager.logInfo("Clicked RM Management Module");
		logger.info("Clicked RM Management Module");
		Thread.sleep(500);

		RMInspection_DetailsPage rm = new RMInspection_DetailsPage(driver);
		rm.navigateToRmInspectionDetailPage();
		logger.info("Navigated to RM Inspection Details Page");
		ExtentReportManager.logInfo("Navigated to RM Inspection Details Page");
		Thread.sleep(1000);

		rm.enterSection(section);
		logger.info("Entered Section: " + section);
		Thread.sleep(1000);

		rm.enterIO(IO_No);
		logger.info("Entered IO: " + IO_No);
		Thread.sleep(1500);

		/* rm.enterDate(Date);
		Thread.sleep(1000);

		rm.enterItemCode(Item_code);
		logger.info("Entered Item Code: " + Item_code);
		Thread.sleep(1500);*/

		QShakti_Dashboard dashMenu = new QShakti_Dashboard(driver);
		dashMenu.clickQShaktiimg();
		logger.info("Navigated to QShakti_Dashboard");
		Thread.sleep(1500);

	}
}
