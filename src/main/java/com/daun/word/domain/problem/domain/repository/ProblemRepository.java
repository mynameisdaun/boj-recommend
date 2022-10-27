package com.daun.word.domain.problem.domain.repository;

import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.Tag;
import com.daun.word.global.GlobalId;
import com.daun.word.global.infra.solvedac.dto.ProblemCount;
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
    int saveProblemTag(@Param("problemId") GlobalId<Problem, Integer> problemGlobalId, @Param("tagId") GlobalId<Tag, Integer> tagGlobalId);

    /* 아이디로 문제를 조회한다 */
    Optional<Problem> findById(@Param("problemId") GlobalId<Problem, Integer> globalId);

    /* 아이디들로 문제들을 조회한다 */
    List<Problem> findByIdIn(@Param("ids") List<GlobalId<Problem, Integer>> ids);

    /* 티어로 문제 조회 */
    List<Problem> findByTierBetweenOrderBySolvedCountDesc(@Param("goe") Tier goe, @Param("loe") Tier loe, @Param("offset") int offset, @Param("limit") int limit);

    /* 티어별 문제 수 조회*/
    List<ProblemCount> countByGroup();
}
