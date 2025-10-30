package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObject.MDM_Dashboard_QMS;
import PageObject.MDM_Machine_Master_QMS;
import PageObject.QMS_Dashboard;
import testBase.BaseClassQMS;

public class QMS_MDM_Machine_TC extends BaseClassQMS{
	QMS_Dashboard qms;
    MDM_Dashboard_QMS mdm;
    MDM_Machine_Master_QMS machine;

    @BeforeMethod(alwaysRun = true)
    public void setupTest() throws Exception {
        qms = new QMS_Dashboard(driver);
        mdm = new MDM_Dashboard_QMS(driver);
        machine = new MDM_Machine_Master_QMS(driver);
        qms.clickMDM();
		mdm.clickMaster();
		mdm.clickMachinesMaster();
    }
    
    @Test	
	public void mdm_machine_uploadFile() throws Exception
	{
    	machine.uploadFile("C:\\Users\\user\\Downloads\\Machines_Master_Template.xlsx");
			String toast = machine.getToastMessage();
		    System.out.println("Toast Message: " + toast);
	
		    Assert.assertTrue(
		            toast.contains("record updated") || toast.contains("record reactivated") ||toast.contains("record added"),
		            "Test failed because: " + toast
		        ); 
	}
    
    
    @Test
	public void addNewButton() 
	{
    	machine.clickAddNewButton();
	}
}
