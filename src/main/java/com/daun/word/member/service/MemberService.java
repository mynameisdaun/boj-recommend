package com.daun.word.member.service;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.member.domain.repository.MemberRepository;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    /* 회원 가입 */
    public Member register(RegisterRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("회원 가입을 위해서 올바른 회원 가입 요청이 있어야 합니다.");
        }
        Member member = new Member(request.getEmail(), request.getPassword(), request.getNickname(), request.getSocialType());
        memberRepository.register(member);
        return member;
    }

    /* email, socialType으로 회원 조회 */
    public Member findMemberByEmailAndSocialType(Email email, SocialType socialType) {
        if(email == null || socialType == null) {
            throw new IllegalArgumentException("해당 조회를 위해서 이메일과 소셜 타입은 필수값 입니다.");
        }
        return memberRepository.findMemberByEmailAndSocialType(email, socialType).orElse(null);
    }
}
/*
* 회사 프로그램을 진단해본 결과,
해커 -> 회사 서버로 공격을 진행 하였지만
회사 -> 해커 서버로 정보등을 옮기는 과정이 차단되어 해커의 공격이 실패하였습니다.

이는 필수적인 사이트를 제외하고, 회사 -> 외부 IP의 아웃바운드를 차단해놓은 회사의 보안 정책 때문이었습니다.
평소 직원들은 웹 서핑, 혹은 파일다운로드를 자유롭게 할 수 없어 이 정책에 대한 불만이 많았으나, 안전한 보안 정책 때문에 회사의 자산을 지킬 수 있었습니다.
*
* */
