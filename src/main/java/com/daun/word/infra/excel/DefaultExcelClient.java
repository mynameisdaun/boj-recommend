package com.daun.word.infra.excel;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@RequiredArgsConstructor
public class DefaultExcelClient implements ExcelClient {
    private final Logger logger = LoggerFactory.getLogger(DefaultExcelClient.class);
    //TODO: 변수 명 재 정의 필요할 것 같다
    private final String excelFileDir;

    public String writeFromMultipartFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        try {
            //todo: 위치 바꿔야 한다. 개발 과정이라 일시적으로 한다.
            String time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
            File dest = new File(excelFileDir + time + "_" + originalFileName);
            file.transferTo(dest);
            return dest.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("error");
        }
    }

    /* 엑셀 파일 읽기 */
    public void read(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        int rowIndex = 0;
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        for (rowIndex = 0; rowIndex < rows; rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                int cells = row.getPhysicalNumberOfCells();
                String value = "";
                for (int col = 0; col < cells; col++) {
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
