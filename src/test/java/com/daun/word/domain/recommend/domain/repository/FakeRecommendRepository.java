package com.daun.word.domain.recommend.domain.repository;

import com.daun.word.domain.problem.domain.repository.FakeProblemRepository;
import com.daun.word.domain.recommend.domain.Recommend;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FakeRecommendRepository implements RecommendRepository {

    private final Map<UUID, Recommend> table = new HashMap<>();

    public FakeRecommendRepository() {

    }

    @Override
    public Optional<Recommend> findById(UUID id) {
        return Optional.ofNullable(table.get(id));
    }

    @Override
    public Recommend save(Recommend recommend) {
        table.put(recommend.getId(), recommend);
        return recommend;
    }
}
