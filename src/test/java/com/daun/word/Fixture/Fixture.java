package com.daun.word.Fixture;

import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.oauth.token.domain.Token;
import com.daun.word.oauth.token.domain.TokenFactory;
import com.daun.word.utils.JwtUtils;

public class Fixture {
    public static String FAKE_KAKAO_REST_API_KEY = "fake-api-key";
    public static String FAKE_KAKAO_REDIRECT_URI = "fake-redirect-uri";
    private static Token token;

    public static Email email() {
        return new Email("tester@tester.com");
    }

    public static Nickname nickname() {
        return new Nickname("tester");
    }

    public static Member member() {
        Member member = new Member(email(), "fake-password", nickname(), SocialType.K);
        return member;
    }

    public static KakaoTokenResponse kakaoTokenResponse() {
        return new KakaoTokenResponse(
                "bearer",
                "asffesadcsacascsavas-qsvasvasdvasvasvdsavas",
                21599,
                "sfdf2o4fu987987sfda222-393829d8wedywed",
                215990);
    }

    public static KakaoProfileResponse kakaoProfileResponse() {
        return new KakaoProfileResponse(
                "123456789",
                "2022-08-15T14:04:12Z",
                new KakaoProfileResponse.KakaoAccount(
                        false, false, new KakaoProfileResponse.Properties("테스터", null, null),
                        true, false, false, false, "tester@tester.com", true, false,
                        "20-29", true, false, "0101", "SOLAR", true, false, "male"),
                new KakaoProfileResponse.Properties(
                        "테스터", "http://k.kakaocdn.net/dn/zt9yB/btrJsu3NH8Z/0YRXHJP3w7IHCbXLtqzzM1/img_640x640.jpg",
                        "http://k.kakaocdn.net/dn/zt9yB/btrJsu3NH8Z/0YRXHJP3w7IHCbXLtqzzM1/img_110x110.jpg"));
    }

    public static Token token() {
        if (token == null) {
            token = new TokenFactory(new JwtUtils("longerlongerlongerfakeaccesskeyas2ef2f2sc11212", "fakeafakakwefjwoef1d131e1diejffakerefreshkey", 6000L, 60000L)).generateToken(member(), kakaoTokenResponse());
        }
        return token;
    }
}
