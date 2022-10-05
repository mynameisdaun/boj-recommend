package com.daun.word.domain.problem.domain.repository;

import com.daun.word.global.Id;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.domain.vo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    /* 문제 업데이트 */
    int update(@Param("problem") Problem problem);

    /* 아이디로 문제를 조회한다 */
    Optional<Problem> findById(@Param("problemId") Id<Problem, Integer> id);
}
