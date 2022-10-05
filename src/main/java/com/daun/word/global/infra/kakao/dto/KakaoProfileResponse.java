package com.daun.word.global.infra.kakao.dto;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Nickname;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KakaoProfileResponse {
    private String id;
    private String connected_at;
    private KakaoAccount kakao_account;
    private Properties properties;

    public Email getEmail() {
        return new Email(this.kakao_account.getEmail());
    }

    public Nickname getNickname() {
        return new Nickname(this.properties.getNickname());
    }

    public String getProfileImage() {
        return this.properties.getProfile_image();
    }

    public String getThumbnailImage() {
        return this.properties.getThumbnail_image();
    }

    public String getGender() {
        return this.kakao_account.getGender();
    }

    public String getBirthday() {
        return this.kakao_account.getBirthday();
    }

    public String getAgeRange() {
        return this.kakao_account.getAge_range();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Properties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KakaoAccount {
        private boolean profile_nickname_needs_agreement;
        private boolean profile_image_needs_agreement;
        private Properties profile;
        private boolean has_email;
        private boolean email_needs_agreement;
        private boolean is_email_valid;
        private boolean is_email_verified;
        private String email;
        private boolean has_age_range;
        private boolean age_range_needs_agreement;
        private String age_range;
        private boolean has_birthday;
        private boolean birthday_needs_agreement;
        private String birthday;
        private String birthday_type;
        private boolean has_gender = true;
        private boolean gender_needs_agreement = false;
        private String gender;
    }
}
