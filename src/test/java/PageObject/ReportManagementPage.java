package PageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import Utilities.ExtentReportManager;

public class ReportManagementPage extends BasePage {
	
	WebDriverWait wait;
	
		public ReportManagementPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		}
		

	    @FindBy(xpath = "//p[contains(normalize-space(),'In Process Inspection Report')]")
	    WebElement pageTitle;
	    
	    @FindBy(xpath = "//span[normalize-space()='IN PROCESS REPORT']")
	    WebElement InReport;

	    @FindBy(xpath = "//span[normalize-space()='IN PROCESS REPORT']")
	    WebElement FAIreport;
	    
	    @FindBy(xpath = "//span[normalize-space()='IN PROCESS REPORT']")
	    WebElement RMreport;
	    
	    @FindBy(xpath = "//label[contains(normalize-space(),'Section')]/following::input[1]")
	    WebElement sectionDropdown;

	    @FindBy(xpath = "//label[contains(normalize-space(),'Production Order No')]/following::input[1]")
	    WebElement productionOrderDropdown;

	    @FindBy(xpath = "//label[contains(normalize-space(),'Date')]/following::input[1]")
	    WebElement dateDropdown;

	    @FindBy(xpath = "//label[contains(normalize-space(),'Item Code')]/following::input[1]")
	    WebElement itemCodeDropdown;

	    @FindBy(xpath = "//label[contains(normalize-space(),'Operation Name')]/following::input[1]")
	    WebElement operationNameDropdown;

	    @FindBy(xpath = "//label[contains(normalize-space(),'Inspection Parameters')]/following::input[1]")
	    WebElement inspectionParamDropdown;

	    @FindBy(xpath = "//*[contains(text(),'No data found')]")
	    WebElement noDataMessage;

	    
	    public void InProcessReport() {
	        wait.until(ExpectedConditions.elementToBeClickable(InReport)).click();
	        ExtentReportManager.logInfo("InReport");
	    }
	    
	    
	    public void FAIRport() {
	        wait.until(ExpectedConditions.elementToBeClickable(FAIreport)).click();
	        ExtentReportManager.logInfo("FAIreport");
	    }
	    
	    public void RMRport() {
	        wait.until(ExpectedConditions.elementToBeClickable(RMreport)).click();
	        ExtentReportManager.logInfo("RMreport");
	    }

	    public boolean verifyPageLoaded() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOf(pageTitle)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public void selectSection(String section) throws InterruptedException {
	    	 
	    	wait.until(ExpectedConditions.elementToBeClickable(sectionDropdown)).click();

		        sectionDropdown.sendKeys(Keys.CONTROL + "a"); // select all text
		        sectionDropdown.sendKeys(Keys.DELETE);     
			//sectionDropdown.clear();
			sectionDropdown.sendKeys(section);

			WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[contains(@role,'option') and normalize-space()='" + section + "']")));

			option.click();

			wait.until(ExpectedConditions.elementToBeClickable(productionOrderDropdown));
	    	    
	        ExtentReportManager.logInfo("Selected Section Name: " + section);
	    }

	    public void selectProductionOrderNo(String ProductionOrderNo) throws InterruptedException {
	    	wait.until(ExpectedConditions.elementToBeClickable(productionOrderDropdown)).click();

	    	productionOrderDropdown.sendKeys(Keys.CONTROL + "a"); // select all text
	        productionOrderDropdown.sendKeys(Keys.DELETE); 
	    	
	    	//productionOrderDropdown.clear();
			productionOrderDropdown.sendKeys(ProductionOrderNo);

			WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[contains(@role,'option') and normalize-space()='" + ProductionOrderNo + "']")));

			option.click();

			wait.until(ExpectedConditions.elementToBeClickable(itemCodeDropdown));
	        ExtentReportManager.logInfo("Selected ProductionOrder No: " + ProductionOrderNo);
	    }
	    
	    public void selectDate(String Date) throws InterruptedException {
	    	wait.until(ExpectedConditions.elementToBeClickable(dateDropdown)).click();

	    	dateDropdown.sendKeys(Keys.CONTROL + "a"); // select all text
	        dateDropdown.sendKeys(Keys.DELETE); 
	        
			//dateDropdown.clear();
			dateDropdown.sendKeys(Date);

			WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[contains(@role,'option') and normalize-space()='" + Date + "']")));

			option.click();
	        ExtentReportManager.logInfo("Selected Date: " + Date);
	    }

	    public void selectItemCode(String itemCode) throws InterruptedException {
	    	wait.until(ExpectedConditions.elementToBeClickable(itemCodeDropdown)).click();
	    	
	    	itemCodeDropdown.sendKeys(Keys.CONTROL + "a"); // select all text
	        itemCodeDropdown.sendKeys(Keys.DELETE); 
	        
			//itemCodeDropdown.clear();
			itemCodeDropdown.sendKeys(itemCode);

			WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[contains(@role,'option') and normalize-space()='" + itemCode + "']")));

			option.click();

			wait.until(ExpectedConditions.elementToBeClickable(operationNameDropdown));
	        ExtentReportManager.logInfo("Selected Item Code: " + itemCode);
	    }

	    public void selectOperation(String OperationName) throws InterruptedException {
	    	wait.until(ExpectedConditions.elementToBeClickable(operationNameDropdown)).click();

	    	operationNameDropdown.sendKeys(Keys.CONTROL + "a"); // select all text
	        operationNameDropdown.sendKeys(Keys.DELETE); 
	        operationNameDropdown.clear();
	        Thread.sleep(800);
			operationNameDropdown.sendKeys(OperationName);

			WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//li[contains(@role,'option') and normalize-space()='" + OperationName + "']")));

			option.click();

			wait.until(ExpectedConditions.elementToBeClickable(inspectionParamDropdown));
	        ExtentReportManager.logInfo("Selected Operation: " + OperationName);
	    }
	    
	    public void selectInspectionParameter(List<String> InspectionParameter) throws InterruptedException {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        for (String se : InspectionParameter) {
	            try {
	                wait.until(ExpectedConditions.elementToBeClickable(inspectionParamDropdown));

	                inspectionParamDropdown.click();
	                Thread.sleep(800);
	                inspectionParamDropdown.sendKeys(Keys.CONTROL + "a"); // select all text
			        inspectionParamDropdown.sendKeys(Keys.DELETE); 
	                Thread.sleep(800);

	                inspectionParamDropdown.sendKeys(se);
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
	        
	        inspectionParamDropdown.click();      
	        ExtentReportManager.logInfo("Selected Inspection Parameter: " + InspectionParameter);
	    }
	    
	  
	}


