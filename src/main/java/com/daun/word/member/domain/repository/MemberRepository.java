package com.daun.word.member.domain.repository;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.SocialType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface MemberRepository {

    /* 회원 가입 */
    int register(@Param("member") Member request);

    /* Email, Social Type 으로 회원 조회하기 */
    Optional<Member> findByEmailAndSocialType(@Param("email") Email email, @Param("socialType") SocialType socialType);
}
