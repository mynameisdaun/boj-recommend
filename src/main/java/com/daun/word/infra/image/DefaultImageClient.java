package com.daun.word.infra.image;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
public class DefaultImageClient implements ImageClient{
    private Logger logger = LoggerFactory.getLogger(DefaultImageClient.class);
    private final String dir;

    @Override
    public String writeFromMultipartFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        try {
            //todo: 위치 바꿔야 한다. 개발 과정이라 일시적으로 한다.
            String time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
            File dest = new File(dir + time + "_" + originalFileName);
            file.transferTo(dest);
            return dest.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("error");
        }
    }
}
