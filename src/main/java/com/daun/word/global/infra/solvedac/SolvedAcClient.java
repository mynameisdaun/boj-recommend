package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.Id;
import com.daun.word.global.vo.Tier;

import java.util.List;


public interface SolvedAcClient {

    Tier findMemberTier(Member member);

    Problem findById(Id<Problem, Integer> id);

    boolean checkAssignment(Member member, Id<Problem, Integer> id);

    List<Problem> checkProblemsSolved(Member member, List<Id<Problem, Integer>> ids);
}
