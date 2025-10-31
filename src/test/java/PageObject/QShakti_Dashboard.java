package PageObject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QShakti_Dashboard extends BasePage{

	public QShakti_Dashboard(WebDriver driver) {
		super(driver);
	}
	
	    @FindBy(xpath = "//div[@class='MuiBox-root css-pwbuwj']")
	    WebElement QDashboardPage;
	    
	    @FindBy(xpath = "//h6[normalize-space()='Dashboard']")
	    WebElement Dashboard;
	    
	    @FindBy(xpath = "//h6[normalize-space()='Master Data Management (MDM)']")
	    private WebElement mdm;

	    @FindBy(xpath = "//h6[normalize-space()='Order Management']")
	    private WebElement orderManagement;

	    @FindBy(xpath = "//h6[normalize-space()='RM Management']")
	    private WebElement rmManagement;

	    @FindBy(xpath = "//h6[normalize-space()='Inprocess Management']")
	    private WebElement inprocess;

	    @FindBy(xpath = "//h6[normalize-space()='Final Acceptance Inspection']")
	    private WebElement finalInspection;

	    @FindBy(xpath = "//h6[normalize-space()='User & Security Management']")
	    private WebElement userSecurity;

	    public void clickQDashboardPage() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(QDashboardPage)).click();
	        
	        } catch (Exception e) {}
	    }
	    
	    public void clickDashboard() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(Dashboard)).click();
	        } catch (Exception e) {}
	    }
	    
	    public void clickMDM() {    
	    try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(mdm)).click(); 
        
        } catch (Exception e) {}
        }
	     
	    public void clickOrderManagement() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(orderManagement)).click();    
	        
	        } catch (Exception e) {}
	        }
	    
	    public void clickRMManagement() { 
	    	  try {
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		            wait.until(ExpectedConditions.elementToBeClickable(rmManagement)).click();
		           
		        } catch (Exception e) {}
	    	  }
	    
	    public void clickInprocess() { 
	    	  try {
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		            wait.until(ExpectedConditions.elementToBeClickable(inprocess)).click();     
		        
		        } catch (Exception e) {}
	    	  }
	    
	    public void clickFinalInspection() { 
	    	  try {
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		            wait.until(ExpectedConditions.elementToBeClickable(finalInspection)).click();
		           
		        } catch (Exception e) {}
	    	  }
	    
	    public void clickUserSecurity() {
	    	  try {
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		            wait.until(ExpectedConditions.elementToBeClickable(userSecurity)).click();
		             
		        } catch (Exception e) {}
	    	  }
}
