package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.global.GlobalId;
import com.daun.word.global.vo.YesNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AssignmentRepositoryTest {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @DisplayName(value = "과제 id로 회원을 조회한다")
    @Test
    void findById() throws Exception {
        //given
        GlobalId<Assignment, Integer> globalId = GlobalId.of(Assignment.class, assignment().getId());
        //when
        Assignment assignment = assignmentRepository.findById(globalId)
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(assignment).isNotNull();
    }

    @DisplayName(value = "과제를 저장한다")
    @Test
    void save() throws Exception {
        //given
        Assignment assignment = new Assignment(study(), recommend(), member_1(), LocalDateTime.now(), LocalDateTime.now(), YesNo.N, null);
        //when
        assignmentRepository.save(assignment);
        //then
        assertThat(assignment.getId()).isNotNull();
    }
}
