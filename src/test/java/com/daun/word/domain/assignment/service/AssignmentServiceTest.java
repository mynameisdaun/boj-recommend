package com.daun.word.domain.assignment.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.assignment.domain.repository.FakeAssignmentRepository;
import com.daun.word.domain.assignment.dto.AssignmentSaveRequest;
import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.domain.repository.FakeProblemRepository;
import com.daun.word.domain.problem.domain.repository.FakeProblemTagRepository;
import com.daun.word.domain.problem.domain.repository.FakeTagRepository;
import com.daun.word.domain.problem.service.ProblemService;
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

    private MemberService memberService;

    private SolvedAcClient solvedAcClient;

    private ProblemService problemService;

    @BeforeEach
    public void setUp() {
        //TODO: SetUp 부분 중복 어떻게 제거할 수 있을까? 혹은 짧은 예제로 변경 가능 하지 않을까?
        this.assignmentRepository = new FakeAssignmentRepository();
        this.assignmentRepository.save(assignment());
        this.assignmentRepository.save(new Assignment(UUID.randomUUID(), member_1(), problem_19()));
        this.assignmentRepository.save(new Assignment(UUID.randomUUID(), member_1(), problem_29()).complete());

        FakeMemberRepository memberRepository = new FakeMemberRepository();
        memberRepository.save(member_1());
        this.memberService = new MemberService(memberRepository, new BCryptPasswordEncoder());


        FakeProblemRepository fakeProblemRepository = new FakeProblemRepository();
        fakeProblemRepository.save(problem_16120());
        fakeProblemRepository.save(problem_1002());
        fakeProblemRepository.save(problem_19());
        fakeProblemRepository.save(problem_29());

        FakeSolvedAcDB fakeSolvedAcDB = new FakeSolvedAcDB();
        fakeSolvedAcDB.addMember(solvedAcMember(member_1().getEmail().getValue()));
        fakeSolvedAcDB.addProblem(solvedAcProblem(16120));
        fakeSolvedAcDB.addProblem(solvedAcProblem(1002));
        fakeSolvedAcDB.addProblem(solvedAcProblem(19));
        fakeSolvedAcDB.addProblem(solvedAcProblem(29));

        fakeSolvedAcDB.memberSolve(member_1().getEmail().getValue(), problem_19().getId());
        fakeSolvedAcDB.memberSolve(member_1().getEmail().getValue(), problem_29().getId());
        this.solvedAcClient = new FakeSolvedAcClient(fakeSolvedAcDB);
        this.problemService = new ProblemService(fakeProblemRepository, new FakeTagRepository(), new FakeProblemTagRepository(), solvedAcClient);

        this.assignmentService = new AssignmentService(assignmentRepository, memberService, problemService, solvedAcClient);
    }

    @DisplayName(value = "과제를 등록한다")
    @Test
    void save() throws Exception {
        //given
        AssignmentSaveRequest request = new AssignmentSaveRequest(member_1().getEmail(), problem_1002().getId());
        //when
        Assignment save = assignmentService.save(request);
        //then
        assertThat(save).isNotNull();
        assertAll(
                () -> assertThat(save.getId()).isInstanceOf(UUID.class),
                () -> assertThat(save.isComplete()).isFalse(),
                () -> assertThat(save.getMember()).isEqualTo(member_1()),
                () -> assertThat(save.getProblem()).isEqualTo(problem_1002())
        );
    }

    @DisplayName(value = "이미 할당된 과제입니다")
    @Test
    void save_assigned() throws Exception {
        //given
        AssignmentSaveRequest request = new AssignmentSaveRequest(member_1().getEmail(), problem_16120().getId());
        //when&&then
        assertThatThrownBy(() -> assignmentService.save(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 할당된 과제입니다");
    }

    @DisplayName(value = "이미 완료된 과제입니다")
    @ValueSource(ints = {19, 29})
    @ParameterizedTest
    void save_completed(final Integer problemId) throws Exception {
        //given
        AssignmentSaveRequest request = new AssignmentSaveRequest(member_1().getEmail(), problemId);
        //when&&then
        assertThatThrownBy(() -> assignmentService.save(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 완료된 과제입니다");
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
        assertAll(
                () -> assertThat(assignment.getId()).isEqualTo(id),
                () -> assertThat(assignment.getMember()).isEqualTo(assignment().getMember()),
                () -> assertThat(assignment.getProblem()).isEqualTo(assignment().getProblem()),
                () -> assertThat(assignment.isComplete()).isEqualTo(assignment().isComplete())
        );
    }

    @DisplayName(value = "존재하지 않는 과제 입니다")
    @Test
    void findById_no_exist() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> assignmentService.findById(UUID.randomUUID()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 과제 입니다");
    }
}
