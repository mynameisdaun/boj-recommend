package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.Id;
import com.daun.word.global.infra.solvedac.dto.ProblemCount;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.vo.Tier;

import java.util.List;


public interface SolvedAcClient {

    ProblemSearchResponse search(String query, int page, String sort, String direction);

    Tier findMemberTier(Member member);

    Problem findById(Id<Problem, Integer> id);

    List<Problem> findByIdsIn(List<Id<Problem, Integer>> ids);

    /* 문제 리스트 중에서, 회원이 풀지 않은 문제만 반환하기 */
    List<Problem> unSolvedProblemsByMember(Member assignTo, List<Problem> Problem);

    /* 백준 통계 가져오기 */
    List<ProblemCount> problemCountGroupByLevel();
}
