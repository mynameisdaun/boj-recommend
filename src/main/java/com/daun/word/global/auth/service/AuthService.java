package com.daun.word.global.auth.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.global.auth.dto.LoginResponse;
import com.daun.word.global.infra.kakao.client.KakaoOAuthClient;
import com.daun.word.global.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.global.infra.kakao.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;

    private final KakaoOAuthClient kakaoOAuthClient;




    @Value("${OAuth.kakao.rest_api_key}")
    private String REST_API_KEY;
    @Value("${OAuth.kakao.redirect_uri}")
    private String REDIRECT_URI;

    @Transactional
    public LoginResponse kakaoLogin(String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoOAuthClient.token(code, REST_API_KEY, REDIRECT_URI);
        KakaoProfileResponse kakaoProfile = kakaoOAuthClient.profile(kakaoTokenResponse.getAccess_token());
        Member member = memberService.findByEmail(kakaoProfile.getEmail());
        /* 우리 서버에 등록되지 않은 회원이라면 회원가입 처리 한다 */
        /* TODO: 이거 바뀌어야 된다.*/
        throw new NotImplementedException("");
        /*if (member == null) {
            RegisterRequest request = new RegisterRequest(kakaoProfile.getEmail(), null, kakaoProfile.getNickname(), SocialType.K);
            member = memberService.register(request);
        }
        *//* TOKEN 발급 *//*
        Token token = tokenService.createToken(member, kakaoTokenResponse);
        //return new LoginResponse(member, token);*/
    }
}
