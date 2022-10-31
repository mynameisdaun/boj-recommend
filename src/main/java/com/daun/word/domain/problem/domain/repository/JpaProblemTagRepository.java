package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.ProblemTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProblemTagRepository extends ProblemTagRepository, JpaRepository<ProblemTag, Long> {
}