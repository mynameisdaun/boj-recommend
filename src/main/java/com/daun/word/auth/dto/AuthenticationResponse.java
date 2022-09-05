package com.daun.word.auth.dto;

import com.daun.word.commons.Id;
import com.daun.word.member.domain.Member;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.domain.vo.Nickname;
import com.daun.word.member.domain.vo.SocialType;
import com.daun.word.member.dto.MemberDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse {
    //TODO: Test Code!
    private final String accessToken;
    private final String refreshToken;
    private final Id<Member, Integer> id;
    private final Email email;
    private final Nickname nickname;
    private final SocialType socialType;

    public AuthenticationResponse(String accessToken, String refreshToken, MemberDTO memberDTO) {
        //TODO: 이건 서버 에러니까 에러 종류 바꿔서 응답해줘야 한다.
        checkArgument(isNotEmpty(accessToken), "액세스 토큰을 응답으로 전달해야 합니다");
        checkArgument(isNotEmpty(accessToken), "리프레쉬 토큰을 응답으로 전달해야 합니다");
        checkArgument(isNotEmpty(accessToken), "회원 정보를 응답으로 전달해야 합니다");
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = Id.of(Member.class, memberDTO.getId());
        this.email = memberDTO.getEmail();
        this.nickname = memberDTO.getNickname();
        this.socialType = memberDTO.getSocialType();
    }
}
