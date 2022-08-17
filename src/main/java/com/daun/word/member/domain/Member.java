package com.daun.word.member.domain;

import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import lombok.*;

import java.time.LocalDateTime;

import static com.daun.word.utils.StringUtils.isNullOrBlank;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
public class Member {
    private Integer id;
    private Email email;
    @ToString.Exclude
    private String password;
    private Nickname nickname;
    private SocialType socialType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //TODO: email , password VO , nickName vo 생성
    public Member(Email email, String password, Nickname nickname, SocialType socialType) {
        if(email == null || isNullOrBlank(password) || nickname == null || socialType == null) {
            throw new IllegalArgumentException("이메일, 비밀번호, 닉네임, 소셜타입은 빈 값이 올 수 없습니다!");
        }
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.socialType = socialType;
    }

    public String getEmail() {
        return this.email.getValue();
    }

    protected void setEmail (String email) {
        this.email = new Email(email);
    }
    public String getNickname() {
        return this.nickname.getValue();
    }

    protected void setNickname (String nickname) {
        this.nickname = new Nickname(nickname);
    }
    public String getSocialType() {
        return socialType.name();
    }

    public void setSocialType(String socialType) {
        this.socialType = SocialType.valueOf(socialType);
    }
}
