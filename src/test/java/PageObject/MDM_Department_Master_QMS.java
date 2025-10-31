package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MDM_Department_Master_QMS extends BasePage{

	public MDM_Department_Master_QMS(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//div[@class='MuiCollapse-root MuiCollapse-vertical MuiCollapse-entered css-c4sutr']//button[@type='button'][normalize-space()='Add New']")
	WebElement addNewButton;
	
	@FindBy(xpath = "//button[@id='upload-btn-1']")
	WebElement uploadFile;
	
	@FindBy(xpath = "//button[@id='download-template-btn-0']")
	WebElement downloadTemplate;
	
	
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
	
	
	//Action Methods
	
	public void uploadFile(String filePath) {
	    WebElement fileUpload = driver.findElement(By.xpath("//input[@id='upload-input-0']"));
	    fileUpload.sendKeys(filePath);
	}	

	
	public void downloadTemplate()
	{
		downloadTemplate.click();
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
