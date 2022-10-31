package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;

import java.util.Optional;

public class FakeSolvedAcClient implements SolvedAcClient {

    private final FakeSolvedAcDB fakeSolvedAcDB;

    public FakeSolvedAcClient(FakeSolvedAcDB solvedAcDB) {
        this.fakeSolvedAcDB = solvedAcDB;
    }

    @Override
    public ProblemSearchResponse search(String query, int page, String sort, String direction) {
        return null;
    }

    @Override
    public Optional<SolvedAcMember> findMemberByEmail(Email email) {
        return Optional.ofNullable(fakeSolvedAcDB.getMember(email.getEmail()));
    }

    @Override
    public Optional<SolvedAcProblem> findById(Integer id) {
        return Optional.ofNullable(fakeSolvedAcDB.getProblem(id));
    }

}
