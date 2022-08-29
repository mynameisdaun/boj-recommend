package com.daun.word.member.domain;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.SocialType;
import lombok.*;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

import static com.daun.word.utils.StringUtils.isNullOrBlank;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(exclude = {"id", "password", "createdAt", "updatedAt", "nickname"})
public class Member implements UserDetails {
    private Integer id;
    private Email email;
    @ToString.Exclude
    private String password;
    private Nickname nickname;
    private SocialType socialType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //TODO: member Role 설정하기?

    public Member(Email email, String password, Nickname nickname, SocialType socialType) {
        if (email == null || isNullOrBlank(password) || nickname == null || socialType == null) {
            throw new IllegalArgumentException("이메일, 비밀번호, 닉네임, 소셜타입은 빈 값이 올 수 없습니다!");
        }
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.socialType = socialType;
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public void setNickname(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new NotImplementedException("");
    }

    @Override
    public String getUsername() {
        return this.email.getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
