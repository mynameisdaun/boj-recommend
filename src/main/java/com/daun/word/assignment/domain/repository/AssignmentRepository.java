package com.daun.word.assignment.domain.repository;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AssignmentRepository {

    /* 과제 저장 */
    int save(@Param("assignment") Assignment assignment);

    int saveDetail(@Param("detail") AssignmentDetail assignmentDetail);

}
