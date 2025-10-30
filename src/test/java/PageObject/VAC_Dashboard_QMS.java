package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VAC_Dashboard_QMS extends BasePage{

	public VAC_Dashboard_QMS(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
		
		@FindBy(xpath = "//span[text()='Control Plan']")
		WebElement controlPlan;
		
		@FindBy(xpath = "//span[text()='Production Order']")	
		WebElement productionOrder;
		
		@FindBy(xpath = "//span[text()='Production Details']")
		WebElement productionDetails;
		
		@FindBy(xpath = "//span[text()='Production Approval']")
		WebElement productionApproval;
		
//		Action Methods
		
		public void clickControlPlan() {
		    wait.until(ExpectedConditions.elementToBeClickable(controlPlan)).click();
		}
		
		public void clickProductionOrder() {   
			 js.executeScript("arguments[0].scrollIntoView(true);", productionOrder);
			   wait.until(ExpectedConditions.elementToBeClickable(productionOrder));
			    
			    try {
			        productionOrder.click();
			    } catch (ElementClickInterceptedException e) {
			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productionOrder);
			    }
		}

		
		public void clickProductionDetails() {
			js.executeScript("arguments[0].scrollIntoView(true);", productionDetails);
			   wait.until(ExpectedConditions.elementToBeClickable(productionDetails));
			   try {
			        productionOrder.click();
			    } catch (ElementClickInterceptedException e) {
			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productionDetails);
			    } 
			
		}
		
		public void clickProductionApproval() {
			wait.until(ExpectedConditions.elementToBeClickable(productionApproval)).click();
		}	
}
