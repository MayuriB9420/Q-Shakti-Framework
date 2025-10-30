package PageObject;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checklist_Control_Plan_VAC_Dashboard_QMS extends BasePage{

	public Checklist_Control_Plan_VAC_Dashboard_QMS(WebDriver driver) {
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
	
		@FindBy(xpath = "//button[@id='save-checklist-button']")
		WebElement saveChecklistButton;
		
		@FindBy(xpath = "//button[@id='checklist-create-cancel-button']")
		WebElement cancelChecklistButton;
		
		@FindBy(xpath = "//ul[@role='listbox']//li")
		WebElement selectChecklistDropdown;	
		
		@FindBy(xpath = "//input[@placeholder='Select Type']")
		WebElement selectTypeDropdown;
		
		@FindBy(xpath = "//input[@placeholder='Select Frequency']")
		WebElement selectFrequencyDropdown;
		
		@FindBy(xpath = "//button[@id='delete-category-row-button']")
		WebElement deleteButton;
		
		@FindBy(xpath = "//button[@id='add-category-button']")
		WebElement addCategoryButton;

		
		//Action Methods
		
		public void clickChecklist() {
			js.executeScript("arguments[0].click();", checklistBtn);
		}
		
		public void clickProcessWorkFlow() {
			js.executeScript("arguments[0].click();", processWorkFlowBtn);
		}
		
		public void clickCreateChecklist() {
		    wait.until(ExpectedConditions.elementToBeClickable(createChecklist));
		    js.executeScript("arguments[0].click();", createChecklist);
		    wait.until(ExpectedConditions.invisibilityOf(createChecklist)); // if it disappears after click
		}
		public void clickChecklistTypeDropdown() {
			js.executeScript("arguments[0].click();", checklistTypeDropdown);	
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
		
		public void enterCategory(String category, int categoryIndex) {
	        String id = "category-name-input-" + categoryIndex; // Uses categoryIndex
	        By locator = By.id(id);
	        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
	        wait.until(ExpectedConditions.elementToBeClickable(locator));
	        input.clear();
	        input.sendKeys(category);
	    }

		public void enterCharacteristic(String value, int categoryIndex, int characteristicRowIndex) {
	        String id = "characteristic-input-" + categoryIndex + "-" + characteristicRowIndex;
	        By locator = By.id(id);
	        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

	        wait.until(ExpectedConditions.elementToBeClickable(locator));
	        input.clear();
	        input.sendKeys(value);
	    }
		
		public void selectTypeDropdown(String typeValue, int categoryIndex, int characteristicRowIndex) {
	        String id = "inspection-type-filter-" + categoryIndex + "-" + characteristicRowIndex;
	        By locator = By.id(id);
	        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(locator));
	     
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
	        dropdown.click();
	        
	        // Clicks the option from the listbox (assumed to be a global locator)
	        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//ul[@role='listbox']//li[normalize-space()='" + typeValue + "']")));
	        option.click();
	    }
	    
	   
		public void selectFrequencyDropdown(String frequencyValue, int categoryIndex, int characteristicRowIndex) {
	        String id = "frequency-filter-" + categoryIndex + "-" + characteristicRowIndex;
	        By locator = By.id(id);
	        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
	        dropdown.click();
	        
	        // Clicks the option from the listbox
	        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//ul[@role='listbox']//li[normalize-space()='" + frequencyValue + "']")));
	        option.click();
	    }

		 public void enterSampleSize(String sampleSize, int categoryIndex, int characteristicRowIndex) {
		        String id = "sample-size-input-" + categoryIndex + "-" + characteristicRowIndex;
		        By locator = By.id(id);
		        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		        
		        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
		        wait.until(ExpectedConditions.elementToBeClickable(locator));
		        input.clear();
		        input.sendKeys(sampleSize);
		    }

		 public void clickCTQRadioButton(int categoryIndex, int characteristicRowIndex) {
		        String id = "ctq-checkbox-" + categoryIndex + "-" + characteristicRowIndex;
		        By locator = By.id(id);

		        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

		        if (!checkbox.isSelected()) {
		            try {
		                checkbox.click();
		            } catch (Exception e) {
		                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
		            }
		        }
		    }
		    
	    
		 public void clickAddRowButton(int categoryIndex) {
		        String id = "add-row-button-" + categoryIndex;
		        By locator = By.id(id);
		        
		        // ... (Your robust click logic remains the same) ...
	            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		        int attempts = 0;
	            // ... (rest of the clickAddRowButton logic) ...
	            while (attempts < 3) {
		            try {
		                WebElement addRowButton = localWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		                ((JavascriptExecutor) driver).executeScript(
		                    "arguments[0].scrollIntoView({block: 'center', inline: 'center'});", addRowButton);
		                Actions actions = new Actions(driver);
		                actions.moveToElement(addRowButton).perform();
		                localWait.until(ExpectedConditions.elementToBeClickable(addRowButton));
		                addRowButton.click();
		                System.out.println("Clicked Add Row button successfully for category " + categoryIndex);
		                return;
		            } catch (Exception e) {
		                System.out.println("Attempt " + (attempts + 1) + " failed for Add Row button: " + e.getMessage());
		                attempts++;
		                try { Thread.sleep(500); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
		            }
		        }
		        throw new RuntimeException("Failed to click Add Row button after multiple attempts");
		    }

	    public void clickAddCategoryButton() {
	        wait.until(ExpectedConditions.elementToBeClickable(addCategoryButton)).click();
	    }
	    
		public void clickSaveChecklistButton() {
			wait.until(ExpectedConditions.elementToBeClickable(saveChecklistButton)).click();	
		}
		
		public void clickCancelChecklistButton() {
			wait.until(ExpectedConditions.elementToBeClickable(cancelChecklistButton)).click();	
		}
	
		
		public void waitForNewRowToAppear(int expectedRowCount) {
		    By rowsLocator = By.xpath("//table//tr");

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    wait.pollingEvery(Duration.ofMillis(300));

		    wait.until(driver -> {
		        List<WebElement> rows = driver.findElements(rowsLocator);
		        if (rows.size() >= expectedRowCount) {
		            // optional: check last row input is visible
		            WebElement lastInput = rows.get(expectedRowCount - 1)
		                                .findElement(By.xpath(".//input[@placeholder='Enter characteristic']"));
		            return lastInput.isDisplayed();
		        }
		        return false;
		    });
		}

	}