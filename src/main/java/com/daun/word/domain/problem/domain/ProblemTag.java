package com.daun.word.domain.problem.domain;

import com.daun.word.global.vo.BaseEntity;

import javax.persistence.*;

@Entity
public class ProblemTag extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
