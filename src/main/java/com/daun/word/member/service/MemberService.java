package com.daun.word.member.service;

import com.daun.word.auth.dto.AuthenticationRequest;
import com.daun.word.auth.dto.LoginResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.repository.MemberRepository;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.dto.MemberDTO;
import com.daun.word.member.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /* 회원 가입 */
    @Transactional
    public MemberDTO register(RegisterRequest request) {
        checkArgument(request != null, "회원 가입을 위해서 올바른 회원 가입 요청이 있어야 합니다.");
        Member member = new Member(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword().getValue()),
                request.getNickname(),
                request.getSocialType()
        );
        memberRepository.save(member);
        return new MemberDTO(member);
    }

    @Transactional
    public LoginResponse login(AuthenticationRequest request) {
        checkArgument(request != null, "로그인 요청은 필수 값 입니다.");
        Member member = findByEmail(request.getEmail());
        member.login(passwordEncoder, request.getPassword());
        member.afterLoginSuccess();
        throw new NotImplementedException("");
    }

    @Transactional(readOnly = true)
    public Member findByEmail(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("해당 조회를 위해서 이메일과 소셜 타입은 필수값 입니다.");
        }
        return memberRepository.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

}
