package com.daun.word.infra.kakao.client;

import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@Service
public class DefaultKakaoOAuthClient implements KakaoOAuthClient {

    private final Logger logger = LoggerFactory.getLogger(DefaultKakaoOAuthClient.class);

    private final RestTemplate restTemplate;
    @Value("${OAuth.kakao.rest_api_key}")
    private String REST_API_KEY;
    @Value("${OAuth.kakao.redirect_uri}")
    private String REDIRECT_URI;

    public DefaultKakaoOAuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public KakaoTokenResponse token(String authCode) {
        if (isNullOrBlank(authCode)) {
            throw new IllegalArgumentException("authCode는 빈 값이 될 수 없습니다.");
        }
        String url = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&" +
                "client_id=" + REST_API_KEY + "&redirect_uri=" + REDIRECT_URI + "&code=" + authCode;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                KakaoTokenResponse.class);
        logger.info(response.getBody().toString());
        return response.getBody();
    }

    @Override
    public KakaoProfileResponse profile(String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<KakaoProfileResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                KakaoProfileResponse.class);
        logger.info(response.getBody().toString());
        return response.getBody();
    }
}

