package TestCases;

import PageObject.LoginPage;
import PageObject.QShakti_Dashboard;
import org.testng.annotations.Test;
import testBase.BaseClass;

public class TC002_Dashboard extends BaseClass{

	 @Test(priority = 1)
	    public void testNavigation() {
		 
		 logger.info("===== Starting Dashboard Navigation Test =====");

	        // 1. Login
	        testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));

	        // 2. Navigate Dashboard menu
	        QShakti_Dashboard dp = new QShakti_Dashboard(driver);
	        
	        dp.clickDashboard();
	        logger.info("Clicked Dashboard");
	        dp.clickQDashboardPage();

	        dp.clickMDM();
	        logger.info("Clicked Master Data Management");
	        dp.clickQDashboardPage();

	        dp.clickOrderManagement();
	        logger.info("Clicked Order Management");
	        dp.clickQDashboardPage();

	        dp.clickRMManagement();
	        logger.info("Clicked RM Management");
	        dp.clickQDashboardPage();

	        dp.clickInprocess();
	        logger.info("Clicked Inprocess Management");
	        dp.clickQDashboardPage();

	        dp.clickFinalInspection();
	        logger.info("Clicked Final Acceptance Inspection");
	        dp.clickQDashboardPage();

	        dp.clickUserSecurity();
	        logger.info("Clicked User & Security Management");
	        dp.clickQDashboardPage();

	        logger.info("===== Dashboard Navigation Test Completed =====");
	    }
	}


