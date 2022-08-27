package com.daun.word.infra.excel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExcelClientTest {

    private DefaultExcelClient excelClient = new DefaultExcelClient("/Users/jeongdaun/Desktop/word/src/main/resources/excel/");


    @DisplayName(value = "read")
    @Test
    void read() throws Exception {
        //given
        excelClient.read("/Users/jeongdaun/Desktop/word/src/main/resources/excel/test.xlsx");
        //when

        //then

    }

}
