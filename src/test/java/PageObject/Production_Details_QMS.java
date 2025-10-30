package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Production_Details_QMS extends BasePage{

	public Production_Details_QMS(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath = "//button[@id='kanban-board-tab']")
	WebElement kanbanBoard;
	
	@FindBy(xpath = "//button[@id='records-tab']")
	WebElement recordTab;
	
	@FindBy(xpath = "//button[@id='graph-tab']")
	WebElement graphTab;
	
	@FindBy(xpath = "//input[@id='kanban-product-filter']")
	WebElement searchProductDropdown;
	
	@FindBy(xpath = "//ul[@id='kanban-product-filter-listbox']//li")
	WebElement searchProductlist;
	
	@FindBy(xpath = "//button//*[name()='svg' and @data-testid='CloseIcon']")
	WebElement closeIconBtn;
	
	
	
//Action Methods
	
	public void clickKanbanBoard()
	{
		wait.until(ExpectedConditions.elementToBeClickable(kanbanBoard)).click();
		js.executeScript("arguments[0].click();", kanbanBoard); 
	}
	
	
	public void clickrecordTab()
	{
		wait.until(ExpectedConditions.elementToBeClickable(recordTab)).click();
		js.executeScript("arguments[0].click();", recordTab); 
	}
	
	public void clickgraphTab()
	{
		wait.until(ExpectedConditions.elementToBeClickable(graphTab)).click();
		js.executeScript("arguments[0].click();", graphTab); 
	} 
	
	public void searchProduct(String product) {
	    By closeIcon = By.xpath("(//button//*[name()='svg' and (contains(@class,'MuiSvgIcon-root') or @data-testid='CloseIcon') and not(ancestor-or-self::*[@aria-hidden='true'])])[last()]");

	    try {
	        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(closeIcon));
	        icon.click();
	    } catch (TimeoutException e) {
	        System.out.println("Visible close icon not found — continuing.");
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(searchProductDropdown)).click();
	    searchProductDropdown.sendKeys(product);

	    By optionLocator = By.xpath("//ul[@id='kanban-product-filter-listbox']//li[normalize-space(text())='" + product + "']");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));

	    for (int i = 0; i < 3; i++) {
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
	            break;
	        } catch (StaleElementReferenceException e) {
	            System.out.println("Dropdown re-rendered, retrying... (" + (i + 1) + ")");
	        }
	    }
	}

}
