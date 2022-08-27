package com.daun.word.infra.excel;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelClient {

    public void read(String filePath) throws IOException;

    public String writeFromMultipartFile(MultipartFile file) throws IOException;
}
