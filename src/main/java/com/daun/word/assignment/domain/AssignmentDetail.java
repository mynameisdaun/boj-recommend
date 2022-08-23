package com.daun.word.assignment.domain;

import com.daun.word.assignment.dto.AssignmentSaveRequest;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
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
    private String quiz; // 문제 원문
    private String submission; // 과제 제출본
    private LocalDateTime createdAt; // 데이터 생성 일시
    private LocalDateTime updatedAt; // 데이터 수정 일시

    public static AssignmentDetail fromSaveRequest(Integer assignmentId, AssignmentSaveRequest.AssignmentDetailSaveRequest request) {
        return new AssignmentDetail(assignmentId, request.getChapterId(), request.getStartDateTime(), request.getEndDateTime(), request.getQuiz());
    }

    public AssignmentDetail(Integer assignmentId, Integer chapterId, LocalDateTime startDateTime, LocalDateTime endDateTime, String quiz) {
        this.assignmentId = assignmentId;
        this.chapterId = chapterId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.quiz = quiz;
    }


}
