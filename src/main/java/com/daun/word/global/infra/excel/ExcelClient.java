package com.daun.word.global.infra.excel;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelClient {

    void read(String filePath) throws IOException;

    String writeFromMultipartFile(MultipartFile file) throws IOException;
}
