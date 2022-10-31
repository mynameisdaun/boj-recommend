package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssignmentRepository {
    Assignment save(final Assignment request);

    Optional<Assignment> findById(UUID id);

    Optional<Assignment> findByMemberAndProblem(final Member member, final Problem problem);
}
