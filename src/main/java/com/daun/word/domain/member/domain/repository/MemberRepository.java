package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository {

    Member save(final Member member);

    Optional<Member> findById(final UUID id);

    Optional<Member> findMemberByEmail(final Email email);
}
