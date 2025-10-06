package PageObject;
import java.time.Duration;
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
    @FindBy(xpath = "//input[@role='combobox' and @value='AMMUNITION FACTORY KHADKI']") 
    WebElement PlantField;
    
    @FindBy(xpath = "//input[@label='Section']") 
    WebElement SectionField;
    
    @FindBy(xpath = "//input[@label='Operations']") 
    WebElement OperationsField;
    
    @FindBy(xpath = "//input[@label='QC Machine']") 
    WebElement QCmachineField;
    
    @FindBy(xpath = "//input[@label='Assign Role']") 
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
    
 // ================== NEW Helper for Multi-Select ==================
    private void selectFromMuiMultiSelect(String label, String... values) {
        // Locate the input field (combobox) based on the label text
        WebElement comboBox = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//label[contains(text(),'" + label + "')]/following::input[@role='combobox'][1]")
        ));
        comboBox.click();  // open dropdown

        for (String value : values) {
            String trimmed = value.trim();

            // Handle "Select All"
            if (trimmed.equalsIgnoreCase("Select All")) {
                WebElement selectAllOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[normalize-space(text())='Select All']" +
                             " | //li//span[normalize-space(text())='Select All']")
                ));
                selectAllOption.click();
            } else {
                // Handle normal items
                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[normalize-space(text())='" + trimmed + "']" +
                             " | //li//span[normalize-space(text())='" + trimmed + "']")
                ));
                option.click();
            }
        }
        
        // optional: close dropdown
        comboBox.sendKeys(Keys.ESCAPE);
    }

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
        
   /* public void selectPlant(String plantName) {
        // If default value is already present (read-only), skip
        String currentValue = PlantField.getAttribute("value").trim();
        if (!currentValue.equalsIgnoreCase(plantName)) {
            selectFromCombo(PlantField, plantName);
        }
        ExtentReportManager.test.log(Status.INFO, "Plant Selected: " + plantName);
    }*/
    
    public void selectPlant(String plant) {
        // Plant seems pre-filled, so probably no action needed
        System.out.println("Plant defaulted as: " + plant);
    }

        // --- Dependent dropdowns ---
    /*public void selectSection(String section) {
        selectFromCombo(SectionField, section);
    }

    public void selectOperation(String operation) {
        selectFromCombo(OperationsField, operation);
    }

    public void selectQcMachine(String machine) {
        selectFromCombo(QCmachineField, machine);
    }
*/
 // --- Section ---
    
    public void selectSection(String sectionData) {
        if (sectionData != null && !sectionData.trim().isEmpty()) {
            selectFromMuiMultiSelect("Sections", sectionData.split(","));
            ExtentReportManager.logInfo("Selected Section: " + sectionData);
        }
    }

    // --- Operation ---
    public void selectOperation(String operationData) {
        if (operationData != null && !operationData.trim().isEmpty()) {
            selectFromMuiMultiSelect("Operations", operationData.split(","));
            ExtentReportManager.logInfo("Selected Operation: " + operationData);
            
        }
    }            

    // --- QC Machine ---
    public void selectQcMachine(String machineData) {
        if (machineData != null && !machineData.trim().isEmpty()) {
            selectFromMuiMultiSelect("Machines", machineData.split(","));
            ExtentReportManager.logInfo("Selected Machine: " + machineData);

        }
    }       
    
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

