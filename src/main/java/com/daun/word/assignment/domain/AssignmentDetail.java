package com.daun.word.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AssignmentDetail {
    private Integer id;  //과제 상세정보 구분자(seq)
    private Integer assignmentId; //과제 구분자(seq)
    private Integer chapterId; // 챕터 구분자(seq)
    private LocalDateTime startDatetime; // 과제 시작 일시
    private LocalDateTime endDatetime; // 과제 종료 일시
    private String openYn; // 과제 열람 여부
    private LocalDateTime openDatetime; // 과제 열람 일시
    private String completeYn; // 과제 제출 여부
    private LocalDateTime completeDatetime; // 과제 제출 일시
    private String quiz; // 문제 원문
    private String submission; // 과제 제출본
    private LocalDateTime createdAt; // 데이터 생성 일시
    private LocalDateTime updatedAt; // 데이터 수정 일시
}
