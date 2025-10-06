package PageObject;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;
import Utilities.ExtentReportManager;


public class UserManagementPage extends BasePage{
	
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    
    //Locators
    
    @FindBy(xpath = "//span[normalize-space()='USER MANAGEMENT']")
    private WebElement userMangt;
    
    @FindBy(xpath = "//span[normalize-space()='ROLE MANAGEMENT']")
    private WebElement RoleMangt;
    
    @FindBy(xpath ="//button[normalize-space()='Create']") 
    WebElement createUserTab;

    @FindBy(xpath = "//input[@name='first_name']") 
    WebElement FirstnameField;

    @FindBy(xpath = "//input[@name='last_name']") 
    WebElement LastnameField;

    @FindBy(xpath= "//input[@name='phone_number']") 
    WebElement MobilenumberField;

    @FindBy(xpath = "//input[@name='email']") 
    WebElement EmailField;

    //@FindBy(xpath = "//div[label[contains(text(),'Plant')]]//input")
    @FindBy(xpath = "//label[contains(normalize-space(.),'Plant')]/following::input[1]") 
    WebElement PlantField;
    
    @FindBy(xpath = "//label[contains(normalize-space(.),'Section')]/following::input[1]") 
    WebElement SectionField;
    
    @FindBy(xpath = "//label[contains(normalize-space(.),'Operations')]/following::input[1]") 
    WebElement OperationsField;
    
    @FindBy(xpath = "//label[contains(normalize-space(.),'QC Machine')]/following::input[1]") 
    WebElement QCmachineField;
    
    @FindBy(xpath = "//label[contains(normalize-space(.),'Assign Role')]/following::input[1]") 
    WebElement AssighnRoleField;
    
    @FindBy(xpath="//button[normalize-space()='SAVE']") 
    WebElement SaveButton;

    @FindBy(xpath ="//div[@class='Toastify__toast-body']/div[2]")
	public  WebElement toasterMessage;
    

    public UserManagementPage(WebDriver driver) {
		super (driver);

    }

    public void navigateToUserManagement() {
        wait.until(ExpectedConditions.elementToBeClickable(userMangt)).click();
        ExtentReportManager.logInfo("Navigated to User and Security Management");
    }
    
    public void navigateToRoleManagement() {
        wait.until(ExpectedConditions.elementToBeClickable(RoleMangt)).click();
        ExtentReportManager.logInfo("Navigated to role Management");
    }
    

    public void openCreateUser() {
        wait.until(ExpectedConditions.elementToBeClickable(createUserTab)).click();
        ExtentReportManager.logInfo("Opened Create User form");
    }
    
