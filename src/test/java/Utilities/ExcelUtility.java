package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    private Workbook workbook;
    private Sheet sheet;

    
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
            if (row != null && row.getCell(0) != null && !row.getCell(0).toString().trim().isEmpty()) {
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

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }


   
    public void close() throws IOException {
        workbook.close();
    }
}