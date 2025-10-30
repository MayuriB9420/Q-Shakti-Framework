package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MDM_PlantMaster_QMS extends BasePage {

	public MDM_PlantMaster_QMS(WebDriver driver) {
		super(driver);
	}	

	@FindBy(xpath = "//div[@class='MuiCollapse-root MuiCollapse-vertical MuiCollapse-entered css-c4sutr']//button[@type='button'][normalize-space()='Add New']")
	WebElement addNewButton;
	
	@FindBy(xpath = "//input[@id='upload-input-0']")
	WebElement uploadFile;
	
	@FindBy(xpath = "")
	WebElement plantCode;
	
	@FindBy(xpath = "")
	WebElement plantName;
	
	@FindBy(xpath = "")
	WebElement plantDescription;
	
	@FindBy(xpath = "")
	WebElement plantLocation;
	
	@FindBy(xpath = "//button[text()='Save']")
	WebElement btnSave;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	WebElement btnClose;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement btnCancel;
	
	By toastMsg = By.xpath("//div[contains(@class,'Toastify__toast-body')]");
	
	//Action Methods
	
	public void uploadFile(String filePath) {
	    WebElement fileUpload = driver.findElement(By.xpath("//input[@id='upload-input-0']"));
	    fileUpload.sendKeys(filePath);
	}	

	public void clickAddNewButton() {
	    wait.until(ExpectedConditions.visibilityOf(addNewButton));
	    wait.until(ExpectedConditions.elementToBeClickable(addNewButton));  
	    addNewButton.click();		
	}
	
	public String getToastMessage() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastMsg));
        return toast.getText();
    }
	
	public void enterPlantCode(String PlantCode)
	{
		
	}
	
	public void enterPlantName(String PlantName)
	{
		
	}
	
	public void enterPlantDesc(String PlantDesc)
	{
		
	}
	
	public void enterPlantLoc(String PlantLoc)
	{
		
	}
}
