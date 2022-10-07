package com.daun.word.domain.recommend.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.global.Id;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface RecommendRepository {

    int save(@Param("recommend") Recommend recommend);

    Optional<Recommend> findById(@Param("recommendId") Id<Recommend, Integer> recommendId);

    Optional<Recommend> findByMemberAndProblem(@Param("member") Member member, @Param("problem") Problem problem);
}
