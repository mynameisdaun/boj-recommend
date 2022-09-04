package com.daun.word.auth.service;

import com.daun.word.auth.dto.AuthenticationRequest;
import com.daun.word.auth.dto.LoginResponse;
import com.daun.word.auth.token.domain.Token;
import com.daun.word.auth.token.service.TokenService;
import com.daun.word.infra.kakao.client.KakaoOAuthClient;
import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.member.dto.RegisterRequest;
import com.daun.word.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;

    private final KakaoOAuthClient kakaoOAuthClient;

    private final TokenService tokenService;



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
