package com.daun.word.domain.assignment.domain;

import com.daun.word.domain.assignment.dto.d_AssignmentSaveRequest;
import lombok.*;

import java.time.LocalDateTime;

import static com.daun.word.global.utils.DateUtils.now;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AssignmentDetail {
    private Integer id;  //과제 상세정보 구분자(seq)
    private Integer assignmentId; //과제 구분자(seq)
    private Integer chapterId; // 챕터 구분자(seq)
    private LocalDateTime startDateTime; // 과제 시작 일시
    private LocalDateTime endDateTime; // 과제 종료 일시
    private String openYn; // 과제 열람 여부
    private LocalDateTime openDateTime; // 과제 열람 일시
    private String completeYn; // 과제 제출 여부
    private LocalDateTime completeDateTime; // 과제 제출 일시
    private LocalDateTime createdAt; // 데이터 생성 일시
    private LocalDateTime updatedAt; // 데이터 수정 일시

    public AssignmentDetail(Integer assignmentId, Integer chapterId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.assignmentId = assignmentId;
        this.chapterId = chapterId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static AssignmentDetail fromSaveRequest(Integer assignmentId, d_AssignmentSaveRequest.AssignmentDetailSaveRequest request) {
        return new AssignmentDetail(assignmentId, request.getChapterId(), request.getStartDateTime(), request.getEndDateTime());
    }

    public boolean isOpen() {
        return this.openYn.equals("Y");
    }

    public boolean isComplete() {
        return this.completeYn.equals("Y");
    }

    public void open() {
        if (this.isOpen()) {
            throw new IllegalStateException("한번 열람한 과제는 다시 열람할 수 없습니다");
        }
        this.openYn = "Y";
        this.openDateTime = now();
    }

    public void submission(String submission) {
        if (!this.isOpen()) {
            throw new IllegalStateException("열람하지 않은 과제는 제출할 수 없습니다.");
        }
        if (this.isComplete()) {
            throw new IllegalStateException("한번 제출한 과제는 다시 제출할 수 없습니다.");
        }
        this.completeYn = "Y";
        this.completeDateTime = now();
    }
}
