package com.daun.word.domain.problem.domain;

import com.daun.word.global.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "problemTag")
@Table(name = "problem_tag")
@Getter
@ToString (exclude = "problem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    //TODO: Response dto 생성 전까지 일시적으로 사용한다.
    @JsonBackReference
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public ProblemTag(Problem problem, Tag tag) {
        this.problem = problem;
        this.tag = tag;
    }
}
