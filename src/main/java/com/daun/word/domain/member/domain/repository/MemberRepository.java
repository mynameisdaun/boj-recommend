package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface MemberRepository {

    int save(@Param("member") Member member);

    Optional<Member> findByEmail(@Param("email") Email email);
}