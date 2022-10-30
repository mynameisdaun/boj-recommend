package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudyMemberRepository {
    StudyMember save(final StudyMember studyMember);

    Optional<StudyMember> findById(final Long pk);

    List<StudyMember> findAll();

    List<StudyMember> findAllByIdIn(final List<UUID> ids);

}
