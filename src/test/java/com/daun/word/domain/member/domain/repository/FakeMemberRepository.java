package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import org.springframework.dao.DuplicateKeyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.daun.word.Fixture.Fixture.another_member;
import static com.daun.word.Fixture.Fixture.member;

public class FakeMemberRepository implements MemberRepository {

    private final Map<Integer, Member> memberTable;

    public FakeMemberRepository() {
        memberTable = new HashMap<>();
        memberTable.put(1, member());
        memberTable.put(2, another_member());
    }

    @Override
    public int save(Member request) {
        if(memberTable.entrySet().stream().map(v->v.getValue().getEmail()).filter(email->email.equals(request.getEmail())).count()>0) {
            throw new DuplicateKeyException("");
        };
        memberTable.put(memberTable.size()+1, request);
        request.setId(memberTable.size());
        return 0;
    }

    @Override
    public Optional<Member> findByEmail(Email email) {
        return memberTable.entrySet()
                .stream()
                .filter(w -> w.getValue().getEmail().equals(email))
                .map(w->w.getValue())
                .findFirst();

    }

    @Override
    public int update(Member member) {
        memberTable.put(member.getId(), member);
        return 1;
    }
}
