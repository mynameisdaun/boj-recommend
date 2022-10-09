package com.daun.word.domain.assignment.domain;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.problem.domain.Problem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Assignment {
    private Integer id; // 과제 구분자
    private Problem problem;
    private Email assignFrom; // 과제 만든 회원
    private Email assignTo; // 과제 할당된 회원
    private LocalDateTime startDateTime; // 과제 시작 일시
    private LocalDateTime endDateTime; // 과제 종료 일시
    private String openYn; // 과제 열람 여부
    private LocalDateTime openDateTime; // 과제 열람 일시
    private String completeYn; // 과제 제출 여부
    private LocalDateTime completeDateTime; // 과제 제출 일시
    private LocalDateTime createdAt; // 데이터 생성 일시
    private LocalDateTime updatedAt; // 데이터 수정 일시

    public Assignment(Problem problem, Email assignFrom, Email assignTo, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.problem = problem;
        this.assignFrom = assignFrom;
        this.assignTo = assignTo;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean isComplete() {
        return this.completeYn.equals("Y");
    }

    public void complete() {
        if (isComplete()) {
            throw new IllegalStateException("이미 완료한 과제는 다시 완료할 수 없습니다");
        }
        this.completeYn = "Y";
        this.completeDateTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assignment that = (Assignment) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
