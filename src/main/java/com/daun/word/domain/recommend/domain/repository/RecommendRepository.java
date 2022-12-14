package com.daun.word.domain.recommend.domain.repository;

import com.daun.word.domain.recommend.domain.Recommend;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecommendRepository {

    Optional<Recommend> findById(final UUID id);

    Recommend save(final Recommend recommend);
}
