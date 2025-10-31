package TestCases;

import org.testng.annotations.Test;
import PageObject.ProductionOrdersPage;
import PageObject.QShakti_Dashboard;
import Utilities.ExtentReportManager;
import testBase.BaseClass;

public class TC008_ProductionOrders extends BaseClass {
	
	@Test(dataProvider = "ProductionOrders", dataProviderClass = Utilities.DataProviders.class)
    public void verifyCompleteButtonClick(String order_number, String lot_number, String lot_qty, String item_code,
                                          String item_desc, String building_id, String target_date, String status,
                                          String plant_id, String customer_name) throws InterruptedException {

		 String[] targetOrders = {"1001"};  // Add the specific order numbers you want

	        // Skip orders not in targetOrders
	        boolean shouldClick = false;
	        for (String target : targetOrders) {
	            if (order_number.equals(target) && status.equalsIgnoreCase("Complete")) {
	                shouldClick = true;
	                break;
	            }
	        }

	        if (!shouldClick) {
	            logger.info("Skipping Order: " + order_number + " (not targeted or status not Complete)");
	            return;
	        }

        logger.info("=== Starting Test for Order: " + order_number + " ===");
		ExtentReportManager.logInfo("=== Starting Test for Order: " + order_number + " ===");
		
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

        ProductionOrdersPage productionPage = new ProductionOrdersPage(driver);
        productionPage.clickProductionOrdersPage();
        ExtentReportManager.logInfo("Clicked Production Orders tab");
        logger.info("Clicked Production Orders tab");
        Thread.sleep(1000);
        
        // Click Complete button for specific order number
        productionPage.clickCompleteButtonForProductionOrder(order_number);
        logger.info("Clicked Complete button for order: " + order_number);
        Thread.sleep(1000);
        
        productionPage.clickConfirmCompleteOrder();
        logger.info("Clicked Confirm Complete for order: " + order_number);
		Thread.sleep(1000);
	    ExtentReportManager.logInfo("Clicked Confirm Complete for order: " + order_number);
	    
		 // Click close button for specific order number
	    productionPage.clickcloseButtonForProductionOrder(order_number);
        logger.info("Clicked Close button for order: " + order_number);
        Thread.sleep(1000);
        
        productionPage.clickConfirmCloseOrder();
        logger.info("Clicked Confirm close for order: " + order_number);
		Thread.sleep(1000);
	    ExtentReportManager.logInfo("Clicked Confirm Close for order: " + order_number);    
	    
        // Click start button for specific order number
	    productionPage.clickstartButtonForProductionOrder(order_number);
        logger.info("Clicked Start button for order: " + order_number);
        Thread.sleep(1000);
        
        productionPage.clickConfirmstartOrder();
        logger.info("Clicked Confirm Start for order: " + order_number);
		Thread.sleep(1000);
	    ExtentReportManager.logInfo("Clicked Confirm Start for order: " + order_number);
	    
	    // Validate toast message (if button was clickable)
      /*  try {
            String toastMessage = productionPage.getToasterMessage();
            logger.info("Toast Message: " + toastMessage);
            ExtentReportManager.logPass("Toast message: " + toastMessage);

            Assert.assertTrue(toastMessage.toLowerCase().contains("success")
                    || toastMessage.toLowerCase().contains("completed"),
                    "Toast message not confirming completion!");
        } catch (Exception e) {
            logger.warn("No toast message appeared (possibly already completed): " + order_number);
            ExtentReportManager.skip("Order " + order_number + " skipped (already completed or unavailable).");
        }
*/
        logger.info("=== Test Completed for Order: " + order_number + " ===");
    }

}
