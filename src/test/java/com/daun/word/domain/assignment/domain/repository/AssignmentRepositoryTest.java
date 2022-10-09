package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.global.Id;
import com.daun.word.domain.member.domain.vo.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AssignmentRepositoryTest {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @DisplayName(value = "과제 id로 회원을 조회한다")
    @Test
    void findById() throws Exception {
        //given
        Id<Assignment, Integer> id = Id.of(Assignment.class, assignment().getId());
        //when
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(assignment).isNotNull();
        assertAll(
                () -> assertThat(assignment.getId()).isEqualTo(Integer.valueOf(1)),
                () -> assertThat(assignment.getProblem()).isNotNull(),
                () -> assertThat(assignment.getProblem().getTags()).isNotNull(),
                () -> assertThat(assignment.getProblem().getTags().size()).isOne(),
                () -> assertThat(assignment.getAssignFrom()).isEqualTo(new Email("tester1@weword.com")),
                () -> assertThat(assignment.getAssignTo()).isEqualTo(new Email("daun9870jung"))
        );
    }

    @DisplayName(value = "과제를 저장한다")
    @Test
    void save() throws Exception {
        //given
        Assignment assignment = new Assignment(problem(), member().getEmail(), another_member().getEmail(), LocalDateTime.now(), LocalDateTime.now());
        //when
        assignmentRepository.save(assignment);
        //then
        assertThat(assignment.getId()).isNotNull();
    }
}
