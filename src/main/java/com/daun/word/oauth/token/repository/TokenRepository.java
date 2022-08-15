package com.daun.word.oauth.token.repository;

import com.daun.word.oauth.token.dto.TokenDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository {
    /* 토큰 생성 */
    Integer createToken(TokenDTO token);

    /* 멤버 ID(구분값)으로 토큰 조회 */
    TokenDTO getTokenByMemberId(@Param("memberId") Integer memberId);
}
