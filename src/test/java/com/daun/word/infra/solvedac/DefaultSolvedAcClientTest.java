package com.daun.word.infra.solvedac;

import com.daun.word.global.Id;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;
import com.daun.word.problem.domain.Problem;
import com.daun.word.problem.domain.vo.Tier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DefaultSolvedAcClientTest {

    private SolvedAcClient solvedAcClient;

    @BeforeEach
    public void setUp() {
        this.solvedAcClient = new DefaultSolvedAcClient(new RestTemplate());
    }

    @DisplayName(value = "Problem Id로 solvedAc에서 문제 조회")
    @Test
    void findById() throws Exception {
        //given&&when
        Problem problem = solvedAcClient.findById(Id.of(Problem.class, 16120));
        //then
        assertThat(problem).isNotNull();
        assertAll(
                () -> assertThat(problem.getId()).isEqualTo(16120),
                () -> assertThat(problem.getTitle()).isEqualTo(new Title("PPAP")),
                () -> assertThat(problem.getUrl()).isEqualTo(new URL("https://www.acmicpc.net/problem/16120")),
                () -> assertThat(problem.getTier()).isEqualTo(new Tier(12)),
                () -> assertThat(problem.getTags().size()).isEqualTo(4),
                () -> assertThat(problem.getTags().get(0).getId()).isPositive(),
                () -> assertThat(problem.getTags().get(0).getKey()).isNotNull(),
                () -> assertThat(problem.getTags().get(0).getTitle()).isNotNull()
        );
    }

}
