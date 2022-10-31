package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository {

    Tag save(final Tag request);

    Tag saveAndFlush(final Tag request);

    Optional<Tag> findById(final Integer id);
}
