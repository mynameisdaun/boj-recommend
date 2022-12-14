package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.global.vo.Title;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeTagRepository implements TagRepository {

    private final Map<Integer, Tag> table = new HashMap<>();

    public FakeTagRepository() {
        save(new Tag(33, "greedy", new Title("그리디 알고리즘")));
        save(new Tag(71, "stack", new Title("스택")));
        save(new Tag(158, "string", new Title("문자열")));
        save(new Tag(175, "data_structures", new Title("자료 구조")));
    }

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
