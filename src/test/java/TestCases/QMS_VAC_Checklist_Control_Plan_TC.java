package TestCases;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import PageObject.Checklist_Control_Plan_VAC_Dashboard_QMS;
import PageObject.QMS_Dashboard;
import PageObject.VAC_Dashboard_QMS;
import Utilities.ExcelUtility;
import testBase.BaseClassQMS;

public class QMS_VAC_Checklist_Control_Plan_TC extends BaseClassQMS{
	@Test
	public void checklist_control_plan() throws Exception {
//		testLoginFunctionality(p.getProperty("emailqms"), p.getProperty("pwdqms"));
	    QMS_Dashboard qms = new QMS_Dashboard(driver);
	    VAC_Dashboard_QMS vac = new VAC_Dashboard_QMS(driver);
	    Checklist_Control_Plan_VAC_Dashboard_QMS checklist = new Checklist_Control_Plan_VAC_Dashboard_QMS(driver);

	    logger.info("********** Started VAC_Checklist_Control_Plan_TC **********");
	    qms.clickVAC();
	    logger.info("Clicked on VAC module");

	    vac.clickControlPlan();
	    logger.info("Clicked on VAC_Control Plan");

	    checklist.clickChecklist();
	    logger.info("Clicked on Checklist"); 
	    
	    checklist.clickCreateChecklist();
	    checklist.enterChecklistName("Auto_Checklist_01");
	    checklist.selectSectionDropdown("Welding Section");
	    checklist.selectDepartmentDropdown("Operations");
	    checklist.selectProductDropdown("Aluminum Rods");
	    checklist.selectWorkingStationDropdown("Workstation-25");
	    
	    String filePath = System.getProperty("user.dir") + "/Testdata/ChecklistData_ControlPlan_QMS.xlsx";
	    List<Map<String, String>> data = ExcelUtility.getChecklistData(filePath, "Sheet1");

	    logger.info("Number of data rows read from Excel: " + data.size()); 
        
	    int excelRowIndex = 0;          
        int categoryBlockIndex = 0;     
        int characteristicRowIndex = 0; 

	    while (excelRowIndex < data.size()) {
	        Map<String, String> rowData = data.get(excelRowIndex);
	        String category = rowData.get("category");
	        String parameter = rowData.get("parameter");
            if (excelRowIndex > 0 && category != null && !category.isEmpty()) {
                logger.info("New category found: " + category + ". Clicking Add Category button.");  
                checklist.clickAddCategoryButton();
                categoryBlockIndex++;
                characteristicRowIndex = 0; 
            }
           
	        if (category != null && !category.isEmpty()) {
	            checklist.enterCategory(category, categoryBlockIndex); 
	        }
            
            if (parameter != null && !parameter.trim().isEmpty()) {
                 checklist.enterCharacteristic(parameter, categoryBlockIndex, characteristicRowIndex);
                 checklist.selectTypeDropdown(rowData.get("type"), categoryBlockIndex, characteristicRowIndex);
                 checklist.selectFrequencyDropdown(rowData.get("frequency"), categoryBlockIndex, characteristicRowIndex);
                 checklist.enterSampleSize(rowData.get("sampleSize"), categoryBlockIndex, characteristicRowIndex);

                 if ("Yes".equalsIgnoreCase(rowData.get("check"))) {
                     checklist.clickCTQRadioButton(categoryBlockIndex, characteristicRowIndex);
                 }
            }
	        if (excelRowIndex < data.size() - 1) { 
                Map<String, String> nextRowData = data.get(excelRowIndex + 1);
                String nextCategory = nextRowData.get("category");
               
                if (nextCategory == null || nextCategory.isEmpty() || nextCategory.trim().isEmpty()) {
                    checklist.clickAddRowButton(categoryBlockIndex);
                    checklist.waitForNewRowToAppear(characteristicRowIndex + 2); 
                }
	        }
	        excelRowIndex++;            
            characteristicRowIndex++;   
	    } 
	    checklist.clickSaveChecklistButton();
//	    Assert.assertTrue(checklist.isToastMessageDisplayed("Checklist created successfully"), "Checklist creation failed or success message not displayed.");
	    logger.info("********** Completed VAC_Checklist_Control_Plan_TC **********");
    }
}