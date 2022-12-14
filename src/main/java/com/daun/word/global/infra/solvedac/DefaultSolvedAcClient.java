package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.exception.NoSuchMemberException;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.dto.search.SortDirection;
import com.daun.word.domain.problem.dto.search.SortType;
import com.daun.word.global.infra.solvedac.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ProblemSearchResponse search(String query, int page, SortType sort, SortDirection direction) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/search/problem")
                .append("?query=").append(query)
                .append("&page=").append(page)
                .append("&sort=").append(sort.getValue())
                .append("&direction=").append(direction.getValue());
        return restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                ProblemSearchResponse.class).getBody();
    }

    /**
     * SolvedAC로 부터 아이디로 '문제 조회' 하기
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
     * SolvedAC로 부터 아이디들로 '문제들 조회' 하기
     *
     * @param ids
     * @return SolvedAcProblem
     * @throws IllegalStateException 조회하려는 문제가 50개 이상인 경우 "한번에 50문제씩 조회할 수 있습니다"
     */
    @Override
    public List<SolvedAcProblem> findByIdsIn(final List<Integer> ids) {
        if (ids.size() > 50) {
            throw new IllegalStateException("한번에 50문제씩 조회할 수 있습니다");
        }
        final StringBuilder url = new StringBuilder(BASE)
                .append("/problem/lookup?problemIds=");
        ids.forEach(id -> url.append(id).append(","));
        url.deleteCharAt(url.length() - 1);
        return restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                httpEntity(),
                new ParameterizedTypeReference<List<SolvedAcProblem>>() {
                }).getBody();
    }

    /**
     * SolvedAC로 부터 '회원 조회' 하기
     *
     * @param email Email
     * @return SolvedAcMember
     */
    @Override
    public SolvedAcMember findMemberByEmail(Email email) {
        StringBuilder url = new StringBuilder(BASE)
                .append("/user/show?handle=")
                .append(email.getValue());
        try {
            return new SolvedAcMember(restTemplate.exchange(
                    url.toString(),
                    HttpMethod.GET,
                    httpEntity(),
                    UserSearchResponse.class).getBody());
        } catch (HttpClientErrorException e) {
            throw new NoSuchMemberException("Solved AC에 등록되지 않은 회원입니다.\n서비스를 이용하기 위해 먼저 Solved AC에 가입해주세요.\nhttps://solved.ac");
        }
    }

    /**
     * SolvedAc 에서 수준별 문제 수 가져오기
     *
     * @return TierCounts
     */
    @Override
    public TierCounts problemCountGroupByTier() {
        StringBuilder url = new StringBuilder(BASE)
                .append("/problem/level");
        return new TierCounts(restTemplate.exchange(
                        url.toString(),
                        HttpMethod.GET,
                        httpEntity(),
                        new ParameterizedTypeReference<List<TierCount>>() {
                        })
                .getBody()
        );
    }

    /**
     * 회원들이 문제를 풀었는지 확인하기
     *
     * @param members
     * @param problem
     * @return boolean
     */
    @Override
    public boolean isSolved(List<Member> members, Problem problem) {
        StringBuilder query = new StringBuilder();
        for (Member m : members) {
            query.append("s@")
                    .append(m.getEmail().getValue())
                    .append(" ")
                    .append(problem.getId())
                    .append(" | ");
        }
        log.error(query.substring(0, query.length() - 3));
        ProblemSearchResponse search = search(query.substring(0, query.length() - 3).toString(), 1, SortType.ACCEPTED_USER_COUNT, SortDirection.ASC);
        return search.getCount() > 0;
    }

    private HttpEntity httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(headers);
    }
}
