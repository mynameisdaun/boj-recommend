package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.Study;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudyRepository {
    Study save(final Study study);

    @Query("select s from study s join fetch s.leader join fetch s.studyMembers where s.id = :id")
    Optional<Study> findById(@Param("id") UUID id);

    List<Study> findAll();

    List<Study> findAllByIdIn(List<UUID> ids);
}
