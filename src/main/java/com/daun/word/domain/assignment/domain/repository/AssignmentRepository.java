package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.global.GlobalId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface AssignmentRepository {

    /* 과제 저장 */
    Integer save(@Param("assignment") Assignment assignment);

    /* 과제 세부 내역 업데이트*/
    Integer update(@Param("assignment") Assignment assignment);

    /* 과제 id로 과제 조회 */
    Optional<Assignment> findById(@Param("id") GlobalId<Assignment, Integer> globalId);
}
