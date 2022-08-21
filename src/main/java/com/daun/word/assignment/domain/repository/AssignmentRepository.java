package com.daun.word.assignment.domain.repository;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AssignmentRepository {

    /* 과제 저장 */
    int save(Assignment assignment);

    int saveDetail(AssignmentDetail assignmentDetail);

}
