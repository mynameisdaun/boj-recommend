package com.daun.word.domain.study.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.study.service.StudyHashService;
import com.daun.word.global.vo.BaseEntity;
import com.daun.word.global.vo.Name;
import lombok.*;

import javax.naming.AuthenticationException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "study")
@Table(name = "study")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Study extends BaseEntity {
    @Id
    @Column(name = "study_id", nullable = false, columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member leader;

    @Column(name = "max_member_count", nullable = false)
    private int maxMemberCount;

    @Embedded
    private Name studyName;

    @Column(name = "hash", nullable = false, columnDefinition = "varbinary(64)")
    private String hash;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<StudyMember> studyMembers = new ArrayList<>();

    public int currentMemberCount() {
        return (int) studyMembers.stream().filter(StudyMember::isValid).count();
    }

    public void auth(String key, StudyHashService studyHashService) throws AuthenticationException {
        if (!studyHashService.sha256(key).equals(this.hash)) {
            throw new AuthenticationException("올바른 스터디 키가 아닙니다.");
        }
    }

    public void enrollMember(StudyMember sm) {
        if (this.studyMembers.contains(sm)) {
            throw new IllegalStateException("이미 스터디에 참여하고 있는 회원입니다.");
        }
        this.studyMembers.add(sm);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Study study = (Study) o;

        return id != null ? id.equals(study.id) : study.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
