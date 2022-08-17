package com.daun.word.oauth.service;

import com.daun.word.infra.kakao.client.KakaoOAuthClient;
import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.SocialType;
import com.daun.word.member.dto.RegisterRequest;
import com.daun.word.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {
    private final Logger logger = LoggerFactory.getLogger(OAuthService.class);

    private final MemberService memberService;
    private final KakaoOAuthClient kakaoOAuthClient;

    public OAuthService(MemberService memberService, KakaoOAuthClient kakaoOAuthClient) {
        this.memberService = memberService;
        this.kakaoOAuthClient = kakaoOAuthClient;
    }

    //TODO: Change Return Type
    public Object kakaoLogin(String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoOAuthClient.token(code);
        KakaoProfileResponse kakaoProfile = kakaoOAuthClient.profile(kakaoTokenResponse.getAccessToken());
        Member member = memberService.findMemberByEmailAndSocialType(kakaoProfile.getEmail(), SocialType.K);

        /* 우리 서버에 등록되지 않은 회원이라면 회원가입 처리 한다 */
        if (member == null) {
            RegisterRequest request = new RegisterRequest(kakaoProfile.getNickname(), kakaoProfile.getEmail(), SocialType.K);
            member = memberService.register(request);
            logger.info(member.toString());
        }


        //TOKEN 발급


        //회원정보와 토큰 함께한 res return

        return null;
    }
}
