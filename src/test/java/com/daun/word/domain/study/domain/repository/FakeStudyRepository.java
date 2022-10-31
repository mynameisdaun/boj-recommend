package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.Study;

import java.util.*;

import static java.util.stream.Collectors.toList;


public class FakeStudyRepository implements StudyRepository {
    private final Map<UUID, Study> table = new HashMap<>();

    @Override
    public Study save(final Study study) {
        table.put(study.getId(), study);
        return study;
    }

    @Override
    public Optional<Study> findById(final UUID id) {
        return Optional.ofNullable(table.get(id));
    }

    @Override
    public List<Study> findAll() {
        return new ArrayList<>(table.values());
    }

    @Override
    public List<Study> findAllByIdIn(final List<UUID> ids) {
        return table.values()
                .stream()
                .filter(s -> ids.contains(s.getId()))
                .collect(toList());
    }

}
