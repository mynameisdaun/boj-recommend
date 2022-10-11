package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.Id;
import com.daun.word.global.infra.solvedac.dto.ProblemCount;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblemResponse;
import com.daun.word.global.infra.solvedac.dto.UserSearchResponse;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSolvedAcClient implements SolvedAcClient {

    private final RestTemplate restTemplate;
    private final String BASE = "https://solved.ac/api/v3";


    /* 문제 검색 */
    @Override
    public ProblemSearchResponse search(String query, int page, String sort, String direction) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/search/problem")
                .append("?query=").append(query)
                .append("&page=").append(page)
                .append("&sort=").append(sort)
                .append("&direction=").append(direction);
        log.info(url.toString());
        return restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                ProblemSearchResponse.class).getBody();
    }


    /* id로 문제 조회 */
    @Override
    public Problem findById(Id<Problem, Integer> id) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/problem/show?problemId=")
                .append(id.getValue());
        ResponseEntity<SolvedAcProblemResponse> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                SolvedAcProblemResponse.class);
        return new Problem(response.getBody());
    }

    /* id 리스트로 문제들 조회 */
    @Override
    public List<Problem> findByIdsIn(List<Id<Problem, Integer>> lists) {
        if (lists == null || lists.isEmpty()) {
            return new ArrayList<>();
        }
        final int limitPerRequest = 100;
        int start = 0;
        List<Problem> resp = new ArrayList<>();
        while (true) {
            List<Id<Problem, Integer>> ids = lists.subList(start, min(start + limitPerRequest, lists.size()));
            StringBuilder url = new StringBuilder(BASE)
                    .append("/problem/lookup?problemIds=")
                    .append(ids.stream().map(id -> String.valueOf(id.getValue())).collect(Collectors.joining(",")));
            log.info("{}", ids.size());
            log.info(url.toString());
            SolvedAcProblemResponse[] response = restTemplate.exchange(
                    url.toString(),
                    HttpMethod.GET,
                    httpEntity(),
                    SolvedAcProblemResponse[].class).getBody();
            resp.addAll(stream(response).map(Problem::new).collect(toList()));
            start += limitPerRequest;
            if (resp.size() == lists.size()) break;
        }
        return resp;
    }

    /* 모든 회원이 풀지 않은 문제 반환하기 */
    //TODO: 매직 넘버 너무 많다
    @Override
    public List<Problem> unSolvedProblemsByMembers(List<Member> members, List<Problem> problems) {
        List<Id<Problem, Integer>> solved = new ArrayList<>();
        final int page = 1;
        int limit = 8;
        int offset = 0;
        while (true) {
            //10개씩 조회한다.
            List<Problem> sub = problems.subList(offset, min(problems.size(), offset + limit));
            String query = memberSolvedQuery(members, sub);
            /* 백준 api의 Bad Request 기준을 피하기 위해 사이즈를 줄인다.*/
            if (query.length() > 450) {
                limit--;
            }
            ProblemSearchResponse response = search(query, page, "solved", "desc");
            solved.addAll(stream(response.getItems()).map(i -> Id.of(Problem.class, i.getProblemId())).collect(toList()));
            if (response.getCount() < limit) {
                break;
            }
            offset += limit;
        }
        return problems.stream()
                .filter(p -> !solved.contains(Id.of(Problem.class, p.getId())))
                .collect(toList());
    }

    private String memberSolvedQuery(List<Member> members, List<Problem> problems) {
        StringBuilder sb = new StringBuilder();
        for (Problem p : problems) {
            for (Member m : members) {
                sb.append("s@")
                        .append(m.getEmail().getValue())
                        .append(" ")
                        .append(p.getId())
                        .append(" | ");
            }
        }
        return sb.toString().substring(0, sb.length() - 3);
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
            StringBuilder query = new StringBuilder()
                    .append("s@").append(assignTo.getEmail().getValue())
                    .append(" *").append(min.getRate()).append("..").append(max.getRate());
            ProblemSearchResponse response = search(query.toString(), page, "solved", "desc");
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
    public SolvedAcMember findMemberByEmail(Email email) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/user/show?handle=")
                .append(email.getValue());
        return new SolvedAcMember(restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                UserSearchResponse.class).getBody());
    }

    public List<ProblemCount> problemCountGroupByLevel() {
        StringBuilder url = new StringBuilder(BASE)
                .append("/problem/level");
        return stream(restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                ProblemCount[].class).getBody())
                .sorted((a, b) -> a.getLevel() - b.getLevel())
                .collect(toList());
    }

    private HttpEntity httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(headers);
    }
}
