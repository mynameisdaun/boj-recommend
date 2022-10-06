package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.Id;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblemResponse;
import com.daun.word.global.vo.Tier;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSolvedAcClient implements SolvedAcClient {

    private final RestTemplate restTemplate;
    private final String BASE = "https://solved.ac/api/v3";

    @Override
    public Problem findById(Id<Problem, Integer> id) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/problem/show?problemId=")
                .append(id.getValue());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<SolvedAcProblemResponse> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                request,
                SolvedAcProblemResponse.class);
        return new Problem(response.getBody());
    }

    @Override
    public boolean checkAssignment(Member assignTo, Id<Problem, Integer> id) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/search/problem?query=")
                .append("s@")
                .append(assignTo.getEmail().getValue())
                .append(" ")
                .append(id.getValue());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ProblemSearchResponse> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                request,
                ProblemSearchResponse.class);
        for (ProblemSearchResponse.Item item : response.getBody().getItems()) {
            if (item.getProblemId() == id.getValue()) return true;
        }
        return false;
    }

    @Override
    public Tier findMemberTier(Member member) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/user/show?handle=")
                .append(member.getEmail().getValue());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(headers);
        return new Tier(restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                request,
                UserSearchResponse.class).getBody().getTier());
    }

    @Override
    public List<Problem> checkProblemsSolved(Member member, List<Id<Problem, Integer>> ids) {
        return null;
    }

    @Data
    @NoArgsConstructor
    public static class UserSearchResponse {
        private String handle;
        private String bio;
        private Organization[] organizations;
        private Badge badge;
        private Background background;
        private String profileImageUrl;
        private int solvedCount;
        private int voteCount;
        private int exp;
        private int tier;
        private int rating;
        private int ratingByProblemsSum;
        private int ratingByClass;
        private int ratingBySolvedCount;
        private int ratingByVoteCount;
        private int _class;
        private String classDecoration;
        private int rivalCount;
        private int reverseRivalCount;
        private int maxStreak;
        private int rank;
        private int isRival;
        private int isReverseRival;

        @Data
        @NoArgsConstructor
        static class Organization {
            private int organizationId;
            private String name;
            private String type;
            private int rating;
            private int userCount;
            private int voteCount;
            private int solvedCount;
            private String color;
        }

        @Data
        @NoArgsConstructor
        static class Badge {
            private String badgeId;
            private String badgeImageUrl;
            private String displayName;
            private String displayDescription;
        }

        @Data
        @NoArgsConstructor
        static class Background {
            private String backgroundId;
            private String backgroundImageUrl;
            private String author;
            private String authorUrl;
            private String displayName;
            private String displayDescription;
        }
    }

    @Data
    @NoArgsConstructor
    static class ProblemSearchResponse {
        private int count;
        private Item[] items;

        @Data
        @NoArgsConstructor
        static class Item {
            private int problemId;
            private String titleKo;
            private boolean isSolvable;
            private boolean isPartial;
            private int acceptedUserCount;
            private int level;
            private int votedUserCount;
            private boolean isLevelLocked;
            private double averageTries;
        }
    }
}
