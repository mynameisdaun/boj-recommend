package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMemberJpaRepository extends JpaRepository<StudyMember, Long> {

}
