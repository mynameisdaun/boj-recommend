package com.daun.word.global.auth.dto;

import com.daun.word.domain.member.domain.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse {
    //TODO: Test Code!
    private final UUID id;
    private final String email;
    private final String nickname;
    private final String socialType;
    private final String accessToken;
    private final String refreshToken;



    public AuthenticationResponse(String accessToken, String refreshToken, Member member) {
        //TODO: 이건 서버 에러니까 에러 종류 바꿔서 응답해줘야 한다.
        checkArgument(isNotEmpty(accessToken), "액세스 토큰을 응답으로 전달해야 합니다");
        checkArgument(isNotEmpty(accessToken), "리프레쉬 토큰을 응답으로 전달해야 합니다");
        checkArgument(isNotEmpty(accessToken), "회원 정보를 응답으로 전달해야 합니다");
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = member.getId();
        this.email = member.getEmail().getValue();
        this.nickname = member.getName().getValue();
        this.socialType = member.getSocialType().name();
    }
}
