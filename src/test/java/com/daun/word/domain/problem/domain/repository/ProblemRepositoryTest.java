package com.daun.word.domain.problem.domain.repository;

import com.daun.word.Fixture.Fixture;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.vo.Tier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class ProblemRepositoryTest {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        em.clear();
    }

    @DisplayName("문제를 저장한다")
    @Test
    void save() {
        //given
        SolvedAcProblem p = Fixture.solvedAcProblem(9_999_999);
        Problem problem = new Problem(p);
        problem.addTags(p.getTags().stream().map(t -> new ProblemTag(problem, new Tag(t))).collect(Collectors.toList()));
        //when
        Problem saved = problemRepository.save(problem);
        em.flush();
        //then
        assertThat(saved).isNotNull();
        assertAll(
                () -> assertThat(saved.getId()).isEqualTo(9_999_999),
                () -> assertThat(saved.getAcceptedUserCount()).isEqualTo(p.getAcceptedUserCount()),
                () -> assertThat(saved.getUrl().getValue()).contains("9999999"),
                () -> assertThat(saved.getTier().getLevel()).isEqualTo(p.getLevel()),
                () -> assertThat(saved.getTitle().getValue()).isEqualTo(p.getTitleKo()),
                () -> assertThat(saved.getProblemTags()).isNotNull(),
                () -> assertThat(saved.getProblemTags().get(0)).isNotNull(),
                () -> assertThat(saved.getProblemTags().size()).isEqualTo(p.getTags().size()),
                () -> assertThat(saved.getCreatedAt()).isNotNull(),
                () -> assertThat(saved.getUpdatedAt()).isNotNull()
        );
    }

    @DisplayName("아이디로 문제를 조회한다")
    @Test
    void findById() {
        //given&&when
        Problem find = problemRepository.findById(16120)
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(find).isNotNull();
        assertAll(
                () -> assertThat(find.getId()).isEqualTo(16120),
                () -> assertThat(find.getAcceptedUserCount()).isGreaterThanOrEqualTo(0),
                () -> assertThat(find.getUrl().getValue()).contains("16120"),
                () -> assertThat(find.getTier().getLevel()).isEqualTo(12),
                () -> assertThat(find.getTitle().getValue()).isEqualTo("PPAP"),
                () -> assertThat(find.getProblemTags()).isNotNull(),
                () -> assertThat(find.getProblemTags().get(0)).isNotNull(),
                () -> assertThat(find.getProblemTags().size()).isGreaterThan(0),
                () -> assertThat(find.getCreatedAt()).isNotNull(),
                () -> assertThat(find.getUpdatedAt()).isNotNull()
        );

    }

    @DisplayName("아이디들로 문제를 조회한다")
    @Test
    void findAllByIdIn() {
        //given
        List<Integer> ids = new ArrayList<>(Arrays.asList(16120, 1002));
        //when
        List<Problem> problems = problemRepository.findAllByIdIn(ids);
        //then
        assertThat(problems).isNotNull();
        assertAll(
                () -> assertThat(problems.size()).isEqualTo(2),
                () -> assertThat(problems.get(0).getId()).isIn(16120, 1002),
                () -> assertThat(problems.get(1).getId()).isIn(16120, 1002),
                () -> assertThat(problems.get(0)).isNotNull(),
                () -> assertThat(problems.get(1)).isNotNull()
        );
    }

    @DisplayName("없는 아이디들로는 문제를 조회한다")
    @Test
    void findAllByIdIn_no_exist() {
        //given
        List<Integer> ids = new ArrayList<>(Arrays.asList(222_222_444, 333_333_666));
        //when
        List<Problem> problems = problemRepository.findAllByIdIn(ids);
        //then
        assertThat(problems).isNotNull();
        assertAll(
                () -> assertThat(problems.size()).isEqualTo(0)
        );
    }

    @DisplayName("특정 티어 사이의 문제를 푼 사람 수로 내림차순 정렬하여 조회한다.")
    @Test
    void findAllByTierBetweenOrderByAcceptedUserCountDesc() {
        //given&&when
        List<Problem> problems = problemRepository.findAllByTierBetweenOrderByAcceptedUserCountDesc(new Tier(11), new Tier(15));
        for (Problem p : problems) {
            System.out.println("====================");
            System.out.println(p);
        }
        //then
        assertThat(problems).isNotNull();
        assertAll(
                () -> assertThat(problems.size()).isEqualTo(3),
                () -> assertThat(problems.get(0).getTier().getLevel()).isBetween(11, 15),
                () -> assertThat(problems.get(0).getAcceptedUserCount()).isGreaterThanOrEqualTo(problems.get(1).getAcceptedUserCount()),
                () -> assertThat(problems.get(1).getTier().getLevel()).isBetween(11, 15),
                () -> assertThat(problems.get(1).getAcceptedUserCount()).isGreaterThanOrEqualTo(problems.get(2).getAcceptedUserCount()),
                () -> assertThat(problems.get(2).getTier().getLevel()).isBetween(11, 15)
        );
    }
}
