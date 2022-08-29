package com.daun.word.member.service;

import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.repository.MemberRepository;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.member.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    /* security 에서 사용하는 username은 사실 email 을 의미한다. */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(new Email(username))
                .orElseThrow(NoSuchElementException::new);
    }

    /* 회원 가입 */
    public Member register(RegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("회원 가입을 위해서 올바른 회원 가입 요청이 있어야 합니다.");
        }
        Member member = new Member(request.getEmail(), request.getPassword(), request.getNickname(), request.getSocialType());
        memberRepository.register(member);
        return member;
    }

    /* email, socialType으로 회원 조회 */
    /*  TODO: email이 unique 인데, 굳이 SocialType을 넣어야 하는 이유가 있을까?
              같은 email, 다른 소셜로 로그인 요청할 경ㅈ우 어떻게 처리할것인지 테스트 케이스 작성해야 한다.
    *    */
    public Member findMemberByEmailAndSocialType(Email email, SocialType socialType) {
        if (email == null || socialType == null) {
            throw new IllegalArgumentException("해당 조회를 위해서 이메일과 소셜 타입은 필수값 입니다.");
        }
        return memberRepository.findByEmailAndSocialType(email, socialType).orElse(null);
    }

}
