package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.Fixture.Fixture;
import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.AssignmentDetail;
import com.daun.word.domain.member.domain.vo.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AssignmentRepositoryTest {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @DisplayName(value = "과제 정보를 업데이트한다")
    @Test
    void detailUpdate() throws Exception {
        //given
        AssignmentDetail detail = Fixture.assignmentDetail_unOpen();
        LocalDateTime after100years = LocalDateTime.now().plusYears(100).truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime after101years = LocalDateTime.now().plusYears(101).truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime after102years = LocalDateTime.now().plusYears(102).truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime after105years = LocalDateTime.now().plusYears(105).truncatedTo(ChronoUnit.SECONDS);
        AssignmentDetail updated = new AssignmentDetail(
                detail.getId(),
                detail.getAssignmentId(),
                detail.getChapterId(),
                after100years,
                after105years,
                "Y",
                after101years,
                "Y",
                after102years,
                detail.getCreatedAt(),
                detail.getUpdatedAt()
        );
        //when
        assignmentRepository.updateDetail(updated);
        //then
        assertAll(
                () -> assertThat(updated.getStartDateTime()).isEqualToIgnoringNanos(after100years),
                () -> assertThat(updated.getEndDateTime()).isEqualToIgnoringNanos(after105years),
                () -> assertThat(updated.getOpenYn()).isEqualTo("Y"),
                () -> assertThat(updated.getOpenDateTime()).isEqualToIgnoringNanos(after101years),
                () -> assertThat(updated.getCompleteYn()).isEqualTo("Y"),
                () -> assertThat(updated.getCompleteDateTime()).isEqualToIgnoringNanos(after102years)
        );
    }

    @DisplayName(value = "과제 id로 회원을 조회한다")
    @Test
    void findById() throws Exception {
        //given
        Integer id = Integer.valueOf(1);
        //when
        Optional<Assignment> assignment = assignmentRepository.findAssignmentById(id);
        //then
        assertThat(assignment).isNotNull();
        assertAll(
                () -> assertThat(assignment.get().getId()).isEqualTo(Integer.valueOf(1)),
                () -> assertThat(assignment.get().getAssignFrom()).isEqualTo(new Email("tester1@weword.com")),
                () -> assertThat(assignment.get().getAssignTo()).isEqualTo(new Email("daun9870jung"))
        );
    }

    @DisplayName(value = "존재하지 않는 id로 회원을 조회할 수 없다")
    @Test
    void findById_no_exist() throws Exception {
        //given
        Integer id = Integer.MAX_VALUE;
        //when
        Optional<Assignment> assignment = assignmentRepository.findAssignmentById(id);
        //then
        assertThat(assignment).isNotNull();
        assertAll(
                () -> assertThat(assignment.isPresent()).isFalse()
        );
    }

    @DisplayName(value = "과제를 등록한다")
    @Test
    void save() throws Exception {
        //given
        Assignment assignment = new Assignment(member().getEmail(), another_member().getEmail(), workbook().getId());
        //when
        int saved = assignmentRepository.saved(assignment);
        //then
        assertThat(saved).isEqualTo(1);
    }

    @DisplayName(value = "과제 세부정보를 등록한다")
    @Test
    void save_assignment_detail() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.now();
        AssignmentDetail detail = new AssignmentDetail(assignment().getId(), chapter().getId(), now, now.plusDays(1));
        //when
        int saved = assignmentRepository.saveDetail(detail);
        //then
        assertThat(saved).isEqualTo(1);
    }
}
