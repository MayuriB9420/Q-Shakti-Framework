package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.ExtentReportManager;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class OpenOrdersPage extends BasePage{

	    public OpenOrdersPage(WebDriver driver) {
		super(driver);
	}

	    @FindBy(xpath = "//span[normalize-space()='OPEN ORDERS']")
	    private WebElement openOrdersPage;
	    
	    @FindBy(xpath = "//button[normalize-space()='Production Planner']")
	    private WebElement productionPlannerTab;

	    @FindBy(xpath = "//button[normalize-space()='RM Planner']")
	    private WebElement rmPlannerTab;

	    @FindBy(xpath = "//label[normalize-space()='Upload']")
	    private WebElement uploadButton;
	    
	    @FindBy(xpath = "//button[@aria-label='Download Production Planner Download.pdf']")
	    private WebElement ProductionPlannerDownloadPDF;

	    @FindBy(xpath = "//button[@aria-label='Download Production Planner Download.xlsx']")
	    private WebElement ProductionPlannerDownloadEXCEL;
	    
	    @FindBy(xpath = "//button[@aria-label='Download RM Planner Download.pdf']")
	    private WebElement RMPlannerDownloadPDF;

	    @FindBy(xpath = "//button[@aria-label='Download RM Planner Download.xlsx']")
	    private WebElement RMPlannerDownloadEXCEL;
	    
	    
	    @FindBy(xpath = "//table[@class='table']/tbody/tr")
	    private List<WebElement> productionOrderRows;
	    
		 // File input 
	    @FindBy(xpath = "//input[@type='file']")
	    WebElement fileInput;
	    
	    @FindBy(xpath ="//div[@class='Toastify__toast-body']/div[2]")
	 		public  WebElement toasterMessage;
	 	    

	    // Locators for the table headers
	   // private By srNoHeader = By.xpath("//th[text()='SR. NO.']");
	   // private By prodOrderNoHeader = By.xpath("//th[text()='PROD. ORDER NO.']");

	    public void clickOpenOrdersPage() {
	        try {
	            openOrdersPage.click();
	        } catch (Exception e) {
	            System.err.println(" Failed to click Upload button: " + e.getMessage());
	        }
	    }
	    
	   
	    public void clickUpload() {
	        try {
	            safeClick(uploadButton);
	        } catch (Exception e) {
	            System.err.println(" Failed to click Upload button: " + e.getMessage());
	        }
	    }
	    
	    
	    public void clickProductionPlannerTab() {
	        try {
	            safeClick(productionPlannerTab);
	        } catch (Exception e) {
	            System.err.println(" Failed to click Production Planner Tab: " + e.getMessage());
	        }
	    }

	    public void clickRMPlannerTab() {
	        try {
	            safeClick(rmPlannerTab);
	        } catch (Exception e) {
	            System.err.println(" Failed to click RM Planner Tab: " + e.getMessage());
	        }
	    }

	    public void downloadProductionPlannerPDF() {
	        try {
	            safeClick(ProductionPlannerDownloadPDF);
	        } catch (Exception e) {
	            System.err.println("Failed to click Production Planner PDF: " + e.getMessage());
	        }
	    }

	    /** Click on Production Planner Excel Download */
	    public void downloadProductionPlannerExcel() {
	        try {
	            safeClick(ProductionPlannerDownloadEXCEL);
	        } catch (Exception e) {
	            System.err.println("Failed to click Production Planner Excel: " + e.getMessage());
	        }
	    }

	    /** Click on RM Planner PDF Download */
	    public void downloadRMPlannerPDF() {
	        try {
	         safeClick(RMPlannerDownloadPDF);
	        
	        } catch (Exception e) {
	            System.err.println("Failed to click RM Planner PDF: " + e.getMessage());
	        }
	    }

	    /** Click on RM Planner Excel Download */
	    public void downloadRMPlannerExcel() {
	        try {
	        safeClick(RMPlannerDownloadEXCEL);
	        } catch (Exception e) {
	            System.err.println("Failed to click RM Planner Excel: " + e.getMessage());
	        }
	    }
	    
	    
	    public boolean uploadFile(String relativePath) {
	        try {
	            String absolutePath = getAbsolutePath(relativePath);
	            File file = new File(absolutePath);

	            if (!file.exists()) {
	                ExtentReportManager.logFail(" File not found at: " + absolutePath);
	                return false;
	            }

	            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('hidden');", fileInput);

	            // Now send the file path
	            fileInput.sendKeys(file.getAbsolutePath());
	            ExtentReportManager.logInfo("Uploaded file: " + file.getAbsolutePath());
	            return true;

	        } catch (Exception e) {
	            ExtentReportManager.logFail(" File upload failed for: " + relativePath + ". Error: " + e.getMessage());
	            return false;
	        }
	    }
	    
	    
	    private String getAbsolutePath(String relativePath) {
	        File file = new File(System.getProperty("user.dir"), relativePath);
	        return file.getAbsolutePath();
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
	            System.err.println("❌ Click failed: " + ex.getMessage());
	        }
	    }
	    
	    
	    public String getToasterMessage() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	            WebElement toaster = wait.until(ExpectedConditions.visibilityOf(toasterMessage));
	            String msg = toaster.getText().trim();
	            
	            // Wait until toaster disappears so next upload won't reuse the old one
	            wait.until(ExpectedConditions.invisibilityOf(toaster));
	            
	            ExtentReportManager.logInfo("Toaster message: " + msg);
	            return msg;
	        } catch (Exception e) {
	            ExtentReportManager.logInfo("No toaster appeared.");
	            return null;
	        }
	    }

	    
	    public void waitForToasterToDisappear() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	            wait.until(ExpectedConditions.invisibilityOf(toasterMessage));
	        } catch (Exception e) {
	            ExtentReportManager.logInfo("Toaster did not disappear within timeout, continuing...");
	        }
	    }
	  /*  public int getRowCount() {
	        return productionOrderRows.size();
	    }

	    public String getCellData(int rowNum, String columnHeader) {
	        // Simple implementation: assuming the column order is fixed and we know the index
	        // A more robust way uses the column header text to find the index dynamically.
	        int colIndex;
	        switch (columnHeader.toUpperCase()) {
	            case "SR. NO.": colIndex = 1; break;
	            case "PROD. ORDER NO.": colIndex = 2; break;
	            case "LOT QTY": colIndex = 4; break; // Note: 'LOT NO.' is column 3
	            case "ITEM CODE": colIndex = 5; break;
	            // Add other columns...
	            default: throw new IllegalArgumentException("Invalid column header: " + columnHeader);
	        }
	        
	        // XPath to get cell content: tr[rowNum]/td[colIndex]
	        String cellXpath = String.format("//table[@class='table']/tbody/tr[%d]/td[%d]", rowNum, colIndex);
	        return driver.findElement(By.xpath(cellXpath)).getText().trim();
	    }*/
	}
