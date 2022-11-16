package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository {

    Member save(@Param("member") final Member member);

    Optional<Member> findById(@Param("id") final UUID id);

    Optional<Member> findMemberByEmail(@Param("email") Email email);

    boolean existsMemberByEmail(@Param("email") final Email email);
}
