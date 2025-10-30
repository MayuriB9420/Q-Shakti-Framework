package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Production_Order_VAC_Dashboard extends BasePage{

	private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;
    
	public Production_Order_VAC_Dashboard(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		this.js = (JavascriptExecutor) driver;
		this.actions=new Actions(driver);
	}
	
	@FindBy(xpath="//button[@id='production-order-add-button']")
	WebElement addbtn;
	
	@FindBy(xpath="//tbody//tr[1]//button[@id='production-order-action-btn']")
	WebElement startBtn;
	
	@FindBy(xpath="//input[@id='production-order-search-field']")
	WebElement searchbtn;
	
	@FindBy(xpath="//input[@id='production-order-customer-filter']")
	WebElement customerDrpdwn;
	
	@FindBy(xpath="//input[@id='production-order-product-filter']")
	WebElement productDrpdwn;
	
	@FindBy(xpath="//ul[@id='production-order-product-filter-listbox']")
	WebElement ProductDrpdwnList;
	
	@FindBy(xpath="//ul[@id='production-order-customer-filter-listbox']")
	WebElement customerDrpdwnList;
	
	@FindBy(xpath="//input[@id='production-order-status-filter']")
	WebElement statusDrpdwn;
	
	@FindBy(xpath="//ul[@id='production-order-status-filter-listbox']")
	WebElement statusDrpdwnList;
	
	@FindBy(xpath="//div[@class='MuiBox-root css-kimw2q']//input[@id='production-order-product-filter']")
	WebElement productionOrderProductDrpdwn;
	
	@FindBy(xpath="//ul[@id='production-order-product-filter-listbox']")
	WebElement productionOrderProductDrpdwnList;
	
	@FindBy(xpath="//div[@class='MuiBox-root css-kimw2q']//input[@id='production-order-customer-filter']")
	WebElement productionOrderCustomerDrpdwn;
	
	@FindBy(xpath="//ul[@id='production-order-customer-filter-listbox']")
	WebElement productionOrderCustomerDrpdwnList;
	
	@FindBy(xpath="//input[@id='production-order-item-code-input']")
	WebElement itemCodeTxtbox;
	
	@FindBy(xpath="//button[@id='production-order-modal-submit-btn']")
	WebElement productionOrderAddBtn;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement productionOrderCancelBtn;
	
	@FindBy(xpath="//button[@id='production-order-modal-close-btn']")
	WebElement productionOrderCloseBtn;
	
	//Action Methods
	
	public void clickAddProductionOrder()
	{	
		js.executeScript("arguments[0].scrollIntoView(true);", addbtn);
		   wait.until(ExpectedConditions.elementToBeClickable(addbtn));
		    
		    try {
		    	addbtn.click();
		    } catch (ElementClickInterceptedException e) {
		        js.executeScript("arguments[0].click();", addbtn);
	    
	}}
	
	public void clickPOProduct(String product)
	{
		js.executeScript("arguments[0].click();", productionOrderProductDrpdwn); 
	    wait.until(ExpectedConditions.visibilityOf(productionOrderProductDrpdwn));
	    productionOrderProductDrpdwn.sendKeys(product);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	    		By.xpath("//ul[@id='production-order-product-filter-listbox']//li[text()='" + product + "']")));
	    option.click();
	}
	
	public void clickPOCustomer(String product)
	{
		js.executeScript("arguments[0].click();", productionOrderCustomerDrpdwn); 
	    wait.until(ExpectedConditions.visibilityOf(productionOrderCustomerDrpdwn));
	    productionOrderCustomerDrpdwn.sendKeys(product);
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	    		By.xpath("//ul[@id='production-order-customer-filter-listbox']//li[text()='" + product + "']")));
	    option.click();
	}
	
	
	
	public void enterItemCode(String itemcode) 
	{
	    js.executeScript("arguments[0].click();", itemCodeTxtbox);
	    itemCodeTxtbox.sendKeys(itemcode);
	}
	
	public void clickAddPO()
	{
		js.executeScript("arguments[0].scrollIntoView(true);", productionOrderAddBtn);
		   wait.until(ExpectedConditions.elementToBeClickable(productionOrderAddBtn));
		    
		    try {
		    	productionOrderAddBtn.click();
		    } catch (ElementClickInterceptedException e) {
		        js.executeScript("arguments[0].click();", productionOrderAddBtn);
	    
		    }
	}	
	
	public void clickStart()
	{
		js.executeScript("arguments[0].scrollIntoView(true);", startBtn);
		   wait.until(ExpectedConditions.elementToBeClickable(startBtn));
		    
		    try {
		    	startBtn.click();
		    } catch (ElementClickInterceptedException e) {
		        js.executeScript("arguments[0].click();", startBtn); 
		    }	
	}
	
}