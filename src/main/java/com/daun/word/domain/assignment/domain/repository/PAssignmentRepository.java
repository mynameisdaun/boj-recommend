package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.PAssignment;
import com.daun.word.global.Id;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface PAssignmentRepository {

    /* 과제 저장 */
    Integer save(@Param("assignment") PAssignment assignment);

    /* 과제 세부 내역 업데이트*/
    Integer update(@Param("assignment") PAssignment assignment);

    /* 과제 id로 과제 조회 */
    Optional<PAssignment> findById(@Param("id") Id<PAssignment, Integer> id);
}
