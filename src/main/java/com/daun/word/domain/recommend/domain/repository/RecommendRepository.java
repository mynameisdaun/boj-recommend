package com.daun.word.domain.recommend.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.global.GlobalId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface RecommendRepository {

    int save(@Param("recommend") Recommend recommend);

    Optional<Recommend> findById(@Param("recommendId") GlobalId<Recommend, Integer> recommendGlobalId);

    Optional<Recommend> findByMemberAndProblem(@Param("member") Member member, @Param("problem") Problem problem);

    List<Recommend> findByMemberAndProblems(@Param("member") Member member, @Param("problems") List<Problem> problems);

    List<Recommend> findByMembersWhereCreatedBefore(@Param("members") List<Member> member, @Param("date") LocalDateTime date);
}
