package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FakeMemberRepository implements MemberRepository {

    Map<UUID, Member> members = new HashMap<>();

    @Override
    public Member save(final Member member) {
        members.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(UUID id) {
        return Optional.ofNullable(members.get(id));
    }

    @Override
    public Optional<Member> findMemberByEmail(Email email) {
        return members.values()
                .stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }
}
