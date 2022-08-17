package com.daun.word.oauth.token.service;

import com.daun.word.infra.kakao.dto.SocialTokenResponse;
import com.daun.word.member.domain.Member;
import com.daun.word.oauth.token.domain.Token;
import com.daun.word.oauth.token.domain.TokenFactory;
import com.daun.word.oauth.token.domain.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final TokenFactory tokenFactory;

    public Token createToken(Member member, SocialTokenResponse socialTokenResponse) {
        Token token = tokenFactory.generateToken(member, socialTokenResponse);
        tokenRepository.save(token);
        return token;
    }
}
