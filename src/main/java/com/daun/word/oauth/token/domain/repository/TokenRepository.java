package com.daun.word.oauth.token.domain.repository;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.oauth.token.domain.Token;
import com.daun.word.oauth.token.dto.TokenDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository {
    /* 토큰 생성 */
    Integer save(Token token);

    /* 멤버 ID(구분값)으로 토큰 조회 */
    TokenDTO getTokenByEmail(@Param("email") Email email);
}
