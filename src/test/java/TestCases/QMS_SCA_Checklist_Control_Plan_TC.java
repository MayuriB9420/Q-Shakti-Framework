package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObject.Checklist_Control_Plan_SCA_Dashboard_QMS;
import PageObject.MDM_Dashboard_QMS;
import PageObject.MDM_PlantMaster_QMS;
import PageObject.QMS_Dashboard;
import PageObject.SCA_Dashboard_QMS;
import testBase.BaseClassQMS;


public class QMS_SCA_Checklist_Control_Plan_TC extends BaseClassQMS{
	QMS_Dashboard qms;
	SCA_Dashboard_QMS sca;
	Checklist_Control_Plan_SCA_Dashboard_QMS checklist;
	
	@BeforeMethod(alwaysRun = true)
    public void setupTest() throws Exception {
//	testLoginFunctionality(p.getProperty("emailqms"), p.getProperty("pwdqms"));
	qms=new QMS_Dashboard(driver);
	sca= new SCA_Dashboard_QMS(driver);
	checklist = new Checklist_Control_Plan_SCA_Dashboard_QMS(driver);
	}
	
	@Test
	public void checklist_control_plan() throws Exception
	{
		logger.info("********** Started Checklist_Control_Plan_TC **********");
		qms.clickSCA();
	
		logger.info("Clicked on SCA module");
		
		sca.clickControlPlan();
		logger.info("Clicked on Control Plan");
		
		checklist.clickChecklist();		
		logger.info("Clicked on Checklist");
		
		checklist.clickCreateChecklist();
		checklist.enterChecklistName("Auto_Checklist_01");
		checklist.selectSectionDropdown("Welding Section");
		checklist.selectDepartmentDropdown("Operations");
		checklist.selectProductDropdown("Aluminum Rods");
		checklist.selectWorkingStationDropdown("Workstation-25");
		checklist.enterCategory("Auto_Category_01");
			
	}}	

