package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement btnCancel;
	
	
	//Action Methods
	
	public void uploadFile(String filePath) {
	    WebElement fileUpload = driver.findElement(By.xpath("//input[@id='upload-input-0']"));
	    fileUpload.sendKeys(filePath);
	}	

	public void clickAddNewButton() {
		addNewButton.click();	
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
