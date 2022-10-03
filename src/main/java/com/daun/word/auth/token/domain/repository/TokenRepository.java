package com.daun.word.auth.token.domain.repository;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.auth.token.domain.Token;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface TokenRepository {
    /* 토큰 생성 */
    Integer save(@Param("token") Token token);

    /* 멤버 ID(구분값)으로 토큰 조회 */
    Optional<Token> findByEmail(@Param("email") Email email);
}