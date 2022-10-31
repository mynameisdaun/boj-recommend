package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeTagRepository implements TagRepository {

    private final Map<Integer, Tag> table = new HashMap<>();

    @Override
    public Tag save(Tag request) {
        table.put(request.getId(), request);
        return request;
    }

    @Override
    public Tag saveAndFlush(Tag request) {
        return save(request);
    }

    @Override
    public Optional<Tag> findById(Integer id) {
        return Optional.ofNullable(table.get(id));
    }
}
