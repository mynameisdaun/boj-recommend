package com.daun.word.domain.study.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.global.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "studyMember")
@Table(name = "study_member")
@AllArgsConstructor @Getter @ToString @EqualsAndHashCode(of = "id")
public class StudyMember extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_id")
    @JsonBackReference
    private Study study;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public StudyMember(Study study, Member member) {
        this.study = study;
        this.member = member;
    }

    protected StudyMember() {
    }

}

