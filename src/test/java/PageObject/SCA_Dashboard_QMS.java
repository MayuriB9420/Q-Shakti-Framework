package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SCA_Dashboard_QMS extends BasePage{

	public SCA_Dashboard_QMS(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
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
			wait.until(ExpectedConditions.elementToBeClickable(productionOrder)).click();
		}
		
		public void clickProductionDetails() {
			wait.until(ExpectedConditions.elementToBeClickable(productionDetails)).click();
		}
		
		public void clickProductionApproval() {
			wait.until(ExpectedConditions.elementToBeClickable(productionApproval)).click();
		}	
}
