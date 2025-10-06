package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObject.QShakti_Dashboard;
import PageObject.UserManagementPage;
import PageObject.RoleManagementPage;
import Utilities.DataProviders;
import Utilities.ExtentReportManager;
import testBase.BaseClass;

public class AddRoleTest extends BaseClass {

    @Test(dataProvider = "rolesData", dataProviderClass = DataProviders.class)
    public void testAddRole(String role, String description, String newRole, String newDesc) throws InterruptedException {
        ExtentReportManager.logInfo("Starting test for Role: " + role);

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

            UserManagementPage userPage = new UserManagementPage(driver);
            userPage.navigateToRoleManagement();
            Thread.sleep(1000);
            
            RoleManagementPage rolePage = new RoleManagementPage(driver);
//rolePage.openAddButtonPopup();
          
            // Click ADD Role button
            driver.findElement(By.xpath("//button[normalize-space()='Add Role']")).click();
            ExtentReportManager.logInfo("Clicked on ADD Role button");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary commonAdd css-sghohy-MuiButtonBase-root-MuiButton-root']")).click();
            ExtentReportManager.logInfo("Clicked on ADD button");
            Thread.sleep(1000);

            // Enter Role Name
            WebElement nameInput = driver.findElement(By.xpath("//span[normalize-space(text())='Name']/ancestor::div[contains(@class,'MuiInputBase-root')]//input"));
            nameInput.clear();
            nameInput.sendKeys(role);
            ExtentReportManager.logInfo("Entered Role: " + role);
            Thread.sleep(1000);

            // Enter Description
            WebElement descInput = driver.findElement(By.xpath("//span[normalize-space(text())='Description']/ancestor::div[contains(@class,'MuiInputBase-root')]//input"));
            descInput.clear();
            descInput.sendKeys(description);
            ExtentReportManager.logInfo("Entered Description: " + description);
            Thread.sleep(1000);

            // Click Submit
            driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
            ExtentReportManager.logInfo("Clicked Submit button");

            Thread.sleep(1000);
            
            
            // Verify role added in table
            String tableXpath = String.format(
                    "//div[@class='MuiDataGrid-root MuiDataGrid-root--densityStandard MuiDataGrid-root--noToolbar MuiDataGrid-withBorderColor css-18zjnul-MuiDataGrid-root']",
                    role, description);
            WebElement newRoleRow = driver.findElement(By.xpath(tableXpath));

            Assert.assertTrue(newRoleRow.isDisplayed(), "Role not added in table");
            ExtentReportManager.logPass("Role successfully added and verified: " + role);
            
           /* // Click Edit (pencil icon) for this role
            WebElement editBtn = driver.findElement(By.xpath("//div[@role='rowgroup']//div[1]//div[5]//div[1]//button[1]"));
            editBtn.click();
            ExtentReportManager.logInfo("Clicked Edit for Role: " + role);

            // Update description
            WebElement editroleInput = driver.findElement(By.xpath("//span[normalize-space(text())='Name']/ancestor::div[contains(@class,'MuiInputBase-root')]//input"));
            editroleInput.clear();
            String updatedrole = role + "_updated";
            editroleInput.sendKeys(updatedrole);
            
            WebElement editDescInput = driver.findElement(By.xpath("//span[normalize-space(text())='Description']/ancestor::div[contains(@class,'MuiInputBase-root')]//input"));
            editDescInput.clear();
            String updatedDescription = description + "_updated";
            editDescInput.sendKeys(updatedDescription);*/
            
            rolePage.editRole(newRole, newDesc);
            ExtentReportManager.logPass("Edited role from " + role + " to " + newRole);
            

        } catch (Exception e) {
            ExtentReportManager.logFail("Test failed for Role: " + role + " | Error: " + e.getMessage());
            throw e; 
        }
    }
}
