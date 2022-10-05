package com.daun.word.domain.problem.domain.repository;

import com.daun.word.global.Id;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.vo.Tag;
import com.daun.word.domain.problem.domain.vo.Tier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Arrays;

import static com.daun.word.Fixture.Fixture.problem;
import static com.daun.word.Fixture.Fixture.tag;
import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProblemRepositoryTest {

    @Autowired
    private ProblemRepository problemRepository;

    @DisplayName(value = "태그를 저장한다")
    @Test
    void saveTag() throws Exception {
        //given
        Tag tag = new Tag(175, "data_structures", new Title("자료 구조"));
        //when
        int saved = problemRepository.saveTag(tag);
        //then
        assertThat(saved).isOne();
        assertThat(tag.getCreatedAt()).isNotNull();
        assertThat(tag.getUpdatedAt()).isNotNull();
    }

    @DisplayName(value = "문제_태그 매핑을 저장한다")
    @Test
    void saveProblem_Tag() throws Exception {
        //given&&when
        Id<Problem, Integer> problemId = Id.of(Problem.class, problem().getId());
        Id<Tag, Integer> tagId = Id.of(Tag.class, tag().getId());
        int saved = problemRepository.saveProblemTag(problemId, tagId);
        //then
        assertThat(saved).isOne();
    }


    @DisplayName(value = "문제를 저장한다")
    @Test
    void save() throws Exception {
        //given
        Problem problem = new Problem(2800, new Title("괄호 제거"), new URL("https://www.acmicpc.net/problem/2800"), new Tier(11), Arrays.asList(tag()));
        //when
        int saved = problemRepository.save(problem);
        //then
        assertThat(saved).isOne();
        assertThat(problem.getCreatedAt()).isNotNull();
        assertThat(problem.getUpdatedAt()).isNotNull();
    }

}
