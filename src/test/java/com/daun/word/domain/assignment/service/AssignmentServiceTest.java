package com.daun.word.domain.assignment.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.assignment.domain.repository.FakeAssignmentRepository;
import com.daun.word.domain.assignment.dto.AssignRequest;
import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.domain.repository.FakeProblemRepository;
import com.daun.word.domain.problem.domain.repository.FakeProblemTagRepository;
import com.daun.word.domain.problem.domain.repository.FakeTagRepository;
import com.daun.word.domain.problem.domain.repository.ProblemQueryRepository;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.domain.study.domain.repository.FakeStudyMemberRepository;
import com.daun.word.domain.study.domain.repository.FakeStudyRepository;
import com.daun.word.domain.study.service.DefaultHashService;
import com.daun.word.domain.study.service.StudyService;
import com.daun.word.global.infra.solvedac.FakeSolvedAcClient;
import com.daun.word.global.infra.solvedac.FakeSolvedAcDB;
import com.daun.word.global.infra.solvedac.SolvedAcClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.NoSuchElementException;
import java.util.UUID;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class AssignmentServiceTest {

    private AssignmentService assignmentService;

    private AssignmentRepository assignmentRepository;

    private ProblemQueryRepository problemQueryRepository;

    private MemberService memberService;

    private SolvedAcClient solvedAcClient;

    private ProblemService problemService;

    private StudyService studyService;

    @BeforeEach
    public void setUp() {
        //TODO: SetUp 부분 중복 어떻게 제거할 수 있을까? 혹은 짧은 예제로 변경 가능 하지 않을까?
        this.assignmentRepository = new FakeAssignmentRepository();
        this.memberService = new MemberService(new FakeMemberRepository(), new BCryptPasswordEncoder());

        FakeProblemRepository fakeProblemRepository = new FakeProblemRepository();

        this.solvedAcClient = new FakeSolvedAcClient();
        this.problemService = new ProblemService(fakeProblemRepository, new FakeTagRepository(), new FakeProblemTagRepository(), solvedAcClient, problemQueryRepository);

        FakeStudyRepository fakeStudyRepository = new FakeStudyRepository();
        FakeStudyMemberRepository fakeStudyMemberRepository = new FakeStudyMemberRepository();
        this.studyService = new StudyService(fakeStudyRepository, fakeStudyMemberRepository, memberService, new DefaultHashService());
        this.assignmentService = new AssignmentService(assignmentRepository, memberService, problemService, solvedAcClient, studyService, new DefaultHashService());
    }

    @DisplayName(value = "과제를 등록한다")
    @Test
    void save() throws Exception {
        //given
        AssignRequest request = new AssignRequest(daun9870jung().getEmail(), problem_1002().getId());
        //when
        Assignment save = assignmentService.assign(request);
        //then
        assertThat(save).isNotNull();
        assertAll(() -> assertThat(save.getId()).isInstanceOf(UUID.class), () -> assertThat(save.isComplete()).isFalse(), () -> assertThat(save.getMember()).isEqualTo(daun9870jung()), () -> assertThat(save.getProblem()).isEqualTo(problem_1002()));
    }

    @DisplayName(value = "이미 할당된 과제입니다")
    @Test
    void save_assigned() throws Exception {
        //given
        AssignRequest request = new AssignRequest(daun9870jung().getEmail(), problem_16120().getId());
        //when&&then
        assertThatThrownBy(() -> assignmentService.assign(request)).isInstanceOf(IllegalStateException.class).hasMessage(request.getProblemId() + "번 문제는 " + daun9870jung().getEmail().getValue() + "님께 이미 할당된 적 있는 문제입니다");
    }

    @DisplayName(value = "이미 완료된 과제입니다")
    @ValueSource(ints = {19, 29})
    @ParameterizedTest
    void save_completed(final Integer problemId) throws Exception {
        //given
        AssignRequest request = new AssignRequest(daun9870jung().getEmail(), problemId);
        //when&&then
        assertThatThrownBy(() -> assignmentService.assign(request)).isInstanceOf(IllegalStateException.class).hasMessage(problemId + "번 문제는 " + daun9870jung().getEmail().getValue() + "님께서 이미 완료한 과제입니다");
    }

    @DisplayName(value = "과제를 조회한다")
    @Test
    void findById() throws Exception {
        //given
        UUID id = assignment().getId();
        //when
        Assignment assignment = assignmentService.findById(id);
        //then
        assertThat(assignment).isNotNull();
        assertAll(() -> assertThat(assignment.getId()).isEqualTo(id), () -> assertThat(assignment.getMember()).isEqualTo(assignment().getMember()), () -> assertThat(assignment.getProblem()).isEqualTo(assignment().getProblem()), () -> assertThat(assignment.isComplete()).isEqualTo(assignment().isComplete()));
    }

    @DisplayName(value = "존재하지 않는 과제 입니다")
    @Test
    void findById_no_exist() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> assignmentService.findById(UUID.randomUUID())).isInstanceOf(NoSuchElementException.class).hasMessage("존재하지 않는 과제 입니다");
    }
}
