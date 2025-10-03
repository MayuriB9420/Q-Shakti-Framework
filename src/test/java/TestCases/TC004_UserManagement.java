package TestCases;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import com.aventstack.extentreports.Status;
import PageObject.UserManagementPage;
import PageObject.QShakti_Dashboard;
import testBase.BaseClass;
import Utilities.DataProviders;
import Utilities.ExtentReportManager;


//@Listeners(Utilities.ExtentReportManager.class)
public class TC004_UserManagement extends BaseClass {

    @Test(dataProvider = "CreateUserData", dataProviderClass = Utilities.DataProviders.class)
    public void testCreateUser(String FirstName, String LastName, String Phone, String Email,
            String Plant, String[] Sections, String[] Operations,
            String[]  Machines, String Role) {
        try {
        	
        	// Step 1: Login
        	testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
            logger.info("Login successful");
            ExtentReportManager.logInfo("Login successful");
            
            QShakti_Dashboard dp = new QShakti_Dashboard(driver);
            dp.clickUserSecurity();
            ExtentReportManager.logInfo("Clicked User and Security Management");

	        logger.info("Clicked User and Security Management");
            Thread.sleep(1000);

            // Step 2: Navigate to User Management → Create User
            UserManagementPage userPage = new UserManagementPage(driver);
            userPage.navigateToUserManagement();
            Thread.sleep(1000);
            userPage.openCreateUser();
            logger.info("Opened Create User form");
            Thread.sleep(1000);


            ExtentReportManager.logInfo("Starting Create User Test with data: " + FirstName);

            userPage.enterFirstName(FirstName);
            ExtentReportManager.logInfo("Entered Last Name: " + FirstName);
            Thread.sleep(1000);

            userPage.enterLastName(LastName);
            ExtentReportManager.logInfo("Entered Last Name: " + LastName);
            Thread.sleep(1000);

            userPage.enterMobileNumber(Phone);
            ExtentReportManager.logInfo("Entered Mobile Number: " + Phone);
            Thread.sleep(1000);

            userPage.enterEmail(Email);
            ExtentReportManager.logInfo("Entered Email: " + Email);
            Thread.sleep(1000);

            // Plant must be selected first
            //userPage.selectPlant(Plant);
           // Thread.sleep(1000);

           // Dependent dropdowns
            for (String sec : Sections) {
                userPage.selectSection(sec);
                logger.info("Selected Section: " + sec);
                ExtentReportManager.logInfo("Selected Section: " + Sections);
                Thread.sleep(1000);
            }

            // Select multiple operations
            for (String op : Operations) {
                userPage.selectOperation(op);
                logger.info("Selected Operation: " + op);
                ExtentReportManager.logInfo("Selected Opeartions: " + Operations);
                Thread.sleep(1000);
            }

            // Select multiple machines
            for (String mac : Machines) {
                userPage.selectQcMachine(mac);
                logger.info("Selected Machine: " + mac);
                ExtentReportManager.logInfo("Selected Machines: " + Machines);
                Thread.sleep(1000);
            }

            // Assign role
            userPage.selectRole(Role);
            ExtentReportManager.logInfo("Selected Role:"+Role);
            Thread.sleep(1000);

            // Save user
            userPage.clickSave();
            ExtentReportManager.logInfo("Clicked Save");
            Thread.sleep(1000);


            // Verify toaster message
         String toasterMsg2 = userPage.getUserToasterMessage();
         Assert.assertNotNull(toasterMsg2, "Toaster message not displayed!");
         Assert.assertTrue(
             toasterMsg2.contains("User created successfully") || toasterMsg2.contains("created"),
             "Unexpected toaster message: " + toasterMsg2 );

         ExtentReportManager.logPass("User created successfully: " + toasterMsg2);

        } catch (Exception e) {
            ExtentReportManager.logFail("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}

