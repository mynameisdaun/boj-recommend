package com.daun.word.domain.problem.domain.repository;

import com.daun.word.config.QueryDslConfig;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.dto.search.RecommendSearchQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(QueryDslConfig.class)
class ProblemQueryRepositoryTest {
    @Autowired
    private ProblemQueryRepository problemQueryRepository;

    @DisplayName(value = "조건에 맞는 문제들을 검색한다")
    @Test
    void search() throws Exception {
        //given
        RecommendSearchQuery query = new RecommendSearchQuery(11, 15);
        //when
        List<Problem> results = problemQueryRepository.search(query);
        //then
        assertThat(results).isNotNull();
        assertAll(
                () -> assertThat(results.size()).isEqualTo(3),
                () -> assertThat(results.get(0).getTier().getLevel()).isBetween(11, 15),
                () -> assertThat(results.get(1).getTier().getLevel()).isBetween(11, 15),
                () -> assertThat(results.get(2).getTier().getLevel()).isBetween(11, 15)
        );
    }

}
