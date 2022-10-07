package com.daun.word.domain.recommend.domain.repository;

import com.daun.word.domain.recommend.domain.Recommend;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.another_member;
import static com.daun.word.Fixture.Fixture.problem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecommendRepositoryTest {

    @Autowired
    private RecommendRepository recommendRepository;

    @DisplayName(value = "회원과 문제 번호로 추천 조회")
    @Test
    void findByMemberAndProblem() throws Exception {
        //given&&when
        Recommend recommend = recommendRepository.findByMemberAndProblem(another_member(), problem())
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(recommend).isNotNull();
        assertAll(
                () -> assertThat(recommend.getMember()).isNotNull(),
                () -> assertThat(recommend.getMember().getEmail()).isEqualTo(another_member().getEmail()),
                () -> assertThat(recommend.getProblem()).isNotNull(),
                () -> assertThat(recommend.getProblem().getId()).isEqualTo(problem().getId())
        );
    }
}
