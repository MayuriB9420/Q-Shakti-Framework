package TestCases;

import org.testng.annotations.Test;
import PageObject.Checklist_Control_Plan_VAC_Dashboard_QMS;
import PageObject.Process_Work_Flow_VAC_QMS_PO;
import PageObject.QMS_Dashboard;
import PageObject.VAC_Dashboard_QMS;
import Utilities.ExcelUtility; 
import testBase.BaseClassQMS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QMS_VAC_Process_Work_Flow_TC_ extends BaseClassQMS{
    
    
    private static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "\\TestData\\VAC_ProcessWorkFlow.xlsx";
    private static final String SHEET_NAME = "Sheet1";
    
  
    private Map<String, String> processNameMap = new HashMap<>();

    @Test
    public void process_work_flow() throws Exception {
        logger.info("********** Starting QMS_VAC_Process_Work_Flow_TC **********");
       
        testLoginFunctionality(p.getProperty("emailqms"), p.getProperty("pwdqms"));
        QMS_Dashboard qms = new QMS_Dashboard(driver);
        VAC_Dashboard_QMS vac = new VAC_Dashboard_QMS(driver);
        Checklist_Control_Plan_VAC_Dashboard_QMS checklist = new Checklist_Control_Plan_VAC_Dashboard_QMS(driver);
        Process_Work_Flow_VAC_QMS_PO processPage = new Process_Work_Flow_VAC_QMS_PO(driver); 

        qms.clickVAC();
        logger.info("Clicked on VAC module");

        vac.clickControlPlan();
        logger.info("Clicked on VAC_Control Plan");

        checklist.clickProcessWorkFlow(); 
        logger.info("Clicked on Process Work Flow link"); 
        
        processPage.clickCreateProcessWorkFlow();
        logger.info("Clicked on Create Process Work Flow button");
        
        processPage.selectProductDropdown("Aluminum Rods"); 
        processPage.selectSectionDropdown("Welding Section");
        processPage.selectItemDropdown("Screw");
        logger.info("Selected Product, Section, and Item.");

        
        List<Map<String, String>> processDataList = ExcelUtility.readData(EXCEL_FILE_PATH, SHEET_NAME);
        logger.info("Successfully read " + processDataList.size() + " rows from Excel.");

        for (int i = 0; i < processDataList.size(); i++) {
            Map<String, String> row = processDataList.get(i);
            
            String srNo = row.get("Sr. No");
            String processName = row.get("Process");
            String parameters = row.get("Parameters");
            String dependency = row.get("Dependency");
            String cycleTime = row.get("Cycle Time");
            String action = row.get("Action");
            
            logger.info("Processing Sr. No: " + srNo + ", Process: " + processName);

            if (!srNo.equals("1")) {
                
                if (srNo.matches("\\d+\\.0")) {
                    srNo = String.valueOf((int) Double.parseDouble(srNo)); 
                }

                if (srNo.matches("\\d+")) { 
                    logger.info("Action: Clicking 'Add Process' button for main process " + srNo);
                    processPage.clickAddNewMainProcess();
                
                } else if (srNo.matches("\\d+\\.\\d+")) { 
                   String parentSrNo = srNo.substring(0, srNo.lastIndexOf('.'));
                    logger.info("Action: Clicking '+' on parent row " + parentSrNo + " to create sub-process " + srNo);
                    processPage.clickAddSubProcess(parentSrNo);
                }
            }
            
            processPage.typeProcessName(srNo, processName);
            processPage.typeParameters(srNo, parameters);
            processPage.selectDependency(srNo, dependency);
            processPage.typeCycleTime(srNo, cycleTime);  
        }
        processPage.clickSubmitBtn();
    }
}