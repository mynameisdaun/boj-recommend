package com.daun.word.domain.member.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.global.vo.Name;
import com.daun.word.domain.member.domain.vo.Password;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.Tier;
import com.google.common.base.Preconditions;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class RegisterRequest {
    @NotNull(message = "이메일은 회원가입 하기 위한 필수 조건입니다.")
    private Email email;
    @NotNull(message = "비밀번호는 회원가입 하기 위한 필수 조건입니다.")
    private Password password;
    @NotNull(message = "닉네임은 회원가입 하기 위한 필수 조건입니다.")
    private Name name;
    @NotNull(message = "소셜 타입은 회원가입 하기 위한 필수 조건입니다.")
    private SocialType socialType;
    @NotNull(message = "티어는 회원가입 하기 위한 필수 조건입니다.")
    private Tier tier;
    //TODO: Validation test 하는 방법 있었는데 격이 안난다, 어질어질하다 수정 반드시 진행하자
    public RegisterRequest(String email, String password, String nickname, String socialType, Tier tier) {
        Preconditions.checkArgument(email != null, "이메일은 회원가입 하기 위한 필수 조건입니다.");
        Preconditions.checkArgument(password != null, "비밀번호는 회원가입 하기 위한 필수 조건입니다.");
        Preconditions.checkArgument(nickname != null, "닉네임은 회원가입 하기 위한 필수 조건입니다.");
        Preconditions.checkArgument(socialType != null, "소셜타입은 회원가입 하기 위한 필수 조건입니다.");
        Preconditions.checkArgument(tier != null, "티어는 회원가입 하기 위한 필수 조건입니다.");
        this.email = new Email(email);
        this.password = new Password(password);
        this.name = new Name(nickname);
        this.socialType = SocialType.valueOf(socialType);
        this.tier = tier;
    }
}
