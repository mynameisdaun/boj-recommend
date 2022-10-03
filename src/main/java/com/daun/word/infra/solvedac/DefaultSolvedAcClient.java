package com.daun.word.infra.solvedac;

import com.daun.word.global.Id;
import com.daun.word.infra.kakao.dto.KakaoTokenResponse;
import com.daun.word.infra.solvedac.dto.SolvedAcProblemResponse;
import com.daun.word.problem.domain.Problem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultSolvedAcClient implements SolvedAcClient{

    private final RestTemplate restTemplate;

    @Override
    public Problem findById(Id<Problem, Integer> id) {
        String url = "https://solved.ac/api/v3/problem/show?problemId="+id.getValue();
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
}
