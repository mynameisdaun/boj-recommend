package com.daun.word.member.service;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.repository.MemberRepository;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /* 회원 가입 */
    public Member register(RegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("회원 가입을 위해서 올바른 회원 가입 요청이 있어야 합니다.");
        }
        Member member = new Member(request.getEmail(), request.getPassword(), request.getNickname(), request.getSocialType());
        memberRepository.save(member);
        return member;
    }

    public Member findByEmail(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("해당 조회를 위해서 이메일과 소셜 타입은 필수값 입니다.");
        }
        return memberRepository.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

}
