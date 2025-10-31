package PageObject;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RMInspection_DetailsPage extends BasePage{
	
	   WebDriverWait wait;

	    public RMInspection_DetailsPage(WebDriver driver) {
	        super(driver);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        PageFactory.initElements(driver, this);
	        
	    }

	    @FindBy(xpath = "//span[normalize-space()='RM INSPECTION DETAILS']")	    
	    WebElement rmInspectionDetailPage;
	    
	    @FindBy(xpath = "//label[contains(normalize-space(.), 'Section')]/following::input[1]")	    
	    WebElement SectionInput;
	    
	    @FindBy(xpath = "//label[contains(normalize-space(.), 'I/O No')]/following::input[1]")	    
	    WebElement IONoInput;
	    
	    @FindBy(xpath = "//label[contains(normalize-space(.), 'Date')]/following::input[1]")	    
	    WebElement DateInput;
	    
	    @FindBy(xpath = "//label[contains(normalize-space(.), 'Item Code')]/following::input[1]")	    
	    WebElement ItemCodeInput;
	    
	    @FindBy(xpath = "//tbody/tr[1]/td[6]/div[1]/button[1]//*[name()='svg']")
	    WebElement ViewButton;
	    
	    @FindBy(xpath = "//tbody/tr[1]/td[6]/div[1]/button[2]//*[name()='svg']")
	    WebElement EditButton;
	    
	    
	    public void navigateToRmInspectionDetailPage() {
	    	
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(rmInspectionDetailPage)).click();
	        
	        } catch (Exception e) {}
	    }
	    
	     public void enterSection(String section) {
	    	
	        wait.until(ExpectedConditions.elementToBeClickable(SectionInput)).click();
	        
	        SectionInput.clear();
	        SectionInput.sendKeys(section);

	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//li[contains(@role,'option') and normalize-space()='" + section + "']")));

	        option.click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(IONoInput));
	     }
	    
	    
        public void enterIO(String IO_No) {
    	   
	        wait.until(ExpectedConditions.elementToBeClickable(IONoInput)).click();
	        
	        IONoInput.clear();
	        IONoInput.sendKeys(IO_No);

	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//li[contains(@role,'option') and normalize-space()='" + IO_No + "']")));

	        option.click();
	        wait.until(ExpectedConditions.elementToBeClickable(ItemCodeInput));
	     }
	    
	     public void enterDate(int Date) {
	    	 
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.visibilityOf(DateInput));
	            DateInput.clear();
	            DateInput.sendKeys(String.valueOf(Date));
	        } catch (Exception e) {
	        		            System.out.println(" Date input field is not interactable: " + e.getMessage());
	        }
	     }
	    
         public void enterItemCode(String Item_code) {
        	 
	        wait.until(ExpectedConditions.elementToBeClickable(ItemCodeInput)).click();
	        
	        ItemCodeInput.clear();
	        ItemCodeInput.sendKeys(Item_code);

	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//li[contains(@role,'option') and normalize-space()='" + Item_code + "']")));

	        option.click();
	        wait.until(ExpectedConditions.elementToBeClickable(ViewButton));
	    }
         
	    public void clickViewButton() {
	    	
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(ViewButton)).click();
	        
	        } catch (Exception e) {}
	    }

	    
	    public void clickEditButton() {
	    	
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(EditButton)).click();
	        
	        } catch (Exception e) {}
	    }
}
