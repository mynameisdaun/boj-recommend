package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;

import java.util.Optional;


public interface SolvedAcClient {

    ProblemSearchResponse search(String query, int page, String sort, String direction);

    Optional<SolvedAcMember> findMemberByEmail(Email email);

    Optional<SolvedAcProblem> findById(Integer id);
}
