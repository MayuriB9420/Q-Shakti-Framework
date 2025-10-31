package PageObject;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.ExtentReportManager;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RM_InspectionPage extends BasePage{
	
	    WebDriverWait wait;

	    public RM_InspectionPage(WebDriver driver) {
	        super(driver);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }

	    @FindBy(xpath = "//span[normalize-space()='RM INSPECTION']")	    
	    WebElement rmInspectionPage;
	    
	    @FindBy(xpath = "//label[contains(normalize-space(.), 'Section')]/following::input[1]")	    
	    WebElement txtSection;
	    
	    @FindBy(xpath = "//label[contains(normalize-space(.), 'I/O No - Mis No')]/following::input[1]")
	    WebElement txtIONo;

	    @FindBy(xpath = "//span[@data-label='Parameter Wise']")
	    WebElement ParameterWiseTab;

	    @FindBy(xpath = "//label[contains(normalize-space(.), 'QC Machine')]/following::input[1]")
	    WebElement qcMachineInput;

	    
	    @FindBy(xpath = "//label[contains(normalize-space(.), 'QC Machine')]/following::input[1]/li[normalize-space(text())='\" + machine + \"']")
	     List<WebElement> qcMachineOptions;

	    @FindBy(xpath = "//p[normalize-space(text())='Accepted']/following::input[1]")
	    WebElement txtAccepted;

	    @FindBy(xpath = "//p[normalize-space(text())='Rejected']/following::input[1]")
	    WebElement txtRejected;

	    @FindBy(xpath = "//input[@placeholder='Enter remarks']")
	    WebElement txtRemarks;

	    @FindBy(xpath = "//button[normalize-space()='Save & Next Parameter >']")
	    WebElement btnSaveNext;
	    
	    @FindBy(xpath = "//button[normalize-space()='Save']")
	    WebElement btnSave;

	    @FindBy(xpath = "//div[contains(text(),'Data Saved Successfully') or contains(text(),'Success')]")
	    WebElement toastSuccess;

	    
	    public void navigateToRmInspectionPage() {
	    	
	        wait.until(ExpectedConditions.elementToBeClickable(rmInspectionPage)).click();
	        ExtentReportManager.logInfo("Navigated to RM Inspection Management");
	    }
	    
           public void parameterWiseInspectionTab() {
	    	
	        wait.until(ExpectedConditions.elementToBeClickable(ParameterWiseTab)).click();
	        ExtentReportManager.logInfo("Navigated to parameter Wise Inspection Tab");
	    }
	    
	    public void enterSection(String section) {
	        wait.until(ExpectedConditions.elementToBeClickable(txtSection)).click();
	        
	        txtSection.clear();
	        txtSection.sendKeys(section);

	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//li[contains(@role,'option') and normalize-space()='" + section + "']")));

	        option.click();

	        wait.until(ExpectedConditions.elementToBeClickable(txtIONo));
	    }
	    
	    public void enterIO(String IO) {
	    	
	        wait.until(ExpectedConditions.elementToBeClickable(txtIONo)).click();
	        
	        txtIONo.clear();
	        txtIONo.sendKeys(IO);

	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//li[contains(@role,'option') and normalize-space()='" + IO + "']")));

	        option.click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(qcMachineInput));
	    }

	    public void selectQCMachines(List<String> qcMachines) throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        for (String machine : qcMachines) {
	            try {
	                wait.until(ExpectedConditions.elementToBeClickable(qcMachineInput));

	                qcMachineInput.click();
	                Thread.sleep(800);

	                qcMachineInput.sendKeys(machine);
	                Thread.sleep(1000);

	                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//ul[@role='listbox']//li[normalize-space()='" + machine + "']")
	                ));
	                option.click();
	                Thread.sleep(800);

	                System.out.println(" Selected: " + machine);
	            } catch (Exception e) {
	                System.out.println(" Option not found or not clickable for: " + machine);
	            }
	        }
	    }


	    public void enterAccepted(int accepted) {
	        txtAccepted.clear();
	        txtAccepted.sendKeys(String.valueOf(accepted));
	    }

	    public void enterRejected(int rejected) {
	        txtRejected.clear();
	        txtRejected.sendKeys(String.valueOf(rejected));
	    }

	    public void enterRemarks(String remark) {
	        txtRemarks.clear();
	        txtRemarks.sendKeys(remark);
	    }

	    public void clickSaveNext() {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSaveNext);
	        btnSaveNext.click();
	    }
	    
	    public void clickSave() {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSave);
	        btnSave.click();
	    }

	    public boolean isSaveSuccessful() {
	        try {
	            wait.until(ExpectedConditions.visibilityOf(toastSuccess));
	            return toastSuccess.isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	}

