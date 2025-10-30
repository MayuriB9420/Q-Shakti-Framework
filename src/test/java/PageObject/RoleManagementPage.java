package PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.ExtentReportManager;
import java.time.Duration;

public class RoleManagementPage extends BasePage {

	private WebDriverWait wait;

    // Locators
    By addRoleButton = By.xpath("//button[normalize-space()='Add Role']");
    By AddButton = By.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary commonAdd css-sghohy-MuiButtonBase-root-MuiButton-root']");
    By nameField = By.xpath("//span[normalize-space(text())='Name']/ancestor::div[contains(@class,'MuiInputBase-root')]//input");
    By descriptionField = By.xpath("//span[normalize-space(text())='Description']/ancestor::div[contains(@class,'MuiInputBase-root')]//input");
    By submitButton = By.xpath("//button[normalize-space()='Submit']");
    By  cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    
    public RoleManagementPage(WebDriver driver) {
    	super (driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openAddButtonPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(addRoleButton)).click();
    }

    public void enterRoleDetails(String role, String description) throws InterruptedException {
    	((WebElement) nameField).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) nameField)).sendKeys(role);
        ExtentReportManager.logInfo("Entered Role: " + role);
        Thread.sleep(1000);
        
        ((WebElement) descriptionField).clear();
        ((WebElement) descriptionField).sendKeys(description);
        ExtentReportManager.logInfo("Entered Description: " + description);
        Thread.sleep(1000);
        
        ((WebElement) submitButton).click();
        ExtentReportManager.logInfo("Clicked Submit button");
        Thread.sleep(1000);
    }
    
    public void submitRole() {
        driver.findElement(submitButton).click();
    }

    // Full Add Role Flow
    public void addRole(String role, String description) throws InterruptedException {
        openAddButtonPopup();
        enterRoleDetails(role, description);
      // submitRole();
    }
    
    public void editRole(String newRole, String newDesc) throws InterruptedException {
        By editButton = By.xpath("//div[@role='rowgroup']//div[1]//div[5]//div[1]//button[1]");
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();

        WebElement roleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        roleInput.clear();
        roleInput.sendKeys(newRole);
        Thread.sleep(1000);

        WebElement descInput = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionField));
        descInput.clear();
        descInput.sendKeys(newDesc);
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}

