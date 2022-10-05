package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.AssignmentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AssignmentRepository {

    /* 과제 저장 */
    Integer saved(@Param("assignment") Assignment assignment);

    /* 과제 세부 내역 업데이트*/
    Integer update(@Param("assignment") Assignment assignment);

    /* 과제 세부 내역 저장*/
    Integer saveDetail(@Param("detail") AssignmentDetail assignmentDetail);

    /* 과제 세부 내역 업데이트*/
    Integer updateDetail(@Param("detail") AssignmentDetail assignmentDetail);

    /* 과제 id로 과제 조회 */
    Optional<Assignment> findAssignmentById(@Param("id") Integer id);

    /* 과제 id로 과제 세부 내역 조회 */
    List<AssignmentDetail> findDetailsById(@Param("id") Integer id);

    /* 과제 상세 id로 과제 세부 내역 조회 */
    Optional<AssignmentDetail> findDetailByDetailId(@Param("id") Integer id);

}
