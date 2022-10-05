package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.Id;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblemResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSolvedAcClient implements SolvedAcClient {

    private final RestTemplate restTemplate;

    @Override
    public Problem findById(Id<Problem, Integer> id) {
        String url = "https://solved.ac/api/v3/problem/show?problemId=" + id.getValue();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<SolvedAcProblemResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                SolvedAcProblemResponse.class);
        return new Problem(response.getBody());
    }

    @Override
    public boolean checkAssignment(Email email, Id<Problem, Integer> id) {
        StringBuilder query = new StringBuilder();
        query.append("s@")
                .append(email.getValue())
                .append(" ")
                .append(id.getValue());
        String url = "https://solved.ac/api/v3/search/problem?query=" + query;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<SearchResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                SearchResponse.class);
        log.error("{}", response.getBody());
        for (SearchResponse.Item item : response.getBody().getItems()) {
            if (item.getProblemId() == id.getValue()) return true;
        }
        return false;
    }

    @Data
    @NoArgsConstructor
    static class SearchResponse {
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
