package Utilities;
//import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.DataProvider;

public class DataProviders {

	   @DataProvider(name = "CreateUserData")
	    public Object[][] getUserData() throws IOException {
	       // String filePath = System.getProperty("user.dir") + "/TestData/CreateUserTestData.xlsx";
	       String filePath = System.getProperty("user.dir") + "/TestData/UserManagementData.xlsx";

	        String sheetName = "Users";

	        ExcelUtility excel = new ExcelUtility(filePath, sheetName);
	        int rowCount = excel.getRowCount();
	        int colCount = excel.getCellCount(0);

	        Object[][] data = new Object[rowCount - 1][9];

	        for (int i = 1; i < rowCount; i++) {
	        	
	            String FirstName = excel.getCellData(i, 0);
	            String LastName = excel.getCellData(i, 1);
	            String phone = excel.getCellData(i, 2);
	            String Email= excel.getCellData(i, 3);
	            String EmployeeId= excel.getCellData(i, 4);
	            String SectionsRaw= excel.getCellData(i, 5);
	            List<String> Sections = Arrays.asList(SectionsRaw.split("\\s*,\\s*"));
	            String OperationsRaw= excel.getCellData(i, 6);
	            List<String> operations = Arrays.asList(OperationsRaw.split("\\s*,\\s*"));
	            String MachinesRaw= excel.getCellData(i, 7);
	            List<String> qcMachines = Arrays.asList(MachinesRaw.split("\\s*,\\s*"));
	            String Role= excel.getCellData(i, 8);
	          

	            data[i - 1][0] = FirstName;
	            data[i - 1][1] = LastName;
	            data[i - 1][2] = phone;
	            data[i - 1][3] = Email;
	            data[i - 1][4] = EmployeeId;
	            data[i - 1][5] = Sections;
	            data[i - 1][6] = operations;
	            data[i - 1][7] = qcMachines;
	            data[i - 1][8] = Role;
	        }

	        excel.close();
	        return data;
	    }

        @DataProvider(name = "rolesData")
        public Object[][] getRoleDescriptionData() throws IOException {
            String filePath = System.getProperty("user.dir") + "/TestData/roles_data.xlsx";
            ExcelUtility excel = new ExcelUtility(filePath, "Roles");

            int rowCount = excel.getRowCount();
            Object[][] data = new Object[rowCount - 1][2]; 

            for (int i = 1; i < rowCount; i++) { 
                data[i - 1][0] = excel.getCellData(i, 0); // ROLE
                data[i - 1][1] = excel.getCellData(i, 1); // DESCRIPTION
            }

            excel.close();
            return data;
        }
        
    
    @DataProvider(name = "EditRoleData")
    public static Object[][] getEditRoleData() throws Exception {
        String filePath = System.getProperty("user.dir") + "/TestData/EditRoleTestData.xlsx";
        String sheetName = "EditRoles";  

        ExcelUtility excel = new ExcelUtility(filePath, sheetName);

        int rowCount = excel.getRowCount();
        int colCount = excel.getCellCount(0);

        Object[][] data = new Object[rowCount - 1][4];

        for (int i = 1; i < rowCount; i++) { // start from 1 to skip header
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

    @DataProvider(name = "RMInspectionDataExcel")
    public Object[][] getRMData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/TestData/RmInspectionData.xlsx";
        String sheetName = "InspectionData";

        ExcelUtility excel = new ExcelUtility(filePath, sheetName);
        int rowCount = excel.getRowCount();
        int colCount = excel.getCellCount(0);

        Object[][] data = new Object[rowCount - 1][6];

        for (int i = 1; i < rowCount; i++) {
            String section = excel.getCellData(i, 0);
            String IO = excel.getCellData(i, 1);

            String qcMachinesRaw = excel.getCellData(i, 2);
            List<String> qcMachines = Arrays.asList(qcMachinesRaw.split("\\s*,\\s*"));

            int accepted = Integer.parseInt(excel.getCellData(i, 3));
            int rejected = Integer.parseInt(excel.getCellData(i, 4));
            String remarks = excel.getCellData(i, 5);

            data[i - 1][0] = section;
            data[i - 1][1] = IO;
            data[i - 1][2] = qcMachines;
            data[i - 1][3] = accepted;
            data[i - 1][4] = rejected;
            data[i - 1][5] = remarks;
        }

        excel.close();
        return data;
    }

    @DataProvider(name = "ProductionOrders")
    public Object[][] getProductionOrders() throws Exception {
        String filePath = System.getProperty("user.dir") + "/TestData/ProductionOrdersData.xlsx";
        String sheetName = "Orders";

        ExcelUtility excel = new ExcelUtility(filePath, sheetName);
        int totalRows = excel.getRowCount();
        int totalCols = excel.getCellCount(0);

        // Assuming 1st row = header
        Object[][] data = new Object[totalRows - 1][totalCols];

        for (int i = 1; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = excel.getCellData(i, j);
            }
        }

        excel.close();
        return data;
    }
    
    @DataProvider(name = "RMDetails")
    public Object[][] getRMInspectionDetails() throws Exception {
        String filePath = System.getProperty("user.dir") + "/TestData/RMInspectionDetails.xlsx";
        String sheetName = "DETAILS";

        ExcelUtility excel = new ExcelUtility(filePath, sheetName);
        int totalRows = excel.getRowCount();
        int totalCols = excel.getCellCount(0);

        // Assuming 1st row = header
        Object[][] data = new Object[totalRows - 1][totalCols];

        for (int i = 1; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                data[i - 1][j] = excel.getCellData(i, j);
            }
        }

        excel.close();
        return data;
    }
    
}