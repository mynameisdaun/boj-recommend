package com.daun.word.domain.recommend.domain.repository;

import com.daun.word.Fixture.Fixture;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.domain.vo.RecommendType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.UUID;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class RecommendRepositoryTest {

    @Autowired
    RecommendRepository recommendRepository;

    @DisplayName(value = "아이디로 추천을 조회한다")
    @Test
    void findById() throws Exception {
        //given
        Recommend r = recommend();
        //when
        Recommend find = recommendRepository.findById(r.getId())
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(find).isNotNull();
        assertAll(
                () -> assertThat(find.getId()).isEqualTo(r.getId()),
                () -> assertThat(find.getProblem()).isEqualTo(r.getProblem()),
                () -> assertThat(find.getType()).isEqualTo(r.getType())
        );
    }

    @DisplayName(value = "추천을 저장한다")
    @Test
    void save() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        Recommend r = new Recommend(id, problem_16120(), daun9870jung(), RecommendType.TIER);
        //when
        Recommend save = recommendRepository.save(r);
        //then
        assertThat(save).isNotNull();
        assertAll(
                () -> assertThat(save.getId()).isEqualTo(id),
                () -> assertThat(save.getMember().getId()).isEqualTo(daun9870jung().getId()),
                () -> assertThat(save.getType()).isEqualTo(RecommendType.TIER),
                () -> assertThat(save.getProblem().getId()).isEqualTo(problem_16120().getId())
        );
    }

}
