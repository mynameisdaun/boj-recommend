package com.daun.word.domain.study.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.global.vo.BaseEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "studyMember")
@Table(name = "study_member")
@Getter @ToString
public class StudyMember extends BaseEntity {
    @Id
    @GeneratedValue
    private Long pk;

    @ManyToOne
    @JoinColumn(name = "study_id")
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

