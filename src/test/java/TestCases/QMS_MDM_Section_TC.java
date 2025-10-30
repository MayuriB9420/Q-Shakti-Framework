package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObject.BasePage;
import PageObject.MDM_Dashboard_QMS;
import PageObject.MDM_PlantMaster_QMS;
import PageObject.MDM_Section_Master_QMS;
import PageObject.QMS_Dashboard;
import testBase.BaseClassQMS;

public class QMS_MDM_Section_TC extends BaseClassQMS {
	QMS_Dashboard qms;
    MDM_Dashboard_QMS mdm;
    MDM_Section_Master_QMS section;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() throws Exception {
        qms = new QMS_Dashboard(driver);
        mdm = new MDM_Dashboard_QMS(driver);
        section = new MDM_Section_Master_QMS(driver);
        qms.clickMDM();
		mdm.clickMaster();
		mdm.clickSectionMaster();
    }
	@Test	
	public void mdm_section_uploadFile() throws Exception
	{
			section.uploadFile("C:\\Users\\user\\Downloads\\Section_Master_Template.xlsx");
			String toast = section.getToastMessage();
		    System.out.println("Toast Message: " + toast);
	
		    Assert.assertTrue(
		            toast.contains("record updated") || toast.contains("record reactivated"),
		            "Test failed because: " + toast
		        ); 
	}
	
	
	@Test
	public void addNewButton() 
	{
		section.clickAddNewButton();
	}
		
}
