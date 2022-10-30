package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.Study;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudyRepository {
    Study save(final Study study);

    Optional<Study> findById(UUID id);

    List<Study> findAll();

    List<Study> findAllByIdIn(List<UUID> ids);
}
