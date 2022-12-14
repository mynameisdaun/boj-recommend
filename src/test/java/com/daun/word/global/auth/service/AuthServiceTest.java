package com.daun.word.global.auth.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

//    @InjectMocks
//    private AuthService AuthService;
//
//    @Mock
//    private MemberService memberService;
//
//    @Mock
//    private KakaoOAuthClient kakaoOAuthClient;
//
//    @Mock
//    private TokenService tokenService;
//
//
//    @DisplayName(value = "카카오 로그인이 성공하면 토큰을 발급한다")
//    @Test
//    void kakaoLogin_success() throws Exception {
//        Token token = token();
//        //given
//        given(kakaoOAuthClient.token(any(), any(), any()))
//                .willReturn(kakaoTokenResponse());
//        given(kakaoOAuthClient.profile(any(String.class)))
//                .willReturn(kakaoProfileResponse());
//        given(memberService.findByEmail(any(Email.class)))
//                .willReturn(member());
//        given(tokenService.createToken(member(), kakaoTokenResponse()))
//                .willReturn(token);
//        //when
//        LoginResponse loginResponse = AuthService.kakaoLogin("fake-auth-code");
//        //then
//        verify(kakaoOAuthClient, times(1)).token(any(), any(), any());
//        verify(kakaoOAuthClient, times(1)).profile(any(String.class));
//        verify(memberService, times(1)).findByEmail(any(Email.class));
//        verify(memberService, times(0)).register(any(RegisterRequest.class));
//        verify(tokenService, times(1)).createToken(any(Member.class), any(KakaoTokenResponse.class));
//        assertThat(loginResponse).isNotNull();
//        assertAll(
//                () -> assertThat(loginResponse.getMember()).isEqualTo(member())
//        );
//    }
//
//    @DisplayName(value = "등록이 되지 않은 회원이라면 카카오 로그인 성공 후 회원가입, 토큰을 발급한다")
//    @Test
//    void kakaoLogin_success_register() throws Exception {
//        Token token = token();
//        //given
//        given(kakaoOAuthClient.token(any(), any(), any()))
//                .willReturn(kakaoTokenResponse());
//        given(kakaoOAuthClient.profile(any(String.class)))
//                .willReturn(kakaoProfileResponse());
//        given(memberService.findByEmail(any(Email.class)))
//                .willReturn(null);
//        given(memberService.register(any(RegisterRequest.class)))
//                .willReturn(new MemberDTO(member()));
//        given(tokenService.createToken(member(), kakaoTokenResponse()))
//                .willReturn(token);
//        //when
//        LoginResponse loginResponse = AuthService.kakaoLogin("fake-auth-code");
//        //then
//        verify(kakaoOAuthClient, times(1)).token(any(), any(), any());
//        verify(kakaoOAuthClient, times(1)).profile(any(String.class));
//        verify(memberService, times(1)).findByEmail(any(Email.class));
//        verify(memberService, times(1)).register(any(RegisterRequest.class));
//        verify(tokenService, times(1)).createToken(any(Member.class), any(KakaoTokenResponse.class));
//        assertThat(loginResponse).isNotNull();
//        assertAll(
//                () -> assertThat(loginResponse.getMember()).isEqualTo(member())
//        );
//    }
}
