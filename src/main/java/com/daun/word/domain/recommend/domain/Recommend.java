package com.daun.word.domain.recommend.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.domain.vo.RecommendType;
import com.daun.word.global.vo.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "recommend")
@Table(name = "recommend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Recommend extends BaseEntity {
    @Id
    @Column(name = "recommend_id", nullable = false, columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "recommend_type")
    private RecommendType type;

    public Recommend(UUID id, Problem problem, Member member, RecommendType type) {
        this.id = id;
        this.problem = problem;
        this.member = member;
        this.type = type;
    }
}
