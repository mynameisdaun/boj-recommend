package com.daun.word.domain.study.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.global.vo.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "studyMember")
@Table(name = "study_member")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class StudyMember extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "valid", columnDefinition = "bit default 0")
    private boolean valid;

    public StudyMember(Study study, Member member) {
        this.study = study;
        this.member = member;
    }

    public String email() {
        return this.member.getEmail().getValue();
    }

}

