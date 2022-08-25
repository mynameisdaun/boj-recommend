package com.daun.word.infra.excel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcelReaderTest {

    ExcelReader excelReader = new ExcelReader();

    @DisplayName(value = "엑셀 파일을 읽는다")
    @Test
    void read() throws Exception {
        //given
        excelReader.read();
        //when

        //then

    }

}
