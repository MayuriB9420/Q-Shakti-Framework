package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// Assuming this class extends BasePage
public class Process_Work_Flow_VAC_QMS extends BasePage{
	
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    
	public Process_Work_Flow_VAC_QMS(WebDriver driver) {
		super(driver);
        this.driver = driver;
        // Set explicit wait time
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        // Initialize PageFactory elements
        PageFactory.initElements(driver, this); 
	}
	
	// --- Static Elements (Using IDs and specific text) ---
	
    // Navigation link to Process Work Flow page
	@FindBy(xpath = "//ul[@class='slide-menu']//a[normalize-space()='Process Work Flow']")		
	WebElement processWorkFlowBtn;
	
    // Button to initiate the creation of a new workflow
	@FindBy(xpath = "//button[@id='process-workflow-create-button']")
	WebElement createProcessWorkFlowbtn;
	
    // Dropdowns on the creation page (MUI Autocomplete inputs)
	@FindBy(xpath = "//input[@id='process-product-filter']")
	WebElement productDropdown;
	
	@FindBy(xpath = "//input[@id='process-section-filter']")
	WebElement sectionDropdown;
	
	@FindBy(xpath = "//input[@id='process-item-filter']")
	WebElement itemDropdown;
	
    // Action buttons for the process grid
	@FindBy(xpath = "//button[@id='remove-process-button']")
	WebElement removeProcessbtn; // This is now used for the main 'Remove Process' button, but we will assume it's also the in-row '-' button for now.
	
	@FindBy(xpath = "//button[@id='add-process-button']")
	WebElement addProcessbtn;
	
	@FindBy(xpath = "//button[text()='Submit']")
	WebElement submitBtn;
	
	@FindBy(xpath = "//button[@id='process-workflow-cancel-button']")
	WebElement cancelBtn;
	
	
	// --- Dynamic Element Locators (Helper Methods) ---

    /**
     * Helper to get the 1-based XPath row index from the 0-based test case index.
     * @param rowIndex 0-based index from the Test Case logic.
     * @return 1-based index for XPath.
     */
    private int getXPathRowIndex(int rowIndex) {
        return rowIndex + 1;
    }

    // Column 2: Process Name Input
    private By getProcessNameInput(int rowIndex) {
        return By.xpath("//tbody/tr[" + getXPathRowIndex(rowIndex) + "]/td[2]//input");
    }

    // Column 3: Parameters Input
    private By getProcessParametersInput(int rowIndex) {
        return By.xpath("//tbody/tr[" + getXPathRowIndex(rowIndex) + "]/td[3]//input");
    }

    // Column 6: Dependency Dropdown (Assuming a standard <select> for now)
    private By getDependencyDropdown(int rowIndex) {
        int xpathRowIndex = getXPathRowIndex(rowIndex); 
        return By.xpath("//tbody/tr[" + xpathRowIndex + "]/td[6]//div[@id='process-dependency-select']"); 
    }

    // Keep the option locator, which works once the menu is open
    private By getDependencyOption(String value) {
        // Looks for a list item (li) with the exact text value inside the listbox
        return By.xpath("//ul[@role='listbox']//li[text()='" + value + "']");
    }

    // Column 7: Cycle Time Input
    private By getCycleTimeInput(int rowIndex) {
        return By.xpath("//tbody/tr[" + getXPathRowIndex(rowIndex) + "]/td[7]//input");
    }

    /**
     * Column 9: Add Sub Process Button (The '+' button/icon in the Action column)
     * FIX: Combines the dynamic row index with the static button ID for unique targeting.
     */
    private By getAddSubProcessButton(int rowIndex) {
        // Assuming the button is in the 9th column (Action column), 
        // and following the structure: tr/td/div/button
        return By.xpath("//tbody/tr[" + getXPathRowIndex(rowIndex) + "]/td[9]/div/button[@id='add-subprocess-button']");
    }
    
    /**
     * Helper to get the row element based on index for waiting purposes.
     */
    private By getRowElement(int rowIndex) {
        return By.xpath("//tbody/tr[" + getXPathRowIndex(rowIndex) + "]");
    }
    
    public void waitForRowToExist(int rowIndex) {
        By processNameInputLocator = getProcessNameInput(rowIndex);
        wait.until(ExpectedConditions.elementToBeClickable(processNameInputLocator));
        }
	
	// --- Action Methods (Required for Test Case) ---
	
   
	public void clickProcessWorkFlow() {
		wait.until(ExpectedConditions.elementToBeClickable(processWorkFlowBtn)).click();
	}
	
	
    
