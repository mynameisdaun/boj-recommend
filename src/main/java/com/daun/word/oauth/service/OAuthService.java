package com.daun.word.oauth.service;

import com.daun.word.infra.kakao.client.KakaoOAuthClient;
import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.SocialType;
import com.daun.word.member.dto.RegisterRequest;
import com.daun.word.member.service.MemberService;
import com.daun.word.oauth.dto.LoginResponse;
import com.daun.word.oauth.token.domain.Token;
import com.daun.word.oauth.token.service.TokenService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OAuthService {
    private final Logger logger = LoggerFactory.getLogger(OAuthService.class);

    private final MemberService memberService;

    private final KakaoOAuthClient kakaoOAuthClient;

    private final TokenService tokenService;

    //TODO: Change Return Type
    public LoginResponse kakaoLogin(String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoOAuthClient.token(code);
        logger.info(kakaoTokenResponse.toString());
        KakaoProfileResponse kakaoProfile = kakaoOAuthClient.profile(kakaoTokenResponse.getAccess_token());
        Member member = memberService.findMemberByEmailAndSocialType(kakaoProfile.getEmail(), SocialType.K);

        /* 우리 서버에 등록되지 않은 회원이라면 회원가입 처리 한다 */
        if (member == null) {
            RegisterRequest request = new RegisterRequest(kakaoProfile.getNickname(), kakaoProfile.getEmail(), SocialType.K);
            member = memberService.register(request);
        }
        /* TOKEN 발급 */
        Token token = tokenService.createToken(member, kakaoTokenResponse);
        LoginResponse response = new LoginResponse(member, token);
        logger.info(response.toString());
        return response;
    }
}
