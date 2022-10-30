package com.daun.word.domain.assignment.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.vo.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "assignment")
@Table(name = "assignment")
@NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter @ToString
public class Assignment extends BaseEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private boolean complete;

    public Assignment(UUID id, Member member, Problem problem) {
        this.id = id;
        this.member = member;
        this.problem = problem;
        this.complete=false;
    }
}
