package PageObject;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import Utilities.ExtentReportManager;
public class MDMPage extends BasePage {

	   // WebDriver driver;
	    Logger logger;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    public MDMPage(WebDriver driver) {
			super (driver);

	    }

	    // Sidebar buttons
	    @FindBy(xpath = "//span[normalize-space()='UPLOAD FILE']")
	    WebElement uploadFileMenu;

	    @FindBy(xpath = "//span[normalize-space()='ACTIVITY LOG']")
	    WebElement activityLogMenu;

	    // Tabs
	    @FindBy(xpath = "//button[normalize-space()='Plant Master']")
	    WebElement plantMasterTab;

	    @FindBy(xpath = "//button[normalize-space()='Building Section']")
	    WebElement buildingSectionTab;
	    
	    @FindBy(xpath = "//button[normalize-space()='Machine Master']")
	    WebElement MachineMasterTab;

	    @FindBy(xpath = "//button[normalize-space()='Parameter Master']")
	    WebElement  ParameterMasterTab;
	    
	    @FindBy(xpath = "//button[normalize-space()='Operation Master']")
	    WebElement OperationMasterTab;

	    @FindBy(xpath = "//button[normalize-space()='Inspection Scheduling Master']")
	    WebElement InspectionSchedulingMaster;
	    
	    @FindBy(xpath = "//button[normalize-space()='Item Master']")
	    WebElement  ItemMasterTab;
	   
	    // Upload button
	    @FindBy(xpath = "//label[normalize-space()='Upload']")
	    WebElement uploadButton;
	    
	 // File input 
	    @FindBy(xpath = "//input[@type='file']")
	    WebElement fileInput;
	    
	    // Toaster message
	   // @FindBy(xpath = "//div[@role='alert' and contains(@class,'Toastify__toast-body')]//div")
	    @FindBy(xpath ="//div[@class='Toastify__toast-body']/div[2]")
		public  WebElement toasterMessage;
	    

	    // ---------- Actions ----------

	    public void openUploadFileMenu() {
	        wait.until(ExpectedConditions.elementToBeClickable(uploadFileMenu)).click();
	    }

	    public void openActivityLog() {
	        wait.until(ExpectedConditions.elementToBeClickable(activityLogMenu)).click();
	    }

	   
	    public void clickPlantMaster() {
	    wait.until(ExpectedConditions.elementToBeClickable(plantMasterTab)).click();
	    }

	    public void clickBuildingSection() {
	        wait.until(ExpectedConditions.elementToBeClickable(buildingSectionTab)).click();
	    }
	  
	    public void clickMachineMaster() {
	    	 wait.until(ExpectedConditions.elementToBeClickable(MachineMasterTab)).click();
	    }
	    
	    public void clickItemMaster() {
	        wait.until(ExpectedConditions.elementToBeClickable(ItemMasterTab)).click();
	    }
	    
	    public void clickParameterMaster() {
	        wait.until(ExpectedConditions.elementToBeClickable(ParameterMasterTab)).click();
	    }
	    
	    public void clickOperationMaster() {
	        wait.until(ExpectedConditions.elementToBeClickable(OperationMasterTab)).click();
	    }
	    
	    public void clickInspectionSchedulingMaster() {
	        wait.until(ExpectedConditions.elementToBeClickable(InspectionSchedulingMaster)).click();
	    }
	    
	    
	    public void clickUpload() {
	        wait.until(ExpectedConditions.elementToBeClickable(uploadButton)).click();
	    }
	    
	    // Upload Methods
	    public boolean uploadFile(String relativePath) {
	        try {
	            String absolutePath = getAbsolutePath(relativePath);
	            File file = new File(absolutePath);

	            if (!file.exists()) {
	                ExtentReportManager.logFail(" File not found at: " + absolutePath);
	                return false;
	            }

	            // Unhide the hidden <input type="file">

	            // Now send the file path
	            fileInput.sendKeys(file.getAbsolutePath());
	            ExtentReportManager.logInfo("✅ Uploaded file: " + file.getAbsolutePath());
	            return true;

	        } catch (Exception e) {
	            ExtentReportManager.logFail("❌ File upload failed for: " + relativePath + ". Error: " + e.getMessage());
	            return false;
	        }
	    }

		private String getAbsolutePath(String relativePath) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getToasterMessage() {
			// TODO Auto-generated method stub
			return null;
		}

		public void waitForToasterToDisappear() {
			// TODO Auto-generated method stub
			
		}
	    
}
	   