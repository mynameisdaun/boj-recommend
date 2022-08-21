package com.daun.word.oauth.token.domain.repository;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.oauth.token.domain.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import static com.daun.word.Fixture.Fixture.token;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
class TokenRepositoryTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private TokenRepository tokenRepository;

    @DisplayName(value = "이메일로 토큰 조회하기")
    @Test
    void findByEmail() throws Exception {
        //given&&when
        Token token = tokenRepository.findByEmail(token().getMemberEmail())
                .orElseThrow(NoSuchElementException::new);
        //when
        assertThat(token).isNotNull();
        assertAll(
                () -> assertThat(token).isEqualTo(token()),
                () -> assertThat(token.getAccessToken()).isEqualTo(token().getAccessToken()),
                () -> assertThat(token.getAccessTokenExpiredDate()).isEqualTo(token.getAccessTokenExpiredDate()),
                () -> assertThat(token.getRefreshToken()).isEqualTo(token().getRefreshToken()),
                () -> assertThat(token.getRefreshTokenExpiredDate()).isEqualTo(token().getRefreshTokenExpiredDate()),
                () -> assertThat(token.getMemberSocialType()).isEqualTo(token().getMemberSocialType()),
                () -> assertThat(token.getSocialAccessToken()).isEqualTo(token().getSocialAccessToken()),
                () -> assertThat(token.getSocialAccessTokenExpiredDate()).isEqualTo(token().getSocialAccessTokenExpiredDate()),
                () -> assertThat(token.getSocialRefreshToken()).isEqualTo(token().getSocialRefreshToken()),
                () -> assertThat(token.getSocialRefreshTokenExpiredDate()).isEqualTo(token().getSocialRefreshTokenExpiredDate()),
                () -> assertThat(token.getCreatedAt()).isNotNull(),
                () -> assertThat(token.getUpdatedAt()).isNotNull()
        );
    }

    @DisplayName(value = "토큰을 발급받지 못한 이메일로 토큰을 조회할 수 없다")
    @Test
    void findByEmail_fail() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            tokenRepository.findByEmail(new Email("no-exist@weword.com"))
                    .orElseThrow(NoSuchElementException::new);
        }).isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName(value = "토큰을 발급할 수 있다")
    @Test
    void save() throws Exception {
        //given
        Token token = new Token(
                new Email("another-tester@weword.com"),
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjA5Nzc4MzEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.Bs9nDgglcyg_IQCcsLQVH48RW1t1-w8QYqkLJissNuU",
                LocalDateTime.parse("2022-08-20 15:43:51", formatter),
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjEwMzY1MTEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.uCNJxT-PuD2FNLZcplTULRqu1XO2YEWX_0--35quTGU",
                LocalDateTime.parse("2022-08-21 08:01:51", formatter),
                SocialType.valueOf("W"),
                "7MP8EHWXFLzHxQsi1YNMXs3KVb1paQBpEPLwZb6QCj1zFwAAAYK54uKs",
                LocalDateTime.parse("2022-08-20 15:13:51", formatter),
                "oOdpDpTD3juuQy7ZVuEBnYkDH3cWJmM_lUie3eUcCj1zFwAAAYK54uKq",
                LocalDateTime.parse("2022-10-19 15:13:50", formatter)
        );
        //when
        int success = tokenRepository.save(token);
        //then
        assertThat(success).isEqualTo(1);
    }

    @DisplayName(value = "토큰 재 발급 시 토큰 정보가 업데이트 된다")
    @Test
    void save_merge() throws Exception {
        //given
        LocalDateTime updatedAccessExpiredDate = LocalDateTime.parse("2022-08-25 15:43:51", formatter);
        LocalDateTime updatedRefreshExpiredDate = LocalDateTime.parse("2022-08-25 15:43:51", formatter);
        LocalDateTime updatedSocialAccessExpiredDate = LocalDateTime.parse("2022-08-25 15:43:51", formatter);
        LocalDateTime updatedSocialRefreshExpiredDate = LocalDateTime.parse("2022-08-25 15:43:51", formatter);
        Token token = new Token(
                token().getMemberEmail(),
                "new-access",
                updatedAccessExpiredDate,
                "new-refresh",
                updatedRefreshExpiredDate,
                SocialType.valueOf("W"),
                "new-social-access",
                updatedSocialAccessExpiredDate,
                "new-social-refresh",
                updatedSocialRefreshExpiredDate
        );
        //when
        int success = tokenRepository.save(token);
        //then
        //TODO: 왜 2지?
        assertThat(success).isEqualTo(2);
    }

    @DisplayName(value = "존재하지 않는 회원은 토큰을 발급받을 수 없다")
    @Test
    void save_fail_no_exists_membrer() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> {
            tokenRepository.save(new Token(
                    new Email("no-exist@weword.com"),
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjA5Nzc4MzEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.Bs9nDgglcyg_IQCcsLQVH48RW1t1-w8QYqkLJissNuU",
                    LocalDateTime.parse("2022-08-20 15:43:51", formatter),
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjEwMzY1MTEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.uCNJxT-PuD2FNLZcplTULRqu1XO2YEWX_0--35quTGU",
                    LocalDateTime.parse("2022-08-21 08:01:51", formatter),
                    SocialType.valueOf("W"),
                    "7MP8EHWXFLzHxQsi1YNMXs3KVb1paQBpEPLwZb6QCj1zFwAAAYK54uKs",
                    LocalDateTime.parse("2022-08-20 15:13:51", formatter),
                    "oOdpDpTD3juuQy7ZVuEBnYkDH3cWJmM_lUie3eUcCj1zFwAAAYK54uKq",
                    LocalDateTime.parse("2022-10-19 15:13:50", formatter)
            ));
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}
