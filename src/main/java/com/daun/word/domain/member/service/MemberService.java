package com.daun.word.domain.member.service;

import com.daun.word.global.auth.dto.AuthenticationRequest;
import com.daun.word.global.auth.dto.AuthenticationResponse;
import com.daun.word.global.auth.token.dto.TokenDTO;
import com.daun.word.global.auth.token.service.TokenService;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.repository.MemberRepository;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.dto.MemberDTO;
import com.daun.word.domain.member.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {

    private final TokenService tokenService;

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

    /* 로그인 */
    @Transactional
    public AuthenticationResponse login(AuthenticationRequest request) {
        checkArgument(request != null, "로그인 요청은 필수 값 입니다.");
        Member member = findByEmail(request.getEmail());
        member.login(passwordEncoder, request.getPassword());
        member.afterLoginSuccess();
        TokenDTO token = tokenService.saveTokenFor(member);
        memberRepository.save(member);

        return new AuthenticationResponse(
                member.getId(),
                member.getEmail().getValue(),
                member.getNickname().getValue(),
                member.getSocialType().name(),
                token.getAccessToken(),
                token.getRefreshToken()
        );
    }

    /* 이메일로 회원 조회*/
    @Transactional(readOnly = true)
    public Member findByEmail(Email email) {
        checkArgument(email != null, "이메일로 회원 조회를 하기 위해서, 이메일은 필수 값 입니다.");

        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일 입니다."));
    }
}
