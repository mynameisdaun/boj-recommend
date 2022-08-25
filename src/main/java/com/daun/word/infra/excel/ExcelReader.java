package com.daun.word.infra.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    /* 엑셀 파일 읽기 */
    public void read() throws IOException {
        FileInputStream file = new FileInputStream("src/main/resources/excel/test.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        int rowIndex = 0;
        int colIndex = 0;
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        for (rowIndex = 0; rowIndex < rows; rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                int cells = row.getPhysicalNumberOfCells();
                String value = "";
                for (int col = 0 ; col < cells ; col ++) {
                    XSSFCell cell = row.getCell(col);
                    cell.getCellType();
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case FORMULA:
                                value += cell.getCellFormula();
                                break;
                            case NUMERIC:
                                value += cell.getNumericCellValue() + "";
                                break;
                            case STRING:
                                value += cell.getStringCellValue() + "";
                                break;
                            case BOOLEAN:
                                value += cell.getBooleanCellValue() + "";
                                break;
                        }
                    }
                    value += " ";
                }
                System.out.println(value);
            }
        }
    }
}
