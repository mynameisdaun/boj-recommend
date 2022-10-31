package com.daun.word.domain.recommend.domain.repository;

import com.daun.word.domain.recommend.domain.Recommend;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.NoSuchElementException;
import java.util.UUID;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecommendRepositoryTest {

    @Autowired
    private RecommendRepository recommendRepository;

    @DisplayName(value = "추천을 저장한다")
    @Test
    void save() throws Exception {
        //given
        Recommend recommend = new Recommend(UUID.randomUUID(), problem_16120(), member_3());
        //when
        int save = recommendRepository.save(recommend);
        //then
        assertThat(save).isOne();
    }

    @DisplayName(value = "회원과 문제 번호로 추천 조회")
    @Test
    void findByMemberAndProblem() throws Exception {
        //given&&when
        Recommend recommend = recommendRepository.findByMemberAndProblem(member_1(), problem_16120())
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(recommend).isNotNull();
        assertAll(
                () -> assertThat(recommend.getMember()).isNotNull(),
                () -> assertThat(recommend.getMember().getEmail()).isEqualTo(member_1().getEmail()),
                () -> assertThat(recommend.getProblem()).isNotNull(),
                () -> assertThat(recommend.getProblem().getId()).isEqualTo(problem_16120().getId())
        );
    }
}
