package com.daun.word.domain.recommend.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.vo.BaseEntity;
import com.daun.word.global.vo.CreatedAt;
import com.daun.word.global.vo.UpdatedAt;
import com.daun.word.global.vo.YesNo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "recommend")
@NoArgsConstructor
@Getter
@ToString
public class Recommend extends BaseEntity {
    @Id
    @Column(name = "recommend_id", nullable = false, columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Recommend(UUID id, Problem problem, Member member) {
        this.id = id;
        this.problem = problem;
        this.member = member;
    }
}
