package com.daun.word.oauth.service;

import com.daun.word.infra.kakao.client.KakaoOAuthClient;
import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.member.dto.RegisterRequest;
import com.daun.word.member.service.MemberService;
import com.daun.word.oauth.dto.LoginResponse;
import com.daun.word.oauth.token.domain.Token;
import com.daun.word.oauth.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthService {

    @Value("${OAuth.kakao.rest_api_key}")
    private String REST_API_KEY;
    @Value("${OAuth.kakao.redirect_uri}")
    private String REDIRECT_URI;

    private final Logger logger = LoggerFactory.getLogger(OAuthService.class);

    private final MemberService memberService;

    private final KakaoOAuthClient kakaoOAuthClient;

    private final TokenService tokenService;

    public LoginResponse kakaoLogin(String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoOAuthClient.token(code, REST_API_KEY, REDIRECT_URI);
        KakaoProfileResponse kakaoProfile = kakaoOAuthClient.profile(kakaoTokenResponse.getAccess_token());
        Member member = memberService.findMemberByEmailAndSocialType(kakaoProfile.getEmail(), SocialType.K);
        /* 우리 서버에 등록되지 않은 회원이라면 회원가입 처리 한다 */
        if (member == null) {
            RegisterRequest request = new RegisterRequest(kakaoProfile.getNickname(), kakaoProfile.getEmail(), SocialType.K);
            member = memberService.register(request);
        }
        /* TOKEN 발급 */
        Token token = tokenService.createToken(member, kakaoTokenResponse);
        return new LoginResponse(member, token);
    }
}
