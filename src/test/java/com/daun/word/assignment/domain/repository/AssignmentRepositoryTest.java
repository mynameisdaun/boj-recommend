package com.daun.word.assignment.domain.repository;

import com.daun.word.assignment.domain.Assignment;
import com.daun.word.assignment.domain.AssignmentDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@MybatisTest
class AssignmentRepositoryTest {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @DisplayName(value = "과제를 등록한다")
    @Test
    void save() throws Exception {
        //given
        Assignment assignment = new Assignment(workbook().getId(), member().getEmail(), another_member().getEmail());
        //when
        int saved = assignmentRepository.save(assignment);
        //then
        assertThat(saved).isEqualTo(1);
    }

    @DisplayName(value = "과제 세부정보를 등록한다")
    @Test
    void save_assignment_detail() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.now();
        AssignmentDetail detail = new AssignmentDetail(assignment().getId(),chapter().getId(),now, now.plusDays(1),"temp-quiz");
        //when
        int saved = assignmentRepository.saveDetail(detail);
        //then
        assertThat(saved).isEqualTo(1);
    }


}
