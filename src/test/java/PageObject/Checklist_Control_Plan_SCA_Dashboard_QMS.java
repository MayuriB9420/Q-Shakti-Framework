package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checklist_Control_Plan_SCA_Dashboard_QMS extends BasePage{

	public Checklist_Control_Plan_SCA_Dashboard_QMS(WebDriver driver) {
		super(driver);	
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath = "//ul[@class='slide-menu']//a[normalize-space()='Checklist']")
	WebElement checklistBtn;
	
	@FindBy(xpath = "//ul[@class='slide-menu']//a[normalize-space()='Process Work Flow']")		
	WebElement processWorkFlowBtn;
	
	
	@FindBy(xpath = "//button[@id='checklist-create-button']")
	WebElement createChecklist;
	
	@FindBy(xpath = "//ul[@role='listbox']//li")
	WebElement checklistTypeDropdown;
	
	@FindBy(xpath = "//input[@placeholder='Enter Checklist Name']")
	WebElement checklistNameTxtbox;
	
	@FindBy(xpath = "//input[@placeholder='Select Section']")
	WebElement sectionDropdown;
	
	
	@FindBy(xpath = "//input[@placeholder='Select Department']")
	WebElement departmentDropdown;
	
	@FindBy(xpath = "//input[@placeholder='Select Product']")
	WebElement productDropdown;
	
	
	@FindBy(xpath = "//input[@placeholder='Select Working Station']")
	WebElement workingStationDropdown;
	
	@FindBy(xpath = "//input[@placeholder='Enter Category *']")
	WebElement categoryTxtbox;
	
	
	
	@FindBy(xpath = "//button[@id='save-checklist-button']")
	WebElement saveChecklistButton;
	
	@FindBy(xpath = "//button[@id='checklist-create-cancel-button']")
	WebElement cancelChecklistButton;
	
	@FindBy(xpath = "//button[@id='add-row-button-0']")
	WebElement addRowButton;
	
	@FindBy(xpath = "//ul[@role='listbox']//li'")
	WebElement selectChecklistDropdown;
	
	
	//Action Methods
	
	public void clickChecklist() {
		js.executeScript("arguments[0].click();", checklistBtn);
	}
	
	public void clickProcessWorkFlow() {
		js.executeScript("arguments[0].click();", processWorkFlowBtn);
	}
	
	public void clickCreateChecklist() {
		js.executeScript("arguments[0].click();", createChecklist);
	}
	
	public void categoryDropdown(String category) {
		js.executeScript("arguments[0].click();", checklistTypeDropdown);
	    wait.until(ExpectedConditions.visibilityOf(checklistTypeDropdown));
	    checklistTypeDropdown.sendKeys(category);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@role='listbox']//li[normalize-space()='" + category + "']")));
	    option.click();
	}
	
	public void enterChecklistName(String checklistName) {
	    js.executeScript("arguments[0].click();", checklistNameTxtbox);
	    wait.until(ExpectedConditions.visibilityOf(checklistNameTxtbox));
	    checklistNameTxtbox.clear();
	    checklistNameTxtbox.sendKeys(checklistName);
	}

	
	public void selectSectionDropdown(String section) {
	    js.executeScript("arguments[0].click();", sectionDropdown);
	    wait.until(ExpectedConditions.visibilityOf(sectionDropdown));
	    sectionDropdown.sendKeys(section);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@role='listbox']//li[normalize-space()='" + section + "']")));
	    option.click();
	}

	
	public void selectDepartmentDropdown(String dept) {
		js.executeScript("arguments[0].click();", departmentDropdown);
	    wait.until(ExpectedConditions.visibilityOf(departmentDropdown));
	    departmentDropdown.sendKeys(dept);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@role='listbox']//li[normalize-space()='" + dept + "']")));
	    option.click();
		}
	
	public void selectProductDropdown(String product) {
		js.executeScript("arguments[0].click();", productDropdown);
	    wait.until(ExpectedConditions.visibilityOf(productDropdown));
	    productDropdown.sendKeys(product);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@role='listbox']//li[normalize-space()='" + product + "']")));
	    option.click();
			
	}

	public void selectWorkingStationDropdown(String WorkStation) {
		js.executeScript("arguments[0].click();", workingStationDropdown);
	    wait.until(ExpectedConditions.visibilityOf(workingStationDropdown));
	    workingStationDropdown.sendKeys(WorkStation);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@role='listbox']//li[normalize-space()='" + WorkStation + "']")));
	    option.click();	
	}
	
	public void enterCategory(String category) {
		js.executeScript("arguments[0].click();", categoryTxtbox);
	    wait.until(ExpectedConditions.visibilityOf(categoryTxtbox));
	    categoryTxtbox.clear();
	    categoryTxtbox.sendKeys(category);
		}
	
	public void clickSaveChecklistButton() {
		wait.until(ExpectedConditions.elementToBeClickable(saveChecklistButton)).click();	
	}
	
	public void clickCancelChecklistButton() {
		wait.until(ExpectedConditions.elementToBeClickable(cancelChecklistButton)).click();	
	}
	
	public void clickAddRowButton() {
		wait.until(ExpectedConditions.elementToBeClickable(addRowButton)).click();	
	}
}





