package TestCases;

import org.testng.annotations.Test;

import PageObject.Checklist_Control_Plan_VAC_Dashboard_QMS;
import PageObject.Process_Work_Flow_VAC_QMS;
import PageObject.QMS_Dashboard;
import PageObject.VAC_Dashboard_QMS;
import Utilities.ExcelUtility;
import testBase.BaseClassQMS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QMS_VAC_Process_Work_Flow_TC extends BaseClassQMS{
    
    
    private static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "\\TestData\\ProcessFlow.xlsx";
    private static final String SHEET_NAME = "Sheet1";
    
  
    private Map<String, String> processNameMap = new HashMap<>();

    @Test
    public void process_work_flow() throws Exception {
        logger.info("********** Starting QMS_VAC_Process_Work_Flow_TC **********");
       
        testLoginFunctionality(p.getProperty("emailqms"), p.getProperty("pwdqms"));
        QMS_Dashboard qms = new QMS_Dashboard(driver);
        VAC_Dashboard_QMS vac = new VAC_Dashboard_QMS(driver);
        Checklist_Control_Plan_VAC_Dashboard_QMS checklist = new Checklist_Control_Plan_VAC_Dashboard_QMS(driver);
        Process_Work_Flow_VAC_QMS process = new Process_Work_Flow_VAC_QMS(driver);

        qms.clickVAC();
        logger.info("Clicked on VAC module");

        vac.clickControlPlan();
        logger.info("Clicked on VAC_Control Plan");

        checklist.clickProcessWorkFlow(); 
        logger.info("Clicked on Process Work Flow link"); 
        
        process.clickCreateProcessWorkFlow();
        logger.info("Clicked on Create Process Work Flow button");
        
        process.selectProductDropdown("Aluminum Rods");
        process.selectSectionDropdown("Welding Section");
        process.selectItemDropdown("Screw");
        logger.info("Selected Product, Section, and Item.");
        
        populateProcessGrid(process);
        
        logger.info("********** Completed QMS_VAC_Process_Work_Flow_TC **********");
    } 
   
    private void populateProcessGrid(Process_Work_Flow_VAC_QMS process) throws Exception {
        try {
            List<Map<String, String>> processDataList = ExcelUtility.getProcessWorkflowData(EXCEL_FILE_PATH, SHEET_NAME);
            logger.info("Successfully read " + processDataList.size() + " rows from Excel.");
            
            int currentRowIndex = 0;
            String previousSrNo = ""; 

            for(int i = 0; i < processDataList.size(); i++) {
                Map<String, String> rowData = processDataList.get(i);
                
                String srNo = rowData.get("srNo");
                String processName = rowData.get("processName");
                String parameters = rowData.get("parameters");
                String dependencyRef = rowData.get("dependency");
                String cycleTime = rowData.get("cycleTime");
                String addSubProcessAction = rowData.get("addSubProcess");
                
                logger.info(String.format("Populating Row %s (UI Index %d): %s", srNo, currentRowIndex, processName));

                // 1. Enter main data fields
                process.enterProcessName(currentRowIndex, processName);
                process.enterProcessParameters(currentRowIndex, parameters);
                process.enterCycleTime(currentRowIndex, cycleTime);
                
                // 2. Store Process Name for future dependency lookups
                processNameMap.put(srNo, processName); 
                
                // 3. Handle Dependency Lookup
                String dependencyToSelect = "Select"; // Default for no dependency
                if (dependencyRef != null && !dependencyRef.trim().equalsIgnoreCase("None") && !dependencyRef.trim().isEmpty()) {
                    // Check if the dependency is a reference number (e.g., "1.1")
                    if (processNameMap.containsKey(dependencyRef)) {
                        dependencyToSelect = processNameMap.get(dependencyRef);
                    } else {
                        dependencyToSelect = dependencyRef;
                    }
                }
                
                if (!dependencyToSelect.equalsIgnoreCase("Select")) {
                    process.selectDependency(currentRowIndex, dependencyToSelect);
                    logger.info("-> Dependency set to: " + dependencyToSelect);
                }
                
                if (addSubProcessAction != null && addSubProcessAction.trim().equalsIgnoreCase("YES")) {
                    process.clickAddSubProcess(currentRowIndex);
                    currentRowIndex++;
                    
                } else {
                    int nextIndex = i + 1;
                    if (nextIndex < processDataList.size()) {
                        String nextSrNo = processDataList.get(nextIndex).get("srNo");
                        if (!nextSrNo.contains(".")) {
                          process.clickAddProcess(currentRowIndex + 1); 
                            logger.info("-> Clicked 'Add Process' button for Sr.No " + nextSrNo);
                        }
                    }
                    currentRowIndex++;
                }               
                }process.clickSubmit();
            
        } catch (Exception e) {
            logger.error("Error during process grid population: " + e.getMessage(), e);
            throw e;
        }
    }
}
