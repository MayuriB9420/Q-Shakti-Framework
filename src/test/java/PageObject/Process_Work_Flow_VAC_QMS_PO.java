package PageObject;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Process_Work_Flow_VAC_QMS_PO extends BasePage {
	 
	private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;
	
    public Process_Work_Flow_VAC_QMS_PO(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		this.js = (JavascriptExecutor) driver;
		this.actions=new Actions(driver);
	}
    
    private static final String TABLE_ROW_BASE = "//table/tbody/tr"; 
    private static final int SR_NO_COL = 1;
    private static final int PROCESS_COL = 2;
    private static final int PARAMETERS_COL = 3;
    private static final int CHECKLIST_COL = 4;
    // private static final int ATTACHMENTS_COL = 5; 
    private static final int DEPENDENCY_COL = 6;
    private static final int CYCLE_TIME_COL = 7;
    // private static final int APPROVAL_COL = 8;
    private static final int ACTION_COL = 9;
	private By submitBtn = By.xpath("//button[text()='Submit']"); 
	
	
	@FindBy(xpath = "//ul[@class='slide-menu']//a[normalize-space()='Process Work Flow']")		
	WebElement processWorkFlowBtn;
	
	@FindBy(xpath = "//button[@id='process-workflow-create-button']")
	WebElement createProcessWorkFlowbtn;
	
	@FindBy(xpath = "//input[@id='process-product-filter']")
	WebElement productDropdown;
	
	@FindBy(xpath = "//input[@id='process-section-filter']")
	WebElement sectionDropdown;
	
	@FindBy(xpath = "//input[@id='process-item-filter']")
	WebElement itemDropdown;
	
	@FindBy(xpath = "//button[@id='add-process-button']")
	WebElement addProcessbtn;
	
	@FindBy(xpath = "//button[@id='remove-process-button']")
	WebElement removeProcessbtn; 
	
	@FindBy(xpath = "//button[@id='process-workflow-cancel-button']")
	WebElement cancelBtn;
	
	public void clickProcessWorkFlow() {
		wait.until(ExpectedConditions.elementToBeClickable(processWorkFlowBtn)).click();
	}
    
    private By getFieldLocator(String srNo, int colIndex, String elementType) {
        String xpath = String.format("%s[td[%d] = '%s']/td[%d]//%s",
                TABLE_ROW_BASE, SR_NO_COL, srNo, colIndex, elementType);
        return By.xpath(xpath);
    }
    
    
    public void clickAddNewMainProcess() {
         WebElement button = wait.until(ExpectedConditions.visibilityOf(addProcessbtn));
        this.js.executeScript("arguments[0].click();", button); // Used initialized js field
         try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void clickAddSubProcess(String parentSrNo) {
         By locator = By.xpath(String.format("%s[td[%d] = '%s']/td[%d]//button[@id='add-subprocess-button']", 
                                            TABLE_ROW_BASE, SR_NO_COL, parentSrNo, ACTION_COL));

        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.js.executeScript("arguments[0].click();", button); // Used initialized js field
    }
    
    public void typeProcessName(String srNo, String name) {
        By locator = getFieldLocator(srNo, PROCESS_COL, "input");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(name);
    }
    
    public void typeParameters(String srNo, String parameters) {
        By locator = getFieldLocator(srNo, PARAMETERS_COL, "input");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(parameters);
    }
    
    public void typeCycleTime(String srNo, String timeValue) {
        By locator = getFieldLocator(srNo, CYCLE_TIME_COL, "input");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(timeValue);
    }
   
    
    public void selectDependency(String srNo, String dependencyCellValue) throws Exception {
        if (dependencyCellValue == null || dependencyCellValue.trim().isEmpty()) {
            System.out.println("Sr.No " + srNo + ": No dependency value provided. Skipping selection.");
            return;
        }

        String[] dependencies = dependencyCellValue.split("\\s*,\\s*");

        boolean allNone = Arrays.stream(dependencies)
                .allMatch(dep -> dep.equalsIgnoreCase("None") || dep.equalsIgnoreCase("None (Child Process)"));

        if (allNone) {
            System.out.println("Sr.No " + srNo + ": Dependency is 'None'. Skipping dropdown click.");
            return;
        }
        // Locate the dropdown field
        By dependencyFieldLocator = By.xpath(String.format(
                "//tr[./td[%d][normalize-space()='%s']]//div[@id='process-dependency-select']",
                SR_NO_COL, srNo
        ));

        WebElement dropdownField = wait.until(ExpectedConditions.elementToBeClickable(dependencyFieldLocator));
        actions.moveToElement(dropdownField).perform();

        // Retry opening dropdown until the listbox appears
        By listboxLocator = By.xpath("//ul[@role='listbox']");
        int attempts = 0;
        while (attempts < 3) {
            try {
                actions.moveToElement(dropdownField).click().perform();
                Thread.sleep(500); // allow animation
                wait.until(ExpectedConditions.visibilityOfElementLocated(listboxLocator));
                break;
            } catch (Exception e) {
                attempts++;
                if (attempts == 3) throw e;
            }
        }

        // Loop through each dependency and select it
        for (String dependency : dependencies) {
            if (dependency.equalsIgnoreCase("None") || dependency.equalsIgnoreCase("None (Child Process)")) {
                continue;
            }

            String[] possibleValues = {
                dependency.trim(),
                dependency.replaceAll("\\s*\\(Sr\\.\\s*No\\.\\s*\\d+\\)", "").trim()
            };

            boolean selected = false;
            for (String val : possibleValues) {
                By optionLocator = By.xpath("//ul[@role='listbox']//li[contains(.,'" + val + "')]");
                try {
                    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
                    actions.moveToElement(option).click().perform();
                    Thread.sleep(600); // allow UI to register selection
                    System.out.println("Sr.No " + srNo + ": Selected dependency → " + val);
                    selected = true;
                    break;
                } catch (Exception e) {
                }
            }

            if (!selected) {
                System.out.println("Sr.No " + srNo + ": Dependency '" + dependency + "' not found in dropdown!");
            }
        }
        
        WebElement outsideElement = driver.findElement(By.xpath("//table")); // adjust if needed
        actions.moveToElement(outsideElement).click().perform();

        // Ensure dropdown closes
        wait.until(ExpectedConditions.invisibilityOfElementLocated(listboxLocator));
        System.out.println("Sr.No " + srNo + ": Dependencies selected and dropdown closed successfully.");
    }

    
    public void clickAddChecklist(String srNo) {
        By locator = By.xpath(String.format("%s[td[%d] = '%s']/td[%d]//button[text()='+ ADD']", 
                                            TABLE_ROW_BASE, SR_NO_COL, srNo, CHECKLIST_COL));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    
	public void selectProductDropdown(String product) {
		this.js.executeScript("arguments[0].click();", productDropdown); // Used initialized js field
	    wait.until(ExpectedConditions.visibilityOf(productDropdown));
	    productDropdown.sendKeys(product);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@id='process-product-filter-listbox']//li[normalize-space()='" + product + "']")));
	    option.click();
	}
	
   
	public void selectSectionDropdown(String section) {
		this.js.executeScript("arguments[0].click();", sectionDropdown); 
	    wait.until(ExpectedConditions.visibilityOf(sectionDropdown));
	    sectionDropdown.sendKeys(section);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@id='process-section-filter-listbox']//li[normalize-space()='" + section + "']")));
	    option.click();
	}
	
    
	public void selectItemDropdown(String item) {
		this.js.executeScript("arguments[0].click();", itemDropdown); 
	    wait.until(ExpectedConditions.visibilityOf(itemDropdown));
	    itemDropdown.sendKeys(item);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//ul[@id='process-item-filter-listbox']//li[normalize-space()='" + item + "']")));
	    option.click();
	}
    
   
	public void clickCreateProcessWorkFlow() {
		wait.until(ExpectedConditions.elementToBeClickable(createProcessWorkFlowbtn)).click();
	}
	
	public void clickSubmitBtn() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        js.executeScript("arguments[0].click();", button);
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