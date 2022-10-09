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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSolvedAcClient implements SolvedAcClient {

    private final RestTemplate restTemplate;
    private final String BASE = "https://solved.ac/api/v3";


    /* id로 문제 조회 */
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

    /* id 리스트로 문제들 조회*/
    @Override
    public List<Problem> findByIdsIn(List<Id<Problem, Integer>> lists) {
        int start = 0;
        List<Problem> resp = new ArrayList<>();
        while (true) {
            List<Id<Problem, Integer>> ids = lists.subList(start, Math.min(start + 100, lists.size()));
            StringBuilder url = new StringBuilder(BASE)
                    .append("/problem/lookup?problemIds=")
                    .append(ids.stream().map(id -> String.valueOf(id.getValue())).collect(Collectors.joining(",")));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity request = new HttpEntity(headers);
            log.info("{}", ids.size());
            log.info(url.toString());
            SolvedAcProblemResponse[] response = restTemplate.exchange(
                    url.toString(),
                    HttpMethod.GET,
                    request,
                    SolvedAcProblemResponse[].class).getBody();
            resp.addAll(stream(response).map(Problem::new).collect(toList()));
            start += 100;
            if (resp.size() == lists.size()) break;
        }
        return resp;
    }

    /* 문제 리스트 중에서, 회원이 풀지 않은 문제만 반환하기 */
    @Override
    public List<Problem> unSolvedProblemsByMember(Member assignTo, List<Problem> problems) {
        List<Id<Problem, Integer>> solved = new ArrayList<>();
        int page = 1;
        int total = 0;
        int cnt = 0;
        Tier max = new Tier(0);
        Tier min = new Tier(30);
        for (Problem p : problems) {
            max = p.getTier().getLevel() > max.getLevel() ? p.getTier() : max;
            min = p.getTier().getLevel() < max.getLevel() ? p.getTier() : min;
        }
        while (true) {
            StringBuilder url = new StringBuilder(BASE)
                    .append("/search/problem?query=")
                    .append("s@").append(assignTo.getEmail().getValue())
                    .append(" *").append(min.getRate()).append("..").append(max.getRate())
                    .append("&page=").append(page);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity request = new HttpEntity(headers);
            ProblemSearchResponse response = restTemplate.exchange(
                    url.toString(),
                    HttpMethod.GET,
                    request,
                    ProblemSearchResponse.class).getBody();
            total = response.getCount();
            cnt += response.getItems().length;
            solved.addAll(stream(response.getItems()).map(i -> Id.of(Problem.class, i.getProblemId())).collect(toList()));
            if (cnt < total) {
                page++;
                continue;
            }
            break;
        }
        return problems.stream()
                .filter(p -> !solved.contains(Id.of(Problem.class, p.getId())))
                .collect(toList());
    }

    @Override
    public List<Problem> manualProblemUpdate() {
        List<Problem> allProblems = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            log.info("\n ###### {} 번째 가져오는 중입니다!", i);
            int page = 1;
            int cnt = 0;
            List<Id<Problem, Integer>> ids = new ArrayList<>();
            while (true) {
                Tier tier = new Tier(i);
                StringBuilder url = new StringBuilder(BASE)
                        .append("/search/problem?query=")
                        .append("*")
                        .append(tier.getRate())
                        .append("..")
                        .append(tier.getRate())
                        .append("&")
                        .append("page=")
                        .append(page);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                ProblemSearchResponse response = restTemplate.exchange(url.toString(), HttpMethod.GET, new HttpEntity(headers), ProblemSearchResponse.class).getBody();
                cnt = response.getCount();
                ids.addAll(stream(response.getItems()).map(r -> Id.of(Problem.class, r.problemId)).collect(toList()));
                if (ids.size() < cnt) {
                    page++;
                    continue;
                }
                break;
            }
            List<Problem> byIdsIn = findByIdsIn(ids);
            allProblems.addAll(byIdsIn);
        }
        log.info("\n ##########################################\n\n{}\n\nallProblemsSize: {}", allProblems, allProblems.size());
        return allProblems;
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
