package com.daun.word.domain.study.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.study.service.StudyHashService;
import com.daun.word.global.vo.BaseEntity;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.YesNo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.naming.AuthenticationException;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "study")
@Getter
public class Study extends BaseEntity {
    @Id
    @Column(name = "study_id", nullable = false, columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "email")
    private Member leader;

    private Name studyName;
    @Column(name = "hash", nullable = false, columnDefinition = "varbinary(64)")
    private String hash;

    @ManyToMany
    @JoinColumn(name = "member_id")
    private List<Member> members;

    public Study(UUID id, Member leader, Name studyName, String hash, List<Member> members) {
        super();
        this.id = id;
        this.leader = leader;
        this.studyName = studyName;
        this.hash = hash;
        this.members = members;
    }

    public void auth(String key, StudyHashService studyHashService) throws AuthenticationException {
        if (!studyHashService.sha256(key).equals(this.hash)) {
            throw new AuthenticationException("올바른 스터디 키가 아닙니다.");
        }
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
