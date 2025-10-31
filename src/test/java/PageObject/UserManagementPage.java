package PageObject;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import com.aventstack.extentreports.Status;
import Utilities.ExtentReportManager;

public class UserManagementPage extends BasePage{
	
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    
    //Locators
    @FindBy(xpath = "//span[normalize-space()='USER MANAGEMENT']")
    private WebElement userMangt;
    
    @FindBy(xpath = "//span[normalize-space()='ROLE MANAGEMENT']")
    private WebElement RoleMangt;
    
    @FindBy(xpath ="//button[normalize-space()='Create']") 
    WebElement createUserTab;
    
    @FindBy(xpath ="//input[@data-testid='custom-autocomplete-input']")
    WebElement PlantDropdown;

    @FindBy(xpath = "//input[@name='first_name']") 
    WebElement FirstnameField;

    @FindBy(xpath = "//input[@name='last_name']") 
    WebElement LastnameField;

    @FindBy(xpath= "//input[@name='phone_number']") 
    WebElement MobilenumberField;

    @FindBy(xpath = "//input[@name='email']") 
    WebElement EmailField;
    
    @FindBy(xpath = "//input[@name='emp_id']")
    WebElement EmployeeIdField;

    // Section
    @FindBy(xpath = "//label[contains(normalize-space(.),'Section')]/following::input[1]")
    private WebElement sectionInput;

    @FindBy(xpath = "//label[contains(normalize-space(.),'Section')]/following::button[contains(@class,'MuiAutocomplete-popupIndicator')][1]")
    private WebElement sectionArrow;
    
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
    
    @FindBy(xpath = "//button[@aria-label='Download User Management Report.pdf']")
    private WebElement UserMgtReportDownloadPDF;

    @FindBy(xpath = "//button[@aria-label='Download User Management Report.xlsx']")
    private WebElement UserMgtReportDownloadEXCEL;
    

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
    
    public void downloadUserMgtPDF() {
        try {
         safeClick(UserMgtReportDownloadPDF);
        
        } catch (Exception e) {
            System.err.println("Failed to click User Mgt PDF: " + e.getMessage());
        }
    }

    /** Click on RM Planner Excel Download */
    public void downloadUserMgtExcel() {
        try {
        safeClick(UserMgtReportDownloadEXCEL);
        } catch (Exception e) {
            System.err.println("Failed to click User Mgt Excel: " + e.getMessage());
        }
    }
    
    
    private void safeClick(WebElement element) {
        try {
            // Wait for Toast to disappear
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> toasts = driver.findElements(By.xpath("//div[contains(@class,'Toastify__toast-container')]"));
            if (!toasts.isEmpty()) {
                for (WebElement toast : toasts) {
                    wait.until(ExpectedConditions.invisibilityOf(toast));
                }
            }
            // Try normal click first
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        } catch (ElementClickInterceptedException e) {
            // Fallback: click using JavaScript if toast or animation still blocks it
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            System.err.println("Click failed: " + ex.getMessage());
        }
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
    
    public void enterEmpId(String EmpId) {
        wait.until(ExpectedConditions.visibilityOf(EmployeeIdField)).sendKeys(EmpId);
        ExtentReportManager.logInfo("Entered valid EmployeeIdField: " + EmpId);
    }
    
        

    public void selectSection(List<String> sectionData) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (String se : sectionData) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(sectionInput));

                sectionInput.click();
                Thread.sleep(800);

                sectionInput.sendKeys(se);
                Thread.sleep(1000);

                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//ul[@role='listbox']//li[normalize-space()='" + se+ "']")
                ));
                option.click();
                Thread.sleep(800);

                System.out.println(" Selected: " + se);
            } catch (Exception e) {
                System.out.println(" Option not found or not clickable for: " + se);
            }
            
        }
        
        sectionInput.click();        
    }
    
    
    public void selectOperation(List<String> operationData) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		for (String op : operationData) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(OperationsField));

				OperationsField.click();
				Thread.sleep(800);

				OperationsField.sendKeys(op);
				Thread.sleep(1000);

				WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//ul[@role='listbox']//li[normalize-space()='" + op + "']")
				));
				option.click();
				Thread.sleep(800);

				System.out.println(" Selected: " + op);
			} catch (Exception e) {
				System.out.println(" Option not found or not clickable for: " + op);
			}
		}
		OperationsField.click();
	}
    
    public void selectQCMachine(List<String> machineData) throws InterruptedException {
    			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    			
    	
    			for (String mc : machineData) {
					try {
						wait.until(ExpectedConditions.elementToBeClickable(QCmachineField));
		
						QCmachineField.click();
						Thread.sleep(800);
		
						QCmachineField.sendKeys(mc);
						Thread.sleep(1000);
		
						WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//ul[@role='listbox']//li[normalize-space()='" + mc + "']")
						));
						option.click();
						Thread.sleep(800);
		
						System.out.println(" Selected: " + mc);
					} catch (Exception e) {
						System.out.println(" Option not found or not clickable for: " + mc);
					}
				}	
    	QCmachineField.click();
    }
    
   /* public void selectRole(String Role) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Role']/following::div[1]")));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + Role.trim() + "']")));
        option.click();
    }*/
    
    public void selectRole(String Role) {
 	   
        wait.until(ExpectedConditions.elementToBeClickable(AssighnRoleField)).click();
        
        AssighnRoleField.clear();
        AssighnRoleField.sendKeys(Role);

        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//li[contains(@role,'option') and normalize-space()='" + Role + "']")));

        option.click();
        wait.until(ExpectedConditions.elementToBeClickable(SaveButton));
     }

    public void selectPlant(String Plant) {
  	   
        wait.until(ExpectedConditions.elementToBeClickable(PlantDropdown)).click();
        
        PlantDropdown.clear();
        PlantDropdown.sendKeys(Plant);

        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//li[contains(@role,'option') and normalize-space()='" + Plant + "']")));

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