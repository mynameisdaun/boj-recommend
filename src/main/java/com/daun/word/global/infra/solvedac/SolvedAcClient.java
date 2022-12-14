package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.dto.search.SortDirection;
import com.daun.word.domain.problem.dto.search.SortType;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.infra.solvedac.dto.TierCounts;

import java.util.List;
import java.util.Optional;


public interface SolvedAcClient {

    ProblemSearchResponse search(final String query, final int page, final SortType sort, final SortDirection direction);

    SolvedAcMember findMemberByEmail(final Email email);

    Optional<SolvedAcProblem> findById(final Integer id);

    List<SolvedAcProblem> findByIdsIn(final List<Integer> ids);

    boolean isSolved(final List<Member> member, final Problem problem);

    TierCounts problemCountGroupByTier();
}
