package com.daun.word.Fixture;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Password;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.global.auth.token.domain.Token;
import com.daun.word.global.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.global.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.global.vo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class Fixture {
    public static String FAKE_KAKAO_REST_API_KEY = "fake-api-key";
    public static String FAKE_KAKAO_REDIRECT_URI = "fake-redirect-uri";
    private static Token token;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static Email email() {
        return new Email("tester1@weword.com");
    }

    public static Password password() {
        return new Password("imTester9!");
    }

    public static Name nickname() {
        return new Name("테스터");
    }

    public static Member member() {
        LocalDateTime now = LocalDateTime.now();
        return new Member(
                UUID.randomUUID(),
                new Email("tester1@weword.com"),
                nickname(),
                "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS",
                new Tier(15),
                SocialType.W
        );
    }

    public static Member another_member() {
        LocalDateTime now = LocalDateTime.now();
        return new Member(
                UUID.randomUUID(),
                new Email("daun9870jung"),
                nickname(),
                "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS",
                new Tier(15),
                SocialType.W
        );
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

        return new Token(
                new Email("tester1@weword.com"),
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjA5Nzc4MzEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.Bs9nDgglcyg_IQCcsLQVH48RW1t1-w8QYqkLJissNuU",
                LocalDateTime.parse("2022-08-20 15:43:51", formatter),
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjEwMzY1MTEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.uCNJxT-PuD2FNLZcplTULRqu1XO2YEWX_0--35quTGU",
                LocalDateTime.parse("2022-08-21 08:01:51", formatter),
                SocialType.valueOf("W"),
                "7MP8EHWXFLzHxQsi1YNMXs3KVb1paQBpEPLwZb6QCj1zFwAAAYK54uKs",
                LocalDateTime.parse("2022-08-20 15:13:51", formatter),
                "oOdpDpTD3juuQy7ZVuEBnYkDH3cWJmM_lUie3eUcCj1zFwAAAYK54uKq",
                LocalDateTime.parse("2022-10-19 15:13:50", formatter)
        );
    }

    public static Tag tag() {
        return new Tag(1, "key", new Title("example"));
    }

    public static Problem problem() {
        return new Problem(16120, new Title("PPAP"), new URL("https://www.acmicpc.net/problem/16120"), new Tier(12), Arrays.asList(tag()));
    }

    public static Assignment assignment() {
        return new Assignment(1, study(), recommend(), another_member(), LocalDateTime.parse("2022-08-19 16:43:51", formatter), LocalDateTime.parse("2022-08-19 16:43:51", formatter), YesNo.N, null, LocalDateTime.parse("2022-08-19 16:43:51", formatter), LocalDateTime.parse("2022-08-19 16:43:51", formatter));
    }

    public static Recommend recommend() {
        return new Recommend(UUID.randomUUID(), problem(), another_member());
    }

    public static Study study() {
        return new Study(UUID.randomUUID(), another_member(), new Name("sample study"), "2023f708bd566934819ba9b65da86551bcc2e445bdd336b64f31e9a9f6f1aa3a", null);
    }
}




