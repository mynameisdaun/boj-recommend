package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class FakeSolvedAcDB {

    private final Map<String, SolvedAcMember> memberTable = new HashMap<>();

    private final Map<Integer, SolvedAcProblem> problemTable = new HashMap<>();

    private final Map<String, Set<Integer>> memberSolvedProblem = new HashMap<>();

    public void addMember(final SolvedAcMember... members) {
        for (final SolvedAcMember m : members) {
            memberTable.put(m.getHandle(), m);
        }
    }

    public void addProblem(final SolvedAcProblem... problems) {
        for (final SolvedAcProblem p : problems) {
            problemTable.put(p.getProblemId(), p);
        }
    }

    public void memberSolve(String handle, Integer problemId) {
        if (memberTable.get(handle) == null) {
            throw new IllegalStateException("존재 하지 않는 회원입니다.");
        }
        Set<Integer> solved = memberSolvedProblem.getOrDefault(handle, new HashSet<>());
        solved.add(problemId);
        memberSolvedProblem.put(handle, solved);
    }

    public SolvedAcMember getMember(final String handle) {
        return memberTable.get(handle);
    }

    public SolvedAcProblem getProblem(final Integer id) {
        return problemTable.get(id);
    }
}
