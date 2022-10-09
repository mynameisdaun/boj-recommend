package com.daun.word.Fixture;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Nickname;
import com.daun.word.domain.member.domain.vo.Password;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.vo.Tag;
import com.daun.word.global.auth.token.domain.Token;
import com.daun.word.global.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.global.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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

    public static Nickname nickname() {
        return new Nickname("테스터");
    }

    public static Member member() {
        LocalDateTime now = LocalDateTime.now();
        return new Member(
                1,
                new Email("tester1@weword.com"),
                "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS",
                nickname(),
                new Tier(15),
                SocialType.W,
                1,
                LocalDateTime.parse("2022-08-20 15:43:51", formatter),
                LocalDateTime.parse("2022-08-20 15:43:51", formatter),
                LocalDateTime.parse("2022-08-20 15:43:51", formatter)
        );
    }

    public static Member another_member() {
        LocalDateTime now = LocalDateTime.now();
        return new Member(2, new Email("daun9870jung"), "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS", nickname(), new Tier(15), SocialType.W, 1, LocalDateTime.parse("2022-08-20 15:43:51", formatter), LocalDateTime.parse("2022-08-20 15:43:51", formatter), LocalDateTime.parse("2022-08-20 15:43:51", formatter));
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
        return new Tag(1, "key", new Title("example"), LocalDateTime.parse("2022-08-19 16:43:51", formatter), LocalDateTime.parse("2022-08-19 16:43:51", formatter));
    }

    public static Problem problem() {
        return new Problem(16120, new Title("PPAP"), new URL("https://www.acmicpc.net/problem/16120"), new Tier(12), Arrays.asList(tag()), 0, 0, LocalDateTime.parse("2022-08-19 16:43:51", formatter), LocalDateTime.parse("2022-08-19 16:43:51", formatter));
    }

    public static Assignment assignment() {
        return new Assignment(1, problem(), member().getEmail(), another_member().getEmail(), LocalDateTime.parse("2022-08-19 16:43:51", formatter), LocalDateTime.parse("2022-08-19 16:43:51", formatter), "N", null, "N", null, LocalDateTime.parse("2022-08-19 16:43:51", formatter), LocalDateTime.parse("2022-08-19 16:43:51", formatter));
    }
}




