package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudyJpaRepository extends JpaRepository<Study, UUID> {

}
