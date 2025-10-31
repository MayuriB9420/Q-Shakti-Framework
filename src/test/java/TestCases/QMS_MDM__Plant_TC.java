package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObject.MDM_Dashboard_QMS;
import PageObject.MDM_PlantMaster_QMS;
import PageObject.QMS_Dashboard;
import testBase.BaseClassQMS;

public class QMS_MDM__Plant_TC extends BaseClassQMS {

    QMS_Dashboard qms;
    MDM_Dashboard_QMS mdm;
    MDM_PlantMaster_QMS plant;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() throws Exception {
        qms = new QMS_Dashboard(driver);
        mdm = new MDM_Dashboard_QMS(driver);
        plant = new MDM_PlantMaster_QMS(driver);
        qms.clickMDM();
    	mdm.clickMaster();
    	mdm.clickPlantMaster();
    }

	@Test
	public void mdm_plant_uploadFile() throws Exception
	{
		plant.uploadFile("C:\\Users\\user\\Downloads\\Plant_Master_Template.xlsx");
		String toast = plant.getToastMessage();
	    System.out.println("Toast Message: " + toast);
	
	    Assert.assertTrue(
	            toast.contains("record updated") || toast.contains("record reactivated"),
	            "Test failed because: " + toast
	        );
	}
	
	@Test
	public void addNewButton() {
		plant.clickAddNewButton();
	}

}
