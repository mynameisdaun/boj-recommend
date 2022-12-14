package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.StudyMember;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class FakeStudyMemberRepository implements StudyMemberRepository {
    private final Map<Long, StudyMember> studyMembers = new HashMap<>();

    @Override
    public StudyMember save(StudyMember studyMember) {
        studyMembers.put(Long.valueOf(studyMembers.size() + 1), studyMember);
        studyMember = new StudyMember(Long.valueOf(studyMembers.size()), studyMember.getStudy(), studyMember.getMember());
        return studyMember;
    }

    @Override
    public Optional<StudyMember> findById(final Long pk) {
        return Optional.ofNullable(studyMembers.get(pk));
    }

    @Override
    public List<StudyMember> findAll() {
        return new ArrayList<>(studyMembers.values());
    }

    @Override
    public List<StudyMember> findAllByIdIn(final List<UUID> ids) {
        return studyMembers.values()
                .stream()
                .filter(s -> ids.contains(s.getId()))
                .collect(toList());
    }

}
