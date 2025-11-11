package PageObject;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import Utilities.ExtentReportManager;

public class Dashboard1Page  extends BasePage{

	    public Dashboard1Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // ====== Locators ======
	    @FindBy(xpath = "//span[normalize-space()='DASHBOARD 1']")
	    WebElement dashboard1Tab;
	    
	    @FindBy(xpath = "//span[normalize-space()='DASHBOARD 2']")
	    WebElement dashboard2Tab;
	    
	    @FindBy(xpath = "//button[normalize-space()='Inprocess']")
	    WebElement InprocessButton;
	    
	    @FindBy(xpath = "//button[normalize-space()='Raw Material']")
	    WebElement RMButton;
	    
	    @FindBy(xpath = "//label[contains(normalize-space(),'Section Name')]/following::input[1]")
	    WebElement sectionNameInput;

	    @FindBy(xpath = "//label[contains(normalize-space(),'Operation')]/following::input[1]")
	    WebElement operationInput;

	    @FindBy(xpath = "//label[contains(normalize-space(),'Item Code')]/following::input[1]")
	    WebElement itemCodeInput;

	    @FindBy(xpath = "//label[contains(normalize-space(),'Inspection Parameters')]/following::input[1]")
	    WebElement inspectionParamInput;

	    @FindBy(xpath = "//label[contains(normalize-space(),'QC-Machine')]/following::input[1]")
	    WebElement qcMachineInput;

	    @FindBy(xpath = "//button[contains(.,'Search') or contains(.,'Apply')]")
	    WebElement searchButton;

	    @FindBy(xpath = "//span[normalize-space()='DASHBOARD 1']")
	    WebElement pageTitle;

	  
	    // ====== Page Actions ======
	    public void openDashboard1() {
	        wait.until(ExpectedConditions.elementToBeClickable(dashboard1Tab)).click();
	        ExtentReportManager.logInfo("Opened Dashboard 1");
	    }
	    
	    
	    public void openDashboard2() {
	        wait.until(ExpectedConditions.elementToBeClickable(dashboard2Tab)).click();
	        ExtentReportManager.logInfo("Opened Dashboard 2");
	    }
	    
	    public void clickInprocessButton() {
	        wait.until(ExpectedConditions.elementToBeClickable(InprocessButton)).click();
	        ExtentReportManager.logInfo("Clicked Inprocess Button");
	    }

	    public void clickRMButton() {
	        wait.until(ExpectedConditions.elementToBeClickable(RMButton)).click();
	        ExtentReportManager.logInfo("Clicked Raw Material Button");
	    }
	    
	    public void selectSectionName(String section) throws InterruptedException {
	        sectionNameInput.click();
	        sectionNameInput.clear();
	        sectionNameInput.sendKeys(section);
	        Thread.sleep(1000);
	        sectionNameInput.sendKeys(Keys.ENTER);
	        ExtentReportManager.logInfo("Selected Section Name: " + section);
	    }

	    public void selectOperation(String operation) throws InterruptedException {
	        operationInput.click();
	        operationInput.clear();
	        operationInput.sendKeys(operation);
	        Thread.sleep(1000);
	        operationInput.sendKeys(Keys.ENTER);
	        ExtentReportManager.logInfo("Selected Operation: " + operation);
	    }

	    public void selectItemCode(String itemCode) throws InterruptedException {
	        itemCodeInput.click();
	        itemCodeInput.clear();
	        itemCodeInput.sendKeys(itemCode);
	        Thread.sleep(1000);
	        itemCodeInput.sendKeys(Keys.ENTER);
	        ExtentReportManager.logInfo("Selected Item Code: " + itemCode);
	    }

	    public void selectInspectionParam(String param) throws InterruptedException {
	        inspectionParamInput.click();
	        inspectionParamInput.clear();
	        inspectionParamInput.sendKeys(param);
	        Thread.sleep(1000);
	        inspectionParamInput.sendKeys(Keys.ENTER);
	        ExtentReportManager.logInfo("Selected Inspection Parameter: " + param);
	    }

	    public void selectQCMachine(String machine) throws InterruptedException {
	        qcMachineInput.click();
	        qcMachineInput.clear();
	        qcMachineInput.sendKeys(machine);
	        Thread.sleep(1000);
	        qcMachineInput.sendKeys(Keys.ENTER);
	        ExtentReportManager.logInfo("Selected QC Machine: " + machine);
	    }

	    public void clickSearchOrApply() {
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
	            ExtentReportManager.logInfo("Clicked Search/Apply button");
	        } catch (Exception e) {
	            ExtentReportManager.logInfo("Search button not present or already applied");
	        }
	    }

	   /* public boolean verifyDashboardLoaded() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOf(pageTitle)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }*/
	    
	    public boolean verifyDashboardLoaded() {
	        try {
	            // Wait until the page title or any unique dashboard element is visible
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	            WebElement titleElement = wait.until(ExpectedConditions.visibilityOf(pageTitle));

	            // Scroll into view in case it's partially off-screen
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", titleElement);
	            Thread.sleep(1000);

	            // Verify it's displayed and text is not empty
	            return titleElement.isDisplayed() && !titleElement.getText().trim().isEmpty();

	        } catch (Exception e) {
	            System.out.println("Dashboard did not load properly: " + e.getMessage());
	            return false;
	        }
	    }

	}



