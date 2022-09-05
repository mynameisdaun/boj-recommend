package com.daun.word.member.domain;

import com.daun.word.commons.Id;
import com.daun.word.config.security.Jwt;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.Password;
import com.daun.word.member.domain.vo.SocialType;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static com.daun.word.utils.StringUtils.isNullOrBlank;
import static java.time.LocalDateTime.now;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(exclude = {"id", "password", "createdAt", "updatedAt", "nickname"})
public class Member {
    private Integer id;
    private Email email;
    @ToString.Exclude
    private String password;
    private Nickname nickname;
    private SocialType socialType;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Member(Email email, String password, Nickname nickname, SocialType socialType) {
        if (email == null || isNullOrBlank(password) || nickname == null || socialType == null) {
            throw new IllegalArgumentException("이메일, 비밀번호, 닉네임, 소셜타입은 빈 값이 올 수 없습니다!");
        }
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.socialType = socialType;
    }

    /* api token 발급 */
    public String accessToken(Jwt jwt, String[] roles) {
        Jwt.Claims claims = Jwt.Claims.of(Id.of(Member.class, id), email, nickname, socialType, roles);
        return jwt.newToken(claims);
    }

    /* 로그인 */
    public void login(PasswordEncoder passwordEncoder, Password password) {
        if(!passwordEncoder.matches(password.getValue(), this.password)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    /* 로그인 성공 후 */
    public void afterLoginSuccess() {
        this.loginCount++;
        this.lastLoginAt = now();
    }


    /* FOR MY BATIS*/
    public void setId(Integer id) {
        this.id = id;
    }
    private void setEmail(String email) {
        this.email = new Email(email);
    }
    private void setNickname(String nickname) {
        this.nickname = new Nickname(nickname);
    }

}
