package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTagRepository extends TagRepository, JpaRepository<Tag, Long> {

}
