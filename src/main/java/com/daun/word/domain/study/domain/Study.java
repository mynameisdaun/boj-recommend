package com.daun.word.domain.study.domain;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.study.service.StudyHashService;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.YesNo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public final class Study {

    private Integer id;
    private final Member leader;
    private final Name studyName;
    private final String hash;
    private final YesNo deleteYn;
    private final List<Member> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
