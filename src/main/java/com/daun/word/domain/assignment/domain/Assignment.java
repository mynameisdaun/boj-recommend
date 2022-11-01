package com.daun.word.domain.assignment.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.vo.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "assignment")
@Table(name = "assignment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Assignment extends BaseEntity {

    @Id
    @Column(name = "assignment_id", nullable = false, columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private boolean complete;

    @Column(name = "complete_at", nullable = true)
    private Date completeAt;

    public Assignment complete() {
        if (this.complete) {
            throw new IllegalStateException("이미 완료된 과제입니다");
        }
        this.complete = true;
        this.completeAt = new Date();
        return this;
    }

    public Assignment(UUID id, Member member, Problem problem) {
        this.id = id;
        this.member = member;
        this.problem = problem;
        this.complete = false;
        this.completeAt = null;
    }
}
