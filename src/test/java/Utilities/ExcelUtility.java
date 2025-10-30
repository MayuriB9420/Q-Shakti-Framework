package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    private Workbook workbook;
    private Sheet sheet;
    private DataFormatter formatter = new DataFormatter(); 

    
    public ExcelUtility(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.err.println("[ExcelUtility] ERROR: Sheet '" + sheetName + "' not found in file: " + filePath);
            System.err.println("[ExcelUtility] Available sheets:");
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                System.err.println("  - '" + workbook.getSheetName(i) + "'");
            }
        }
    }

    public int getRowCount() {
        int lastRow = sheet.getLastRowNum();   
        int count = 0;
        for (int i = 0; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            // Count rows only if the first cell (Sr. No.) is not null/empty, using the robust formatter
            if (row != null && row.getCell(0) != null && !formatter.formatCellValue(row.getCell(0)).trim().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public int getCellCount(int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        return (row == null) ? 0 : row.getLastCellNum();
    }

    
    public String getCellData(int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) return "";
        Cell cell = row.getCell(colIndex);
        if (cell == null) return "";

        // Use the instance DataFormatter
        return formatter.formatCellValue(cell).trim();
    }


   
    public void close() throws IOException {
        workbook.close();
    }
    
    private static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        Cell cell0 = row.getCell(0);
        if (cell0 != null && !getCellValue(cell0).trim().isEmpty()) {
            return false;
        }
        Cell cell1 = row.getCell(1);
        if (cell1 != null && !getCellValue(cell1).trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public static List<Map<String, String>> getProcessWorkflowData(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> processData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet '" + sheetName + "' not found in the workbook.");
            }

            Iterator<Row> rows = sheet.iterator();
            if (rows.hasNext()) {
                rows.next(); // Skip the header row (row 0)
            }

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (isRowEmpty(currentRow)) {
                    continue;
                }
                
                Map<String, String> rowData = new HashMap<>();

                
                rowData.put("srNo", getCellValue(currentRow.getCell(0)));
                rowData.put("processName", getCellValue(currentRow.getCell(1)));
                rowData.put("parameters", getCellValue(currentRow.getCell(2)));
                rowData.put("checklist", getCellValue(currentRow.getCell(3)));
                rowData.put("attachments", getCellValue(currentRow.getCell(4)));
                
                rowData.put("dependency", getCellValue(currentRow.getCell(5)));
                rowData.put("cycleTime", getCellValue(currentRow.getCell(6)));
                rowData.put("approval", getCellValue(currentRow.getCell(7)));
                rowData.put("addSubProcess", getCellValue(currentRow.getCell(8)));
                
                processData.add(rowData);
            }
        }
        return processData;
    }
    
    
    public static List<Map<String, String>> getChecklistData(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> checklistData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            Iterator<Row> rows = sheet.iterator();
            if (rows.hasNext()) {
                rows.next(); // Skip header
            }

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (isRowEmpty(currentRow)) {
                    continue;
                }
                
                Map<String, String> rowData = new HashMap<>();

                rowData.put("category", getCellValue(currentRow.getCell(0)));
                rowData.put("parameter", getCellValue(currentRow.getCell(1)));
                rowData.put("type", getCellValue(currentRow.getCell(2)));
                rowData.put("frequency", getCellValue(currentRow.getCell(3)));
                rowData.put("sampleSize", getCellValue(currentRow.getCell(4)));
                rowData.put("check", getCellValue(currentRow.getCell(5)));
                checklistData.add(rowData);
            }
        }
        return checklistData;
    }
     
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }
    
    
public static List<Map<String, String>> readData(String filePath, String sheetName) throws IOException {
        
        List<Map<String, String>> dataList = new ArrayList<>();
        FileInputStream fileInputStream = null;
        Workbook workbook = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream); // For .xlsx files
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the workbook.");
            }
            Row headerRow = sheet.getRow(sheet.getFirstRowNum());
            if (headerRow == null) {
                throw new IllegalStateException("The sheet is empty or header row is missing.");
            }
            
            List<String> header = new ArrayList<>();
            for (Cell cell : headerRow) {
                header.add(getCellValueAsString(cell));
            }
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);
                if (dataRow == null) {
                    continue; // Skip empty rows
                }

                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int j = 0; j < header.size(); j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String headerName = header.get(j);
                    String cellValue = getCellValueAsString(cell);
                    
                    rowMap.put(headerName, cellValue);
                }
                dataList.add(rowMap);
            }

        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return dataList;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        DataFormatter formatter = new DataFormatter();
        String cellValue = formatter.formatCellValue(cell).trim();
        if (cell.getCellType() == CellType.NUMERIC) {
            if (cellValue.matches("\\d+\\.0")) {
                return cellValue.substring(0, cellValue.indexOf('.'));
            }
        }
        return cellValue;
    }

}
