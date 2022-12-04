package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssignmentRepository {
    Assignment save(final Assignment request);

    Optional<Assignment> findById(UUID id);

    List<Assignment> findAllByDeleted(final boolean deleted);

    Optional<Assignment> findByMemberAndProblem(final Member member, final Problem problem);

    List<Assignment> findByMember(final Member member);

    @Query(" select a from assignment a join fetch a.problem where a.member in :members and a.problem in :problems ")
    List<Assignment> findAllByMembersAndProblemIn(@Param("members") final List<Member> members, @Param("problems") final List<Problem> problems);

}
