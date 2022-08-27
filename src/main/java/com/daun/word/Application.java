package com.daun.word;

import com.daun.word.infra.excel.DefaultExcelClient;
import com.daun.word.infra.excel.ExcelClient;
import com.daun.word.infra.image.DefaultImageClient;
import com.daun.word.infra.kakao.client.DefaultKakaoOAuthClient;
import com.daun.word.infra.kakao.client.KakaoOAuthClient;
import com.daun.word.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {

    @Value("${OAuth.jwt.access.key}")
    private String accessKey;
    @Value("${OAuth.jwt.refresh.key}")
    private String refreshKey;
    @Value("${OAuth.jwt.duration.access}")
    private Long accessExpiresIn;
    @Value("${OAuth.jwt.duration.refresh}")
    private Long refreshExpiresIn;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public KakaoOAuthClient kakaoOAuthClient() {
        return new DefaultKakaoOAuthClient(restTemplate());
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils(accessKey, refreshKey, accessExpiresIn, refreshExpiresIn);
    }

    @Bean
    public ExcelClient excelClient() {
        return new DefaultExcelClient("/Users/jeongdaun/Desktop/word/src/main/resources/excel/");
    }

    @Bean
    public DefaultImageClient imageClient() {
        return new DefaultImageClient("/Users/jeongdaun/Desktop/word/src/main/resources/image/");
    }

}