    public void selectRole1(String role) throws InterruptedException {
        // Example: selecting a role from dropdown/table
        By roleDropdown = By.xpath("//button[@title='Open']//*[name()='svg']");
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(roleDropdown));
        dropdown.click();
        Thread.sleep(1000);
        WebElement option = driver.findElement(By.xpath("//input[@placeholder='Choose a role']"));
        option.click();
    }
    // Validate if permission checkbox is ticked
    public boolean isPermissionChecked(String screen, String permission) {
        String xpath = "//td[contains(text(),'" + screen + "')]" +
                       "/following-sibling::td//input[@aria-label='" + permission + "']";
        return driver.findElement(By.xpath(xpath)).isSelected();
    }

 /* ---------- Reusable method for MUI combobox ----------
    private void selectFromCombo(WebElement comboInput, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(comboInput)).click();
        comboInput.clear();
        comboInput.sendKeys(value);

        // wait for option and select
        WebElement option = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//li[contains(text(),'" + value + "')]")));
        option.click();

        ExtentReportManager.test.log(Status.INFO, "Selected from combo: " + value);
    }*/
    


    //Actions
    public void enterFirstName(String FirstName) {
        wait.until(ExpectedConditions.visibilityOf(FirstnameField)).sendKeys(FirstName);
        ExtentReportManager.logInfo("Entered First Name: " + FirstName);
    }

    public void enterLastName(String LastName) {
        wait.until(ExpectedConditions.visibilityOf(LastnameField)).sendKeys(LastName);
        ExtentReportManager.logInfo("Entered Last Name: " + LastName);
    }

    public void enterMobileNumber(String PhoneNumber) {
        wait.until(ExpectedConditions.visibilityOf(MobilenumberField)).sendKeys(PhoneNumber);
        ExtentReportManager.logInfo("Entered Phone number: " + PhoneNumber);
    }
        
    public void enterEmail(String Email) {
        wait.until(ExpectedConditions.visibilityOf(EmailField)).sendKeys(Email);
        ExtentReportManager.logInfo("Entered valid Email: " + Email);
    }
        
    public void selectPlant1(String plantName) {
      
        PlantField.clear();
        PlantField.sendKeys(plantName);

        // Wait for dropdown to appear if needed (for MUI combobox)
        // Press ENTER to select the first matching option
        PlantField.sendKeys(Keys.ENTER);
    }

    public String getSelectedPlant() {
        return (PlantField).getAttribute("value");
    }
    
   /* public void selectSection(String sectionName) {
        SectionField.clear();
        SectionField.sendKeys(sectionName);
        SectionField.sendKeys(Keys.ENTER);
    }*/

   /* public void selectPlant(String plantName) {
        if (plantName != null && !plantName.trim().isEmpty()) {
            String cleanPlant = plantName.replaceAll("\\s+", " ").replace("\n", "").trim();
            selectDropdownByLabel("Plant", cleanPlant);
        }
    }
*/
    private void selectMUIComboBox(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
        element.sendKeys(Keys.ENTER);
    }


        public void selectSection(String sectionData) {
        	
        	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        	    // 1️⃣ Click the dropdown arrow to open
        	    WebElement sectionArrow = driver.findElement(By.xpath(
        	        "//label[contains(normalize-space(.),'Section')]/following::button[contains(@class,'MuiAutocomplete-popupIndicator')][1]"
        	    ));
        	    sectionArrow.click();

        	    // 2️⃣ Wait for the listbox to appear
        	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='listbox']")));

        	    WebElement selectAll = wait.until(ExpectedConditions.elementToBeClickable(
				    By.xpath("//ul[@role='listbox']//li//*[contains(normalize-space(.),'Select All')]")
				));
				selectAll.click();
				return;
        	}

        



    // Section
    @FindBy(xpath = "//label[contains(normalize-space(.),'Section')]/following::input[1]")
    private WebElement sectionInput;

    @FindBy(xpath = "//label[contains(normalize-space(.),'Section')]/following::button[contains(@class,'MuiAutocomplete-popupIndicator')][1]")
    private WebElement sectionArrow;


  /*  public void selectSection(String sectionData) {
        for (String section : cleanAndSplitValues(sectionData)) {
            selectDropdownByLabel("Section", section);
        }
        ExtentReportManager.logInfo("Selected Section(s): " + sectionData);
    }
   
    // --- Operation ---
    public void selectOperation(String operationData) {
        for (String operation : cleanAndSplitValues(operationData)) {
            selectDropdownByLabel("Operations", operation);
        }
        ExtentReportManager.logInfo("Selected Operation(s): " + operationData);
        
    }            

    // --- QC Machine ---
    public void selectQcMachine(String machineData) {
        for (String machine : cleanAndSplitValues(machineData)) {
            selectDropdownByLabel("QC Machine", machine);
        }
        ExtentReportManager.logInfo("Selected Machine(s): " + machineData);

    }       
    */
    public void selectRole(String Role) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Role']/following::div[1]")));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + Role.trim() + "']")));
        option.click();
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(SaveButton)).click();
        ExtentReportManager.logInfo("Clicked Save button");
    }

    public String getUserToasterMessage() {
        try {
            WebElement toaster = wait.until(ExpectedConditions.visibilityOf(toasterMessage));
            String msg = toaster.getText().trim();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class='Toastify__toast-body']/div[2]")));
            ExtentReportManager.logInfo("Toaster message: " + msg);
            return msg;
        } catch (Exception e) {
            ExtentReportManager.logInfo("No toaster appeared.");
            return null;
        }
    }
    
    public void waitForToasterToDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(toasterMessage));
        } catch (Exception e) {
            ExtentReportManager.logInfo("Toaster did not disappear within timeout, continuing...");
        }
    }

}