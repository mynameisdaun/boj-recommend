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

    /* 회원 가입 */
    int save (@Param("member") Member member);

    /* 회원 정보 업데이트 */
    int update(@Param("member") Member member);

    /* Email, Social Type 으로 회원 조회하기 */
    Optional<Member> findByEmail(@Param("email") Email email);
}
