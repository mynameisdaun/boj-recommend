package com.daun.word.member.domain.repository;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.SocialType;
import com.daun.word.member.dto.RegisterRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface MemberRepository {

    /* 회원 가입 */
    int register(Member request);

    /* Email, Social Type 으로 회원 조회하기 */
    Member findMemberByEmailAndSocialType(@Param("email") String email, @Param("socialType") SocialType socialType);
}