	public void selectProductDropdown(String product) {
		js.executeScript("arguments[0].click();", productDropdown);
	    wait.until(ExpectedConditions.visibilityOf(productDropdown));
	    productDropdown.sendKeys(product);
        // Assuming the listbox is dynamically generated after typing/clicking
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@id='process-product-filter-listbox']//li[normalize-space()='" + product + "']")));
	    option.click();
	}
	
   
	public void selectSectionDropdown(String section) {
		js.executeScript("arguments[0].click();", sectionDropdown);
	    wait.until(ExpectedConditions.visibilityOf(sectionDropdown));
	    sectionDropdown.sendKeys(section);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@id='process-section-filter-listbox']//li[normalize-space()='" + section + "']")));
	    option.click();
	}
	
    
	public void selectItemDropdown(String item) {
		js.executeScript("arguments[0].click();", itemDropdown);
	    wait.until(ExpectedConditions.visibilityOf(itemDropdown));
	    itemDropdown.sendKeys(item);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@id='process-item-filter-listbox']//li[normalize-space()='" + item + "']")));
	    option.click();
	}
    
   
	public void clickCreateProcessWorkFlow() {
		wait.until(ExpectedConditions.elementToBeClickable(createProcessWorkFlowbtn)).click();
	}
	
   
	public void enterProcessName(int rowIndex, String processName) {
	    By locator = getProcessNameInput(rowIndex);
	    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(locator)); 
	    input.clear();
	    input.sendKeys(processName);
	   }
	
   
	public void enterProcessParameters(int rowIndex, String parameter) {
        // Wait for the row to exist before interacting with any element in it
        waitForRowToExist(rowIndex);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(getProcessParametersInput(rowIndex)));
		element.sendKeys(parameter);
	}
	
//    public void enterCycleTime(int rowIndex, String cycleTime) {
//        // Wait for the row to exist before interacting with any element in it
//        waitForRowToExist(rowIndex);
//        // Clean input to remove " mins" or similar text
//        String cleanedCycleTime = cycleTime.replaceAll("[^0-9]", "");
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(getCycleTimeInput(rowIndex)));
//        element.sendKeys(cleanedCycleTime); 
//    }
    
	
	public void enterCycleTime(int rowIndex, String cycleTime) {
	    waitForRowToExist(rowIndex); 
	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(getCycleTimeInput(rowIndex)));
	    element.clear();
	    element.sendKeys(cycleTime.trim()); 
	    try {
	        Thread.sleep(500); // Wait for 500 milliseconds (half a second)
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }}
	
    public void selectDependency(int rowIndex, String dependencyValue) {
        if (dependencyValue == null || dependencyValue.trim().equalsIgnoreCase("None") || dependencyValue.trim().isEmpty()) {
           return;
        }
        waitForRowToExist(rowIndex);
        By dropdownLocator = getDependencyDropdown(rowIndex);
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        dropdown.click();  
        By optionLocator = getDependencyOption(dependencyValue.trim());
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        js.executeScript("arguments[0].click();", option);
        System.out.println("Selected Dependency '" + dependencyValue + "' for row UI Index " + rowIndex);
        
       }
    
    
    public void clickAddProcess(int newRowIndex) {
        int maxRetries = 5;
        int attempt = 0;
        By addProcessBy = By.id("add-process-button"); 

        while (attempt < maxRetries) {
            try {
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addProcessBy));
                js.executeScript("arguments[0].scrollIntoView(true);", button);
               js.executeScript("arguments[0].click();", button);          
               waitForRowToExist(newRowIndex);
                return; 

            } catch (Exception e) {
                attempt++;
//                logger.warn("Failure on attempt " + attempt + " to click Add Process/wait for new row. Reason: " + e.getMessage());
                if (attempt < maxRetries) {
                    // Wait briefly before the next retry
                    try { Thread.sleep(500); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
                } else {
//                    logger.error("Failed to click 'Add Process' and locate the new row after " + maxRetries + " attempts. Final Error: " + e.getMessage());
                    throw e; // Re-throw the final exception
                }
            }
        }
    }
    
    
    public void clickAddSubProcess(int rowIndex) {
        waitForRowToExist(rowIndex); 
        
        By buttonLocator = getAddSubProcessButton(rowIndex);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLocator));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        js.executeScript("arguments[0].click();", button); 
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

	public void clickRemoveProcess() {
		wait.until(ExpectedConditions.elementToBeClickable(removeProcessbtn)).click();
	}
	
	public void clickSubmit() {
		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
	}

	public void clickCancel() {
		wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
	}
}
