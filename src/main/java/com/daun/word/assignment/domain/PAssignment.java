package com.daun.word.assignment.domain;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.problem.domain.repository.Problem;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PAssignment {
    private Integer id; // 과제 구분자
    private final Problem problem;
    private final Email assignFrom; // 과제 만든 회원
    private final Email assignTo; // 과제 할당된 회원
    private LocalDateTime startDateTime; // 과제 시작 일시
    private LocalDateTime endDateTime; // 과제 종료 일시
    private String openYn; // 과제 열람 여부
    private LocalDateTime openDateTime; // 과제 열람 일시
    private String completeYn; // 과제 제출 여부
    private LocalDateTime completeDateTime; // 과제 제출 일시
    private LocalDateTime createdAt; // 데이터 생성 일시
    private LocalDateTime updatedAt; // 데이터 수정 일시
}
