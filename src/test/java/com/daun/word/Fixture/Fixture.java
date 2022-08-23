package com.daun.word.Fixture;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import com.daun.word.chapter.domain.Chapter;
import com.daun.word.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.oauth.token.domain.Token;
import com.daun.word.word.domain.Word;
import com.daun.word.word.domain.Words;
import com.daun.word.word.domain.vo.English;
import com.daun.word.word.domain.vo.Korean;
import com.daun.word.workbook.domain.WorkBook;
import com.daun.word.workbook.domain.vo.Author;
import com.daun.word.workbook.domain.vo.Description;
import com.daun.word.workbook.domain.vo.Title;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Fixture {
    public static String FAKE_KAKAO_REST_API_KEY = "fake-api-key";
    public static String FAKE_KAKAO_REDIRECT_URI = "fake-redirect-uri";
    private static Token token;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Email email() {
        return new Email("tester1@weword.com");
    }

    public static Nickname nickname() {
        return new Nickname("테스터");
    }

    public static Member member() {
        LocalDateTime now = LocalDateTime.now();
        return new Member(1, new Email("tester1@weword.com"), "fake-password", nickname(), SocialType.W, now, now);
    }

    public static Member another_member() {
        LocalDateTime now = LocalDateTime.now();
        return new Member(2, new Email("tester2@weword.com"), "fake-password", nickname(), SocialType.W, now, now);
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

    public static Word word() {
        return new Word(1, new English("word"), new Korean("단어"));
    }

    public static WorkBook workbook() {
        return new WorkBook(
                Integer.valueOf(1),
                new Title("재밌는 단어장"),
                new Author("운영자"),
                new Description("아주 친절한 설명"),
                "https://weword.com/fake-image");
    }

    public static Chapter chapter() {
        return new Chapter(1, new Title("Day 1"), 1, new Words(new ArrayList<>(Arrays.asList(word()))));
    }

    public static Assignment assignment() {
        return new Assignment(
                Integer.valueOf(1),
                member().getEmail(),
                another_member().getEmail(),
                workbook().getId()
        );
    }

    public static AssignmentDetail assignmentDetail_complete() {
        return new AssignmentDetail(
                Integer.valueOf(1),
                Integer.valueOf(1),
                Integer.valueOf(1),
                LocalDateTime.parse("2022-08-20 15:43:51", formatter),
                LocalDateTime.parse("2023-08-21 15:43:51", formatter),
                "Y",
                LocalDateTime.parse("2022-08-21 15:43:51", formatter),
                "Y",
                LocalDateTime.parse("2022-08-21 16:43:51", formatter),
                "fake-quiz",
                "fake-submission",
                LocalDateTime.parse("2022-08-19 16:43:51", formatter),
                LocalDateTime.parse("2022-08-21 16:43:51", formatter)
        );
    }

    public static AssignmentDetail assignmentDetail_open_unComplete() {
        return new AssignmentDetail(
                Integer.valueOf(2),
                Integer.valueOf(1),
                Integer.valueOf(2),
                LocalDateTime.parse("2022-08-20 15:43:51", formatter),
                LocalDateTime.parse("2023-08-21 15:43:51", formatter),
                "Y",
                LocalDateTime.parse("2022-08-21 15:43:51", formatter),
                "N",
                null,
                "fake-quiz",
                null,
                LocalDateTime.parse("2022-08-19 16:43:51", formatter),
                LocalDateTime.parse("2022-08-19 16:43:51", formatter)
        );
    }

    public static AssignmentDetail assignmentDetail_unOpen() {
        return new AssignmentDetail(
                Integer.valueOf(3),
                Integer.valueOf(1),
                Integer.valueOf(3),
                LocalDateTime.parse("2022-08-20 15:43:51", formatter),
                LocalDateTime.parse("2023-08-21 15:43:51", formatter),
                "N",
                null,
                "N",
                null,
                "fake-quiz",
                null,
                LocalDateTime.parse("2022-08-19 16:43:51", formatter),
                LocalDateTime.parse("2022-08-19 16:43:51", formatter)
        );
    }

}




