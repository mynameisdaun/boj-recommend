package com.daun.word.domain.assignment.domain.repository;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

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

    @Override
    public List<Assignment> findAllByDeleted(boolean deleted) {
        throw new NotImplementedException("아직 구현 전 입니다 ㅠㅠ");
    }

    @Override
    public List<Assignment> findAllByMembersAndProblemIn(List<Member> member, List<Problem> problems) {
        throw new NotImplementedException("아직 구현 전 입니다 ㅠㅠ");
    }
}
