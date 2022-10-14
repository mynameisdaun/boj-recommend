package com.daun.word.domain.assignment.domain;

import com.daun.word.domain.assignment.dto.AssignmentRequest;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.global.vo.YesNo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
public class Assignment {
    private Integer id; // 과제 구분자
    private final Study study;
    private final Recommend recommend;
    private final Member assignTo; // 과제 할당된 회원
    private final LocalDateTime startDateTime; // 과제 시작 일시
    private final LocalDateTime endDateTime; // 과제 종료 일시
    private final YesNo completeYn; // 과제 제출 여부
    private final LocalDateTime completeDateTime; // 과제 제출 일시
    private LocalDateTime createdAt; // 데이터 생성 일시
    private LocalDateTime updatedAt; // 데이터 수정 일시

    public Assignment(AssignmentRequest request) {
        this.study= request.getStudy();
        this.recommend = request.getRecommend();
        this.assignTo = request.getAssignTo();
        this.startDateTime = request.getStartDateTime();
        this.endDateTime = request.getEndDateTime();
        this.completeYn = YesNo.N;
        this.completeDateTime = null;
    }

    public boolean isComplete() {
        return this.completeYn == YesNo.Y;
    }

    public Assignment complete() {
        if (isComplete()) {
            throw new IllegalStateException("이미 완료한 과제는 다시 완료할 수 없습니다");
        }
        return new Assignment(this.id, this.study, this.recommend, this.assignTo, this.startDateTime, this.endDateTime, YesNo.Y, LocalDateTime.now(), this.createdAt, this.updatedAt);
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
