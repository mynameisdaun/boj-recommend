package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.GlobalId;
import com.daun.word.global.infra.solvedac.dto.ProblemCount;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;

import java.util.List;


public interface SolvedAcClient {

    ProblemSearchResponse search(String query, int page, String sort, String direction);

    SolvedAcMember findMemberByEmail(Email email);

    Problem findById(GlobalId<Problem, Integer> globalId);

    List<Problem> findByIdsIn(List<GlobalId<Problem, Integer>> ids);

    /* 문제 리스트 중에서, 모든 회원이 풀지 않은 문제만 반환하기 */
    List<Problem> unSolvedProblemsByMembers(List<Member> assignTo, List<Problem> Problem);

    /* 문제 리스트 중에서, 회원이 풀지 않은 문제만 반환하기 */
    List<Problem> unSolvedProblemsByMember(Member assignTo, List<Problem> Problem);

    /* 백준 통계 가져오기 */
    List<ProblemCount> problemCountGroupByLevel();
}
