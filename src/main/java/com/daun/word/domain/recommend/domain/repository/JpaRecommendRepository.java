package com.daun.word.domain.recommend.domain.repository;

import com.daun.word.domain.recommend.domain.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRecommendRepository extends RecommendRepository, JpaRepository<Recommend, UUID> {
}
