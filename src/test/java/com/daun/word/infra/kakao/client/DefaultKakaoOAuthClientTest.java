package com.daun.word.infra.kakao.client;

import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultKakaoOAuthClientTest {
    //TODO: happy Case 완성 했으나, worse case도 진행해야 한다

    @InjectMocks
    DefaultKakaoOAuthClient kakaoOAuthClient;

    @Mock
    RestTemplate restTemplate;

    @DisplayName(value = "카카오로부터 액세스 토큰을 얻습니다.")
    @Test
    void token_success() throws Exception {
        //given
        String authCode = "fake-Auth-Code";
        given(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .willReturn(ResponseEntity.of(Optional.of(kakaoTokenResponse())));
        //when
        KakaoTokenResponse response = kakaoOAuthClient.token(authCode, FAKE_KAKAO_REST_API_KEY, FAKE_KAKAO_REDIRECT_URI);
        //then
        verify(restTemplate, times(1)).exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class));
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(kakaoTokenResponse());
    }

    @DisplayName(value = "카카오로부터 프로필을 얻습니다.")
    @Test
    void profile_success() throws Exception {
        String accessToken = "fake-Auth-Code";
        given(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .willReturn(ResponseEntity.of(Optional.of(kakaoProfileResponse())));
        //when
        KakaoProfileResponse response = kakaoOAuthClient.profile(accessToken);
        //then
        verify(restTemplate, times(1)).exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class));
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(kakaoProfileResponse());
    }
}
