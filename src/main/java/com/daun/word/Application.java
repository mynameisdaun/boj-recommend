package com.daun.word;

import com.daun.word.global.infra.image.DefaultImageClient;
import com.daun.word.global.infra.kakao.client.DefaultKakaoOAuthClient;
import com.daun.word.global.infra.kakao.client.KakaoOAuthClient;
import com.daun.word.global.infra.solvedac.DefaultSolvedAcClient;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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
    public SolvedAcClient solvedAcClient() {
        return new DefaultSolvedAcClient(restTemplate());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
