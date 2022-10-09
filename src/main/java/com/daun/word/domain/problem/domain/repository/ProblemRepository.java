package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.vo.Tag;
import com.daun.word.global.Id;
import com.daun.word.global.vo.Tier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface ProblemRepository {

    /* 문제 저장 */
    int save(@Param("problem") Problem problem);

    /* 태그 저장 */
    int saveTag(@Param("tag") Tag tag);

    /* 문제_태그 매핑 저장 */
    int saveProblemTag(@Param("problemId") Id<Problem, Integer> problemId, @Param("tagId") Id<Tag, Integer> tagId);

    /* 아이디로 문제를 조회한다 */
    Optional<Problem> findById(@Param("problemId") Id<Problem, Integer> id);

    /* 티어로 문제 조회 */
    List<Problem> findByTierBetweenOrderBySolvedCountDesc(@Param("goe") Tier goe, @Param("loe") Tier loe, @Param("offset") int offset, @Param("limit") int limit);
}
