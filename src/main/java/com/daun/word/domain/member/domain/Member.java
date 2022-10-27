package com.daun.word.domain.member.domain;

import com.daun.word.config.security.Jwt;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Password;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.CreatedAt;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import com.daun.word.global.vo.UpdatedAt;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "member")
@Table(name="member", uniqueConstraints = {
        @UniqueConstraint(
                name = "email_unique",
                columnNames = {"email"}
        )
})
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString @Getter
public class Member {

    @Id
    @Column(name = "member_id", nullable = false, columnDefinition = "varbinary(16)")
    private UUID id;

    private Email email;

    private Name name;


    @Column(name = "password")
    private String password;

    private Tier tier;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    @Column(name = "login_count", nullable = false, columnDefinition = "int default 0")
    private int loginCount;

    @Column(name = "last_login_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginAt;

    private CreatedAt createdAt;

    private UpdatedAt updatedAt;

    public Member(Email email, String password, Name name, Tier tier, SocialType socialType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.tier = tier;
        this.socialType = socialType;
    }

    /* api token 발급 */
    public String accessToken(Jwt jwt, String[] roles) {
        Jwt.Claims claims = Jwt.Claims.of(UUID.randomUUID(), email, name, socialType, roles);
        return jwt.newToken(claims);
    }

    /* 로그인 */
    public void login(PasswordEncoder passwordEncoder, Password password) {
        if (!passwordEncoder.matches(password.getValue(), this.password)) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    /* 로그인 성공 후 */
    public void afterLoginSuccess() {
        this.loginCount++;
        this.lastLoginAt = new Date();
    }

}
