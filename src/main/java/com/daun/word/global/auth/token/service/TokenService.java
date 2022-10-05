package com.daun.word.global.auth.token.service;

import com.daun.word.global.auth.token.domain.Token;
import com.daun.word.global.auth.token.domain.TokenFactory;
import com.daun.word.global.auth.token.domain.repository.TokenRepository;
import com.daun.word.global.auth.token.dto.TokenDTO;
import com.daun.word.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TokenService {

    private final TokenFactory tokenFactory;

    private final TokenRepository tokenRepository;

    public TokenDTO saveTokenFor(Member member) {
        Token token = tokenFactory.token(member);
        tokenRepository.save(token);
        return new TokenDTO(token);
    }
}
