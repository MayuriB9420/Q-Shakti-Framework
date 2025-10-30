package PageObject;
import java.time.Duration;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;

	public class ManageAccessPage extends BasePage {
	  
	    public  Logger logger;
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    By submitButton1 = By.xpath("//button[normalize-space()='Submit']");

	    
	    public ManageAccessPage(WebDriver driver) {
	    	super (driver);
	    }

	       public void ManageAccessButton() {
	    WebElement ManageAccess=driver.findElement(By.xpath("//button[normalize-space()='Manage Access']"));
	    ManageAccess.click();
        logger.info("Clicked User and Security Management");
        ExtentReportManager.logInfo("Clicked Manage access");
	    }
	       
	       // Step 3: Submit Role

    public void submitRole1() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(submitButton1));

    // Scroll into view (important for MUI pages)
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton1);
    Thread.sleep(500);

    // Ensure button is visible
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton1);
  }
	      
	    // Validate if permission checkbox is ticked
	    public boolean isPermissionChecked(String screen, String permission) {
	        String xpath = "//td[contains(text(),'" + screen + "')]" +
	                       "/following-sibling::td//input[@aria-label='" + permission + "']";
	        return driver.findElement(By.xpath(xpath)).isSelected();
	    }

	    public static class ScreenPermission {
	        private String screen;
	        private String view;
	        private String add;
	        private String edit;
	        private String delete;
	        private String export;

	        // constructor
	        public ScreenPermission(String screen, String view, String add, String edit, String delete, String export) {
	            this.screen = screen;
	            this.view = view;
	            this.add = add;
	            this.edit = edit;
	            this.delete = delete;
	            this.export = export;
	        }

	        // getters
	        public String getScreen() { return screen; }
	        public String getView() { return view; }
	        public String getAdd() { return add; }
	        public String getEdit() { return edit; }
	        public String getDelete() { return delete; }
	        public String getExport() { return export; }
	    }

	}