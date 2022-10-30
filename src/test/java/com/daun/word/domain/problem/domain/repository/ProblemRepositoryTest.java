package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.global.GlobalId;
import com.daun.word.global.infra.solvedac.dto.ProblemCount;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.Title;
import com.daun.word.global.vo.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.problem;
import static com.daun.word.Fixture.Fixture.tag;
import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProblemRepositoryTest {

    @Autowired
    private ProblemRepository problemRepository;

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
        GlobalId<Problem, Integer> problemGlobalId = GlobalId.of(Problem.class, problem().getId());
        GlobalId<Tag, Integer> tagGlobalId = GlobalId.of(Tag.class, tag().getId());
        int saved = problemRepository.saveProblemTag(problemGlobalId, tagGlobalId);
        //then
        assertThat(saved).isOne();
    }


    @DisplayName(value = "아이디로 문제를 조회한다")
    @Test
    void findById() throws Exception {
        //given
        GlobalId<Problem, Integer> globalId = GlobalId.of(Problem.class, 16120);
        //when
        Problem problem = problemRepository.findById(globalId).orElseThrow(NoSuchElementException::new);
        //then
        assertThat(problem).isNotNull();
        assertThat(problem.getId()).isEqualTo(16120);
    }

    @DisplayName(value = "티어별 문제 수를 조회한다")
    @Test
    void countByGroup() throws Exception {
        //given
        List<ProblemCount> problemCounts = problemRepository.countByGroup();
        System.out.println(problemCounts);
        //when&&then
        assertThat(problemCounts).isNotNull();
        assertThat(problemCounts.isEmpty()).isFalse();
        assertThat(problemCounts.get(0)).isNotNull();
        assertThat(problemCounts.get(0).getCount()).isGreaterThan(0);
    }

}
