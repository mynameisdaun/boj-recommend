package com.daun.word.global.infra.kakao.client;

import com.daun.word.global.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.global.infra.kakao.dto.KakaoTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.daun.word.global.utils.StringUtils.isNullOrBlank;

@Service
@Slf4j
public class DefaultKakaoOAuthClient implements KakaoOAuthClient {

    private final RestTemplate restTemplate;


    public DefaultKakaoOAuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public KakaoTokenResponse token(String authCode, String restApiKey, String redirectUri) {
        if (isNullOrBlank(authCode)) {
            throw new IllegalArgumentException("authCode는 빈 값이 될 수 없습니다.");
        }
        String url = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&" +
                "client_id=" + restApiKey + "&redirect_uri=" + redirectUri + "&code=" + authCode;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                KakaoTokenResponse.class);
        return response.getBody();
    }

    @Override
    public KakaoProfileResponse profile(String accessToken) {
        //TODO: 3rd party api end point 어떻게 관리하면 좋을까?
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
        return response.getBody();
    }
}

