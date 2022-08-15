package com.daun.word.oauth.service;

import com.daun.word.oauth.dto.KakaoAccessTokenResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoOAuthClient {
    //TODO: restapikey 숨기기
    private String REST_API_KEY = "dcb22091e397cc3e33bfc4a7d502022d";
    private String REDIRECT_URI = "http://localhost:3000/oauth/kakao/callback";
    private final String TOKEN_HOST = "https://kauth.kakao.com";
    private final String TOKEN_URL = "/oauth/token";

    public ResponseEntity<?> token(String code) {
        StringBuilder sb = new StringBuilder(TOKEN_HOST);
        System.out.println("here is code!!");
        System.out.println(code);
        sb.append(TOKEN_URL)
          .append("?grant_type=authorization_code")
          .append("&client_id=").append(REST_API_KEY)
          .append("&redirect_uri=").append(REDIRECT_URI)
          .append("&code=").append(code);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity tokenRequest = new HttpEntity(tokenHeaders);
        KakaoAccessTokenResponse tokenResponse = restTemplate.postForObject(sb.toString(), tokenRequest, KakaoAccessTokenResponse.class);
        System.out.println(tokenResponse.toString());
        return ResponseEntity.ok(tokenResponse);
    }
}
