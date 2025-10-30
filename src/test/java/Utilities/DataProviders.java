package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import PageObject.ManageAccessPage;
import PageObject.ManageAccessPage.ScreenPermission;
import testBase.BaseClass;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class DataProviders {
	   
	   @DataProvider(name = "CreateUserData")
	    public static Object[][] getUserData() throws Exception {
	        String filePath = System.getProperty("user.dir") + "/TestData/CreateUserTestData.xlsx";
	        String sheetName = "Users";

	        ExcelUtility excel = new ExcelUtility(filePath, sheetName);

	        int rowCount = excel.getRowCount();
	        Object[][] data = new Object[rowCount - 1][9]; // 9 columns

	        for (int i = 1; i < rowCount; i++) { // skip header
	            data[i - 1][0] = excel.getCellData(i, 0).trim(); // FirstName
	            data[i - 1][1] = excel.getCellData(i, 1).trim(); // LastName
	            data[i - 1][2] = excel.getCellData(i, 2).trim(); // Phone
	            data[i - 1][3] = excel.getCellData(i, 3).trim(); // Email
	            data[i - 1][4] = excel.getCellData(i, 4).trim(); // Plant
	            data[i - 1][5] = excel.getCellData(i, 5).trim(); // Section (multi-select string)
	            data[i - 1][6] = excel.getCellData(i, 6).trim(); // Operation (multi-select string)
	            data[i - 1][7] = excel.getCellData(i, 7).trim(); // Machine (multi-select string)
	            data[i - 1][8] = excel.getCellData(i, 8).trim(); // Role
	        }

	        excel.close();
	        return data;
	    }
    

        @DataProvider(name = "rolesData")
        public Object[][] getRoleDescriptionData() throws IOException {
            String filePath = System.getProperty("user.dir") + "/TestData/roles_data.xlsx";
            ExcelUtility excel = new ExcelUtility(filePath, "Sheet1");

            int rowCount = excel.getRowCount();
            Object[][] data = new Object[rowCount - 1][2]; // minus 1 for header

            for (int i = 1; i < rowCount; i++) { // start from 1 to skip header
                data[i - 1][0] = excel.getCellData(i, 0); // ROLE
                data[i - 1][1] = excel.getCellData(i, 1); // DESCRIPTION
            }

            excel.close();
            return data;
        }
    
    @DataProvider(name = "EditRoleData")
    public static Object[][] getEditRoleData() throws Exception {
        String filePath = System.getProperty("user.dir") + "/TestData/EditRoleTestData.xlsx";
        String sheetName = "EditRoles";  // Excel sheet name

        ExcelUtility excel = new ExcelUtility(filePath, sheetName);

        int rowCount = excel.getRowCount();
        int colCount = excel.getCellCount(0);

        // We expect 4 columns: ExistingRole, ExistingDescription, NewRoleName, NewDescription
        Object[][] data = new Object[rowCount - 1][4];

        for (int i = 1; i < rowCount; i++) { // Skip header row
            data[i - 1][0] = excel.getCellData(i, 0); // ExistingRole
            data[i - 1][1] = excel.getCellData(i, 1); // ExistingDescription
            data[i - 1][2] = excel.getCellData(i, 2); // NewRoleName
            data[i - 1][3] = excel.getCellData(i, 3); // NewDescription
        }

        excel.close();
        return data;
    }
    
    @DataProvider(name = "RolePermissionData")
    public String[][] getRolePermissionData() throws Exception {
        String path = ".\\TestData\\RolePermissionData.xlsx";
        ExcelUtility xl = new ExcelUtility(path, "Permissions");

        int rows = xl.getRowCount();
        int cols = 7; // Role + Screen + 5 permissions

        String[][] data = new String[rows][cols];

        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = xl.getCellData(i, j);
            }
        }
        return data;
    }

   /* @DataProvider(name = "RolePermissionData")
    public static Object[][] getRolePermissionData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/TestData/RolePermissionData.xlsx";
        ExcelUtility excel = new ExcelUtility(filePath, "Sheet1");

        Map<String, List<ScreenPermission>> roleMap = new LinkedHashMap<>();

        int rowCount = excel.getRowCount();
        for (int i = 1; i < rowCount; i++) {  // start from 1 to skip header
            String role = excel.getCellData(i, 0);
            String screen = excel.getCellData(i, 1);
            String view = excel.getCellData(i, 2);
            String add = excel.getCellData(i, 3);
            String edit = excel.getCellData(i, 4);
            String delete = excel.getCellData(i, 5);
            String export = excel.getCellData(i, 6);

         
			ManageAccessPage map = new ManageAccessPage(driver);

            ScreenPermission permission = new ScreenPermission(screen, view, add, edit, delete, export);
            

            roleMap.computeIfAbsent(role, k -> new ArrayList<>()).add(permission);
        }

        excel.close();

        // Convert Map to DataProvider format
        Object[][] data = new Object[roleMap.size()][2];
        int index = 0;
        for (Map.Entry<String, List<ScreenPermission>> entry : roleMap.entrySet()) {
            data[index][0] = entry.getKey();                  // Role
            data[index][1] = entry.getValue();                // List<ScreenPermission>
            index++;
        }

        return data;
    }*/

//        @DataProvider(name = "checklistData")
//        public Object[][] getData() throws IOException {
//        	String path = System.getProperty("user.dir") + "/TestData/ChecklistData_QMS.xlsx";
//            FileInputStream fis = new FileInputStream(new File(path));
//            Workbook workbook = new XSSFWorkbook(fis);
//            Sheet sheet = workbook.getSheetAt(0);
//
//            int rows = sheet.getPhysicalNumberOfRows();
//            int cols = sheet.getRow(0).getLastCellNum();
//
//            Object[][] data = new Object[rows - 1][cols];
//
//            for (int i = 1; i < rows; i++) {
//                Row row = sheet.getRow(i);
//                for (int j = 0; j < cols; j++) {
//                    Cell cell = row.getCell(j);
//                    data[i - 1][j] = cell != null ? cell.toString() : "";
//                }
//            }
//
//            workbook.close();
//            fis.close();
//            return data;
//        }
    
    public class ChecklistData {
        private final String characteristic;
        private final String type;
        private final String lsl;
        private final String usl;
        private final String frequency;
        private final String sampleSize;
        private final boolean isCtq;

        public ChecklistData(String characteristic, String type, String lsl, String usl, String frequency, String sampleSize, boolean isCtq) {
            this.characteristic = characteristic;
            this.type = type;
            this.lsl = lsl;
            this.usl = usl;
            this.frequency = frequency;
            this.sampleSize = sampleSize;
            this.isCtq = isCtq;
        }

        // Getters
        public String getCharacteristic() { return characteristic; }
        public String getType() { return type; }
        public String getLsl() { return lsl; }
        public String getUsl() { return usl; }
        public String getFrequency() { return frequency; }
        public String getSampleSize() { return sampleSize; }
        public boolean isCtq() { return isCtq; }
    }
    
    }
