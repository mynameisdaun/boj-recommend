package com.daun.word.domain.problem.service;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.ProblemTag;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.domain.problem.domain.repository.*;
import com.daun.word.global.infra.solvedac.FakeSolvedAcClient;
import com.daun.word.global.infra.solvedac.FakeSolvedAcDB;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.daun.word.Fixture.Fixture.problem_16120;
import static com.daun.word.Fixture.Fixture.solvedAcProblem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProblemServiceTest {

    private ProblemService problemService;

    private ProblemRepository problemRepository;

    private TagRepository tagRepository;

    private ProblemTagRepository problemTagRepository;

    private SolvedAcClient solvedAcClient;

    @BeforeEach
    void setUp() {
        Problem problem = problem_16120();

        this.tagRepository = new FakeTagRepository();
        Tag tag_1 = new Tag(33, "greedy", new Title("그리디 알고리즘"));
        Tag tag_2 = new Tag(71, "stack", new Title("스택"));
        Tag tag_3 = new Tag(158, "string", new Title("문자열"));
        Tag tag_4 = new Tag(175, "data_structures", new Title("자료 구조"));
        tagRepository.save(tag_1);
        tagRepository.save(tag_2);
        tagRepository.save(tag_3);
        tagRepository.save(tag_4);

        this.problemTagRepository = new FakeProblemTagRepository();
        problemTagRepository.save(new ProblemTag(problem, tag_1));
        problemTagRepository.save(new ProblemTag(problem, tag_2));
        problemTagRepository.save(new ProblemTag(problem, tag_3));
        problemTagRepository.save(new ProblemTag(problem, tag_4));

        FakeSolvedAcDB fakeSolvedAcDB = new FakeSolvedAcDB();

        fakeSolvedAcDB.addProblem(solvedAcProblem(1));
        this.solvedAcClient = new FakeSolvedAcClient(fakeSolvedAcDB);

        this.problemRepository = new FakeProblemRepositorySuite(problemTagRepository);
        this.problemRepository.save(problem);

        this.problemService = new ProblemService(problemRepository, tagRepository, problemTagRepository, solvedAcClient);
    }

    @DisplayName(value = "문제를 조회한다")
    @Test
    void findById() throws Exception {
        //given
        Integer id = problem_16120().getId();
        //when
        Problem problem = problemService.findById(id);
        //then
        assertThat(problem).isNotNull();
        assertAll(
                () -> assertThat(problem.getId()).isEqualTo(id),
                () -> assertThat(problem.getTier()).isInstanceOf(Tier.class),
                () -> assertThat(problem.getProblemTags().size()).isPositive(),
                () -> assertThat(problem.getProblemTags().get(0)).isInstanceOf(ProblemTag.class),
                () -> assertThat(problem.getAcceptedUserCount()).isPositive()
        );
    }

    @DisplayName(value = "문제를 찾을 수 없는 경우 BOJ에서 조회한다")
    @Test
    void findById_no_exist() throws Exception {
        //given
        Integer id = 1;
        //when
        Problem problem = problemService.findById(id);
        //then
        assertThat(problem).isNotNull();
        assertAll(
                () -> assertThat(problem.getId()).isEqualTo(id),
                () -> assertThat(problem.getTier()).isInstanceOf(Tier.class),
                () -> assertThat(problem.getProblemTags().size()).isPositive(),
                () -> assertThat(problem.getProblemTags().get(0)).isInstanceOf(ProblemTag.class),
                () -> assertThat(problem.getAcceptedUserCount()).isPositive()
        );
    }

    @DisplayName(value = "BOJ문제 로컬 저장")
    @Test
    void save() throws Exception {
        //given
        SolvedAcProblem request = solvedAcProblem(19);
        //when
        Problem save = problemService.save(request);
        //then
        assertThat(save).isNotNull();
        assertAll(
                () -> assertThat(save.getId()).isEqualTo(request.getProblemId()),
                () -> assertThat(save.getTitle().getValue()).isEqualTo(request.getTitleKo()),
                () -> assertThat(save.getProblemTags().size()).isEqualTo(request.getTags().size()),
                () -> assertThat(save.getTier().getLevel()).isEqualTo(request.getLevel()),
                () -> assertThat(save.getAcceptedUserCount()).isEqualTo(request.getAcceptedUserCount()),
                () -> assertThat(save.getUrl().getValue()).contains(String.valueOf(request.getProblemId()))
        );
    }
}
