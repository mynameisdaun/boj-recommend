package com.daun.word.member.service;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.SocialType;
import com.daun.word.member.domain.repository.MemberRepository;
import com.daun.word.member.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    /* 회원 가입 */
    public Member register(RegisterRequest request) {
        Member member = new Member(request.getEmail(), request.getPassword(), request.getNickname(), request.getSocialType());
        memberRepository.register(member);
        return member;
    }

    /* email, socialType으로 회원 조회 */
    public Member findMemberByEmailAndSocialType(String email, SocialType socialType) {
        return memberRepository.findMemberByEmailAndSocialType(email, socialType);
    }
}
