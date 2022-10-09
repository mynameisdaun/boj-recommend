package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.Id;
import com.daun.word.global.vo.Tier;

import java.util.List;


public interface SolvedAcClient {

    Tier findMemberTier(Member member);

    Problem findById(Id<Problem, Integer> id);

    List<Problem> findByIdsIn(List<Id<Problem, Integer>> ids);

    /* 문제 리스트 중에서, 회원이 풀지 않은 문제만 반환하기 */
    List<Problem> unSolvedProblemsByMember(Member assignTo, List<Problem> Problem);

    List<Problem> manualProblemUpdate();
}
