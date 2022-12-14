package com.daun.word.global.infra.solvedac;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.SolvedAcMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.exception.NoSuchMemberException;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.dto.search.SortDirection;
import com.daun.word.domain.problem.dto.search.SortType;
import com.daun.word.global.infra.solvedac.dto.ProblemSearchResponse;
import com.daun.word.global.infra.solvedac.dto.SolvedAcProblem;
import com.daun.word.global.infra.solvedac.dto.TierCounts;
import com.daun.word.global.vo.Tier;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeSolvedAcClient implements SolvedAcClient {

    private final FakeSolvedAcDB fakeSolvedAcDB;

    public FakeSolvedAcClient() {
        this.fakeSolvedAcDB = new FakeSolvedAcDB();
    }

    @Override
    public ProblemSearchResponse search(String query, int page, SortType sort, SortDirection direction) {
        return null;
    }

    @Override
    public SolvedAcMember findMemberByEmail(Email email) {
        return Optional.ofNullable(fakeSolvedAcDB.getMember(email.getEmail()))
                .orElseThrow(() -> new NoSuchMemberException("Solved AC에 등록되지 않은 회원입니다.\n서비스를 이용하기 위해 먼저 Solved AC에 가입해주세요.\nhttps://solved.ac"));
    }

    @Override
    public Optional<SolvedAcProblem> findById(Integer id) {
        return Optional.ofNullable(fakeSolvedAcDB.getProblem(id));
    }

    @Override
    public boolean isSolved(List<Member> members, Problem problem) {
        int count = 0;
        for (Member member : members) {
            if (fakeSolvedAcDB.isSolved(member.getEmail().getValue(), problem.getId())) count++;
        }
        return count == members.size();
    }

    @Override
    public TierCounts problemCountGroupByTier() {
        throw new NotImplementedException("");
    }

    @Override
    public List<SolvedAcProblem> findByIdsIn(List<Integer> ids) {
        throw new NotImplementedException("");
    }
}
