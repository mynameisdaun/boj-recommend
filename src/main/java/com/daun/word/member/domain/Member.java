package com.daun.word.member.domain;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static com.daun.word.utils.StringUtils.isNullOrBlank;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
public class Member {
    @ToString.Exclude
    private final Logger logger = LoggerFactory.getLogger(Member.class);
    private Integer id;
    private String email;
    @ToString.Exclude
    private String password;
    private String nickname;
    private SocialType socialType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //TODO: email , password VO , nickName vo 생성
    public Member(String email, String password, String nickname, SocialType socialType) {
        if(isNullOrBlank(email) || isNullOrBlank(password) || isNullOrBlank(nickname) || socialType == null) {
            throw new IllegalArgumentException("이메일, 비밀번호, 닉네임, 소셜타입은 빈 값이 올 수 없습니다!");
        }
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.socialType = socialType;
    }

    public String getSocialType() {
        return socialType.name();
    }

    public void setSocialType(String socialType) {
        this.socialType = SocialType.valueOf(socialType);
    }
}
