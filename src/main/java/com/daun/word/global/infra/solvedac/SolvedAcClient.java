package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;

import java.util.Optional;


public interface SolvedAcClient {

    ProblemSearchResponse search(final String query, final int page, final String sort, final String direction);

    Optional<SolvedAcMember> findMemberByEmail(final Email email);

    Optional<SolvedAcProblem> findById(final Integer id);

    boolean isSolved(final Member member, final Problem problem);
}
