package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FakeAssignmentRepository implements AssignmentRepository {

    Map<UUID, Assignment> table = new HashMap<>();

    @Override
    public Assignment save(Assignment request) {
        table.put(request.getId(), request);
        return request;
    }

    @Override
    public Optional<Assignment> findById(UUID id) {
        return Optional.ofNullable(table.get(id));
    }

    @Override
    public Optional<Assignment> findByMemberAndProblem(Member member, Problem problem) {
        return table.values()
                .stream()
                .filter(a -> a.getMember().equals(member) && a.getProblem().equals(problem))
                .findFirst();
    }
}
