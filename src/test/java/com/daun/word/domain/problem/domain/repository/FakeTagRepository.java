package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Tag;

import java.util.Optional;

public class FakeTagRepository implements TagRepository{

    @Override
    public Tag save(Tag request) {
        return null;
    }

    @Override
    public Tag saveAndFlush(Tag request) {
        return null;
    }

    @Override
    public Optional<Tag> findById(Integer id) {
        return Optional.empty();
    }
}
