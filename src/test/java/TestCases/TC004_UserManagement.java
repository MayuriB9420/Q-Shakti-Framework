package TestCases;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
//import com.aventstack.extentreports.Status;
import PageObject.UserManagementPage;
import PageObject.QShakti_Dashboard;
import testBase.BaseClass;
import Utilities.ExtentReportManager;

//@Listeners(Utilities.ExtentReportManager.class)
public class TC004_UserManagement extends BaseClass {

    @Test(dataProvider = "CreateUserData", dataProviderClass = Utilities.DataProviders.class)
    public void testCreateUser(String FirstName, String LastName, String Phone, String Email,String EmpId,
    		List<String> Section, List<String> Operation, List<String> Machine, String Role) {
        try {
        	
        	//  Login
        	testLoginFunctionality(p.getProperty("email"), p.getProperty("password"));
            logger.info("Login successful");
            ExtentReportManager.logInfo("Login successful");
            
            QShakti_Dashboard dp = new QShakti_Dashboard(driver);
            dp.clickUserSecurity();
            ExtentReportManager.logInfo("Clicked User and Security Management");

	        logger.info("Clicked User and Security Management");
            Thread.sleep(1000);

            // Navigate to User Management → Create User
            UserManagementPage userPage = new UserManagementPage(driver);
            userPage.navigateToUserManagement();
            Thread.sleep(1500);
            
            String Plant=p.getProperty("PlantName");
            userPage.selectPlant(Plant);
            logger.info("Selected Plant: " + Plant);
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
            Thread.sleep(1500);
            
            userPage.enterEmpId(EmpId);
            ExtentReportManager.logInfo("Entered EmployeeIdField: " + EmpId);
            Thread.sleep(1500);

          // Plant must selected first
          //  userPage.selectPlant1(Plant);
          // logger.info("Selected Section(s): " + Plant);
          // Thread.sleep(2000);

            // Section, Operation, Machine (comma-separated strings)
           userPage.selectSection(Section);
            logger.info("Selected Section(s): " + Section);
            ExtentReportManager.logInfo("Selected Section(s): " + Section);
            Thread.sleep(1500);
            
            userPage.selectOperation(Operation);
            logger.info("Selected Operation(s): " + Operation);
            ExtentReportManager.logInfo("Selected Operation(s): " + Operation);
            Thread.sleep(1500);

            userPage.selectQCMachine(Machine);
            logger.info("Selected Machine(s): " + Machine);
            ExtentReportManager.logInfo("Selected Machine(s): " + Machine);
            Thread.sleep(1500);

            // Assign role
            userPage.selectRole(Role);
            ExtentReportManager.logInfo("Selected Role:"+Role);
            Thread.sleep(1000);

            // Save user
            userPage.clickSave();
            ExtentReportManager.logInfo("Clicked Save");
            Thread.sleep(1000);

            // Verify toaster message
        /* String toasterMsg2 = userPage.getUserToasterMessage();
         Assert.assertNotNull(toasterMsg2, "Toaster message not displayed!");
         Assert.assertTrue(
             toasterMsg2.contains("User created successfully") || toasterMsg2.contains("created"),
             "Unexpected toaster message: " + toasterMsg2 );

         ExtentReportManager.logPass("User created successfully: " + toasterMsg2);
         logger.info("User created successfully: " + toasterMsg2);
         */
            
         // Download PDF/Excel
         userPage.downloadUserMgtPDF();
        logger.info(" User Mgt PDF downloaded");
        Thread.sleep(1000);
        
        
        userPage.downloadUserMgtExcel();
        logger.info(" User Mgt Excel downloaded");
        Thread.sleep(2000);

        } catch (Exception e) {
            ExtentReportManager.logFail("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
       
    }
}