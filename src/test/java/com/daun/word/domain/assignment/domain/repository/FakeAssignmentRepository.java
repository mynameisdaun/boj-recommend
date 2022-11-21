package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import org.apache.commons.lang3.NotImplementedException;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static com.daun.word.Fixture.Fixture.*;

public class FakeAssignmentRepository implements AssignmentRepository {

    private final Map<UUID, Assignment> table = new HashMap<>();

    public FakeAssignmentRepository() {
        save(assignment());
        save(new Assignment(UUID.randomUUID(), problem_19(), daun9870jung()));
        save(new Assignment(UUID.randomUUID(), problem_29(), daun9870jung()).complete());
    }

    @Override
    public Assignment save(final Assignment request) {
        table.put(request.getId(), request);
        return request;
    }

    @Override
    public Optional<Assignment> findById(final UUID id) {
        return Optional.ofNullable(table.get(id));
    }

    @Override
    public Optional<Assignment> findByMemberAndProblem(final Member member, final Problem problem) {
        return table.values().stream().filter(a -> a.getMember().equals(member) && a.getProblem().equals(problem)).findFirst();
    }

    @Override
    public List<Assignment> findAllByDeleted(final boolean deleted) {
        throw new NotImplementedException("아직 구현 전 입니다 ㅠㅠ");
    }

    @Override
    public List<Assignment> findAllByMembersAndProblemIn(final List<Member> members, final List<Problem> problems) {
        return table.values()
                .stream()
                .filter(a -> members.contains(a.getMember()) && problems.contains(a.getProblem()))
                .collect(Collectors.toList());
    }
}
