package com.daun.word.domain.member.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.repository.MemberRepository;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 회원을 등록한다
     *
     * @param request RegisterRequest
     * @return Member
     * @throws IllegalStateException 회원 요청이 null 일 경우, "잘못된 회원가입 요청입니다."
     * @throws IllegalStateException 같은 이메일로 가입한 회원이 존재할 경우, "이미 가입한 회원입니다."
     */
    @Transactional
    public Member register(RegisterRequest request) {
        checkArgument(request != null, "잘못된 회원가입 요청입니다");
        if (memberRepository.existsMemberByEmail(request.getEmail())) {
            throw new IllegalStateException("이미 가입한 회원입니다");
        }
        Member member = new Member(
                UUID.randomUUID(),
                request.getEmail(),
                request.getName(),
                passwordEncoder.encode(request.getPassword().getValue()),
                request.getTier(),
                request.getSocialType()
        );
        memberRepository.save(member);
        return member;
    }

    /**
     * Email로 회원을 조회한다
     *
     * @param email Email
     * @return Member
     * @throws IllegalStateException  이메일이 null 일 경우, "이메일은 필수 값 입니다"
     * @throws NoSuchElementException 해당 이메일로 가입한 회원이 존재하지 않을 경우, "존재하지 않는 회원 입니다"
     */
    @Transactional
    public Member findByEmail(final Email email) {
        checkArgument(email != null, "이메일은 필수 값 입니다");
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원 입니다"));
    }

    @Transactional
    public List<Member> findByEmailIn(final List<Email> emails) {
        return emails.stream()
                .map(this::findByEmail)
                .collect(Collectors.toList());
    }

}
