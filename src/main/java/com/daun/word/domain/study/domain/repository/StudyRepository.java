package com.daun.word.domain.study.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.global.GlobalId;
import com.daun.word.global.vo.YesNo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface StudyRepository {

    int save(@Param("study") Study study);

    int saveStudyMember(@Param("studyId") UUID globalId, @Param("member") Member member, @Param("deleteYn") YesNo deleteYn);

    Optional<Study> findById(@Param("id") UUID studyGlobalId);
}
