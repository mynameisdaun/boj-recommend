package com.daun.word.Fixture;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Password;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.vo.RecommendType;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.global.auth.token.domain.Token;
import com.daun.word.global.infra.kakao.dto.KakaoProfileResponse;
import com.daun.word.global.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.vo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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

    public static Member daun9870jung() {
        LocalDateTime now = LocalDateTime.now();
        return new Member(UUID.fromString("f1860abc-2ea1-411b-bd4a-baa44f0d5580"), new Email("tester1"), nickname(), "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS", new Tier(15), SocialType.W);
    }

    public static Member member_2() {
        LocalDateTime now = LocalDateTime.now();
        return new Member(UUID.fromString("e7f677ab-e1d5-4f24-a606-e183e43c7d54"), new Email("tester2"), nickname(), "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS", new Tier(15), SocialType.W);
    }


    public static Member member_3() {
        return new Member(UUID.randomUUID(), new Email("tester3"), nickname(), "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS", new Tier(15), SocialType.W);
    }

    public static Member member_4() {
        return new Member(UUID.randomUUID(), new Email("tester4"), nickname(), "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS", new Tier(15), SocialType.W);
    }


    public static KakaoTokenResponse kakaoTokenResponse() {
        return new KakaoTokenResponse("bearer", "asffesadcsacascsavas-qsvasvasdvasvasvdsavas", 21599, "sfdf2o4fu987987sfda222-393829d8wedywed", 215990);
    }

    public static KakaoProfileResponse kakaoProfileResponse() {
        return new KakaoProfileResponse("123456789", "2022-08-15T14:04:12Z", new KakaoProfileResponse.KakaoAccount(false, false, new KakaoProfileResponse.Properties("테스터", null, null), true, false, false, false, "tester@tester.com", true, false, "20-29", true, false, "0101", "SOLAR", true, false, "male"), new KakaoProfileResponse.Properties("테스터", "http://k.kakaocdn.net/dn/zt9yB/btrJsu3NH8Z/0YRXHJP3w7IHCbXLtqzzM1/img_640x640.jpg", "http://k.kakaocdn.net/dn/zt9yB/btrJsu3NH8Z/0YRXHJP3w7IHCbXLtqzzM1/img_110x110.jpg"));
    }

    public static Token token() {

        return new Token(new Email("tester1@weword.com"), "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjA5Nzc4MzEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.Bs9nDgglcyg_IQCcsLQVH48RW1t1-w8QYqkLJissNuU", LocalDateTime.parse("2022-08-20 15:43:51", formatter), "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjEwMzY1MTEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.uCNJxT-PuD2FNLZcplTULRqu1XO2YEWX_0--35quTGU", LocalDateTime.parse("2022-08-21 08:01:51", formatter), SocialType.valueOf("W"), "7MP8EHWXFLzHxQsi1YNMXs3KVb1paQBpEPLwZb6QCj1zFwAAAYK54uKs", LocalDateTime.parse("2022-08-20 15:13:51", formatter), "oOdpDpTD3juuQy7ZVuEBnYkDH3cWJmM_lUie3eUcCj1zFwAAAYK54uKq", LocalDateTime.parse("2022-10-19 15:13:50", formatter));
    }

    public static Tag tag() {
        return new Tag(1, "key", new Title("example"));
    }

    public static Problem problem_16120() {
        return new Problem(16120, new Title("PPAP"), new URL(16120), new Tier(12), 1378);
    }

    public static Problem problem_1002() {
        return new Problem(1002, new Title("터렛"), new URL(1002), new Tier(8), 29321);
    }

    public static Problem problem_19() {
        return new Problem(19, new Title("첫번째 가짜"), new URL(19), new Tier(10), 29321);
    }

    public static Problem problem_29() {
        return new Problem(29, new Title("두번째 가짜"), new URL(29), new Tier(11), 5678);
    }

    public static Problem problem_39() {
        return new Problem(39, new Title("세번째 가짜"), new URL(39), new Tier(13), 56789);
    }

    public static Assignment assignment() {
        return new Assignment(UUID.fromString("aba40f35-7c62-4b8d-b269-a5fb9eb4ad70"), problem_16120(), daun9870jung());
    }

    public static Assignment assignment_complete() {
        return new Assignment(UUID.fromString("cfc60b53-4b8d-7c62-b269-a5fb7fa4ad70"), problem_29(), daun9870jung());
    }

    public static Recommend recommend() {
        return new Recommend(UUID.fromString("eb4adf35-4b8d-7c26-b269-f6cec40fb970"), problem_16120(), daun9870jung(), RecommendType.TIER);

    }

    public static Study study() {
        return new Study(UUID.fromString("cec40f35-7c26-4b8d-b269-f6fb9eb4ad70"), daun9870jung(), new Name("sample study"), "2023f708bd566934819ba9b65da86551bcc2e445bdd336b64f31e9a9f6f1aa3a");
    }

    public static SolvedAcMember solvedAcMember(String handle) {
        SolvedAcMember solvedAcMember = new SolvedAcMember();
        solvedAcMember.setHandle(handle);
        return solvedAcMember;
    }

    public static SolvedAcProblem solvedAcProblem(Integer id) {
        SolvedAcProblem solvedAcProblem = new SolvedAcProblem();
        solvedAcProblem.setProblemId(id);
        solvedAcProblem.setTitleKo("배열 정리하기");
        solvedAcProblem.setTitles(Arrays.asList(new SolvedAcProblem.Title("ko", "ko", "배열 정리하기", false)));
        solvedAcProblem.setSolvable(false);
        solvedAcProblem.setPartial(false);
        solvedAcProblem.setAcceptedUserCount(17);
        solvedAcProblem.setLevel(15);
        solvedAcProblem.setVotedUserCount(3);
        solvedAcProblem.setSprout(false);
        solvedAcProblem.setGivesNoRating(false);
        solvedAcProblem.setLevelLocked(false);
        solvedAcProblem.setAverageTries(2.7059);
        solvedAcProblem.setOfficial(true);
        solvedAcProblem.setTags(Arrays.asList(new SolvedAcProblem.Tag("bfs", false, 126, 732, Arrays.asList(new SolvedAcProblem.DisplayName("en", "breadth-first search", null))), new SolvedAcProblem.Tag("graphs", false, 7, 2627, Arrays.asList(new SolvedAcProblem.DisplayName("ko", "그래프 이론", null), new SolvedAcProblem.DisplayName("en", "graph theory", null))), new SolvedAcProblem.Tag("graph_traversal", false, 11, 1453, Arrays.asList(new SolvedAcProblem.DisplayName("ko", "그래프 탐색", null), new SolvedAcProblem.DisplayName("en", "graph traversal", null)))));
        return solvedAcProblem;
    }
}




