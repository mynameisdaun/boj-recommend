package com.daun.word.assignment.domain.repository;

import com.daun.word.assignment.domain.PAssignment;
import com.daun.word.global.Id;
import com.daun.word.member.domain.vo.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PAssignmentRepositoryTest {

    @Autowired
    private PAssignmentRepository assignmentRepository;

    @DisplayName(value = "과제 id로 회원을 조회한다")
    @Test
    void findById() throws Exception {
        //given
        Id<PAssignment, Integer> id = Id.of(PAssignment.class, p_assignment().getId());
        //when
        Optional<PAssignment> assignment = assignmentRepository.findById(id);
        //then
        assertThat(assignment).isNotNull();
        assertAll(
                () -> assertThat(assignment.get().getId()).isEqualTo(Integer.valueOf(1)),
                () -> assertThat(assignment.get().getProblem()).isNotNull(),
                () -> assertThat(assignment.get().getProblem().getTags()).isNotNull(),
                () -> assertThat(assignment.get().getProblem().getTags().size()).isOne(),
                () -> assertThat(assignment.get().getAssignFrom()).isEqualTo(new Email("tester1@weword.com")),
                () -> assertThat(assignment.get().getAssignTo()).isEqualTo(new Email("tester2@weword.com"))
        );
    }

    @DisplayName(value = "과제를 저장한다")
    @Test
    void save() throws Exception {
        //given
        PAssignment assignment = new PAssignment(problem(), member().getEmail(), another_member().getEmail(), LocalDateTime.now(), LocalDateTime.now());
        //when
        assignmentRepository.save(assignment);
        //then
        assertThat(assignment.getId()).isNotNull();
    }
}
