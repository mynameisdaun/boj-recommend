package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.GlobalId;
import com.daun.word.global.infra.solvedac.dto.ProblemCount;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.infra.solvedac.dto.UserSearchResponse;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    /**
     * SolvedAC로 부터 '문제 검색' 하기
     *
     * @param query     String 쿼리 문자열
     * @param page      int 페이지 수
     * @param sort      String 정렬 기준 주로 solved 기준으로 사용 (id, level, title, solved, average_try, random)
     * @param direction String 정렬방향 asc, desc
     * @return ProblemSearchResponse
     */
    @Override
    public ProblemSearchResponse search(String query, int page, String sort, String direction) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/search/problem")
                .append("?query=").append(query)
                .append("&page=").append(page)
                .append("&sort=").append(sort)
                .append("&direction=").append(direction);
        return restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                ProblemSearchResponse.class).getBody();
    }

    /**
     * SolvedAC로 부터 '문제 조회' 하기
     *
     * @param id
     * @return SolvedAcProblem
     */
    @Override
    public Optional<SolvedAcProblem> findById(Integer id) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/problem/show?problemId=")
                .append(id);
        return Optional.ofNullable(restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                SolvedAcProblem.class).getBody());
    }

    /**
     * SolvedAC로 부터 '회원 조회' 하기
     *
     * @param email Email
     * @return SolvedAcMember
     */
    @Override
    public Optional<SolvedAcMember> findMemberByEmail(Email email) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/user/show?handle=")
                .append(email.getValue());
        return Optional.ofNullable(new SolvedAcMember(restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                UserSearchResponse.class).getBody()));
    }

    /**
     * 회원이 문제를 풀었는지 확인하기
     *
     * @param member
     * @param problem
     * @return boolean
     */
    @Override
    public boolean isSolved(Member member, Problem problem) {
        StringBuilder query = new StringBuilder();
        query.append("s@")
                .append(member.getEmail().getValue())
                .append(" ")
                .append(problem.getId());
        ProblemSearchResponse search = search(query.toString(), 1, "solved", "asc");
        return search.getCount() > 0;
    }

    private HttpEntity httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(headers);
    }
}
