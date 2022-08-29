package com.daun.word.assignment.service;

import com.daun.word.assignment.domain.repository.FakeAssignmentRepository;
import com.daun.word.assignment.dto.AssignmentDetailResponse;
import com.daun.word.assignment.dto.AssignmentSaveRequest;
import com.daun.word.assignment.dto.AssignmentSaveResponse;
import com.daun.word.assignment.dto.SubmissionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.daun.word.Fixture.Fixture.assignmentDetail_open_unComplete;
import static com.daun.word.Fixture.Fixture.assignmentDetail_unOpen;
import static com.daun.word.assignment.dto.AssignmentSaveRequest.AssignmentDetailSaveRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


class AssignmentServiceTest {

    private AssignmentService assignmentService;

    @BeforeEach
    public void setUp() {
        assignmentService = new AssignmentService(new FakeAssignmentRepository());
    }

    @DisplayName(value = "과제를 제출한다")
    @Test
    void submission() throws Exception {
        //given
        String submission = "fake-submission";
        SubmissionRequest request = new SubmissionRequest(assignmentDetail_open_unComplete().getId(), submission);
        //when
        AssignmentDetailResponse response = assignmentService.submission(request);
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getDetail().getCompleteYn()).isEqualTo("Y"),
                () -> assertThat(response.getDetail().getCompleteDateTime()).isNotNull()
        );
    }

    @DisplayName(value = "이미 제출한 과제는 다시 제출할 수 없다.")
    @Test
    void submission_already_submitted() throws Exception {
        //given
        String submission = "fake-submission";
        SubmissionRequest request = new SubmissionRequest(assignmentDetail_open_unComplete().getAssignmentId(), submission);
        //when&&then
        assertThatThrownBy(() -> assignmentService.submission(request))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName(value = "열람하지 않은 과제는 제출할 수 없다.")
    @Test
    void submission_unOpened() throws Exception {
        //given
        String submission = "fake-submission";
        SubmissionRequest request = new SubmissionRequest(assignmentDetail_unOpen().getAssignmentId(), submission);
        //when&&then
        assertThatThrownBy(() -> assignmentService.submission(request))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName(value = "과제를 열람한다")
    @Test
    void open() throws Exception {
        //given&&when
        AssignmentDetailResponse response = assignmentService.open(assignmentDetail_unOpen().getId());
        //then
        assertThat(response).isNotNull();
        assertAll(
                () -> assertThat(response.getDetail().getOpenYn()).isEqualTo("Y"),
                () -> assertThat(response.getDetail().getOpenDateTime()).isNotNull()
        );
    }

    @DisplayName(value = "이미 열람한 과제는 다시 열람할 수 없다, 과제 열람은 한번만 가능하다")
    @Test
    void open_already_opened() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> assignmentService.open(assignmentDetail_open_unComplete().getId()))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName(value = "과제를 저장한다")
    @Test
    void save() throws Exception {
        //given
        List<AssignmentDetailSaveRequest> details = new ArrayList<>();
        details.add(new AssignmentDetailSaveRequest(Integer.valueOf(1), "2024-08-20 15:43:51", "2024-08-20 15:43:51", "tmp-quizs"));
        details.add(new AssignmentDetailSaveRequest(Integer.valueOf(2), "2024-08-20 15:43:51", "2024-08-20 15:43:51", "tmp-quizs"));
        AssignmentSaveRequest request = new AssignmentSaveRequest(
                Integer.valueOf(1),
                "tester1@weword.com",
                "tester2@weword.com",
                details);
        //when
        AssignmentSaveResponse save = assignmentService.save(request);
        //then
        assertThat(save).isNotNull();
        assertAll(
                () -> assertThat(save.getAssignment().getId()).isOne(),
                () -> assertThat(save.getDetails().size()).isEqualTo(2)
        );
    }

}
