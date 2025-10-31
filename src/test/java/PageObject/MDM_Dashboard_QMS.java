package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MDM_Dashboard_QMS extends BasePage {

	public MDM_Dashboard_QMS(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[text()='Master']")
	WebElement master;
	
	@FindBy(xpath = "//p[text()='Plant Master']")
	WebElement plantMaster;
	
	
	@FindBy(xpath = "//p[text()='Section Master']")
	WebElement sectionMaster;
	
	@FindBy(xpath = "//p[text()='Machines Master']")
	WebElement machinesMaster;
	
	
	@FindBy(xpath = "//p[text()='Products Master']")
	WebElement productsMaster;
	
	@FindBy(xpath = "//p[text()='Item Master']")
	WebElement itemMaster;
	
	@FindBy(xpath = "//p[text()='Customer Master']")
	WebElement customerMaster;

	@FindBy(xpath = "//p[text()='Department Master']")
	WebElement departmentMaster;
	
	@FindBy(xpath = "//p[text()='Work Station Master']")
	WebElement workStationMaster;

//Action Methods
	
	public void clickMaster() {

		master.click();
	}
	
	public void clickPlantMaster() {

		plantMaster.click();
	}
	public void clickSectionMaster() {

		sectionMaster.click();
	}
	public void clickMachinesMaster() {

		machinesMaster.click();
	}
	
	public void clickProductsMaster() {

		productsMaster.click();
	}
	
	public void clickItemMaster() {

		itemMaster.click();
	}
	
	public void clickCustomerMaster() {

		customerMaster.click();
	}
	public void clickDepartmentMaster() {

		departmentMaster.click();
	}

	public void clickWorkStationMaster() {

		workStationMaster.click();
	}
}




	