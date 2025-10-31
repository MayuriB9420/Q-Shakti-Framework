package PageObject;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;

public class ProductionOrdersPage extends BasePage{
    WebDriverWait wait;

    @FindBy(xpath = "//span[normalize-space()='PRODUCTION ORDERS']")
    WebElement productionOrdersPage;

    @FindBy(xpath = "//h4[contains(text(),'Production Orders')]")
    WebElement pageTitle;

    @FindBy(xpath = "//button[normalize-space()='Complete']")
    WebElement completeButton;

    @FindBy(xpath = "//button[normalize-space()='Yes, complete Order']")
    WebElement confirmCompleteButton;
    
    @FindBy(xpath = "//button[normalize-space()='Yes, close Order']")
    WebElement confirmCloseButton;
    
    @FindBy(xpath = "//button[normalize-space()='Yes, start Order']")
    WebElement confirmStartButton;

    @FindBy(xpath ="//div[@class='Toastify__toast-body']/div[2]")
  	public  WebElement toasterMessage;
    
    public ProductionOrdersPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void clickProductionOrdersPage() {
        try {
            productionOrdersPage.click();
        } catch (Exception e) {
            System.err.println(" Failed to click Upload button: " + e.getMessage());
        }
    }
    
    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void clickConfirmCompleteOrder() {
        confirmCompleteButton.click();
    }
    
    public void clickConfirmstartOrder() {
        confirmStartButton.click();
    }

    
    public void clickConfirmCloseOrder() {
        confirmCloseButton.click();
    }

    public void clickCompleteButtonForProductionOrder(String order_number ) throws InterruptedException {
       // String xpath1 = " //div[@role='gridcell' and .//p[normalize-space(text())='" + prodOrderNo + "']]"
                    // + "//button[normalize-space()='Complete']";
    	String xpath1 = "  //div[@role='row' and .//div[@data-field='order_number']//p[normalize-space()='" + order_number + "']]//button[normalize-space()='Complete']";
        
       // String xpath = "//button[normalize-space()='Complete']";
        WebElement completeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath1)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", completeButton);
        Thread.sleep(1000);
        
        completeButton.click();
    }
    
  //div[@role='row' and .//div[@data-field='order_number']//p[normalize-space()='1001']]//button[normalize-space()='Complete'] 
  //div[@role='gridcell' and .//p[normalize-space(text())='1001']]
    
    public void clickstartButtonForProductionOrder(String order_number ) throws InterruptedException {
    
     	String xpath1 = "  //div[@role='row' and .//div[@data-field='order_number']//p[normalize-space()='" + order_number + "']]//button[normalize-space()='Start']";
         
         WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath1)));

         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", startButton);
         Thread.sleep(1000);
         
         startButton.click();
     }
    
    public void clickcloseButtonForProductionOrder(String order_number ) throws InterruptedException {
        
     	String xpath1 = "  //div[@role='row' and .//div[@data-field='order_number']//p[normalize-space()='" + order_number + "']]//button[normalize-space()='Close']";
         
     	WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath1)));

         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeButton);
         Thread.sleep(1000);
         
         closeButton.click();
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

    
    public boolean isButtonVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
