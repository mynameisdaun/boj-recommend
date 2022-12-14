package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.study.domain.StudyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaStudyMemberRepository extends StudyMemberRepository, JpaRepository<StudyMember, Long> {

}
