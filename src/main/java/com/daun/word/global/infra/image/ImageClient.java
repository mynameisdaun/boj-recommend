package com.daun.word.global.infra.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageClient {

    String writeFromMultipartFile(MultipartFile file) throws IOException;
}
