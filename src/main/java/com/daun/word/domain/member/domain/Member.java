package com.daun.word.domain.member.domain;

import com.daun.word.config.security.Jwt;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Password;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.BaseEntity;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "member")
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(
                name = "email_unique",
                columnNames = {"email"}
        )
})
@ToString
@Getter
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id", nullable = false, columnDefinition = "varbinary(16)")
    private UUID id;

    @Embedded
    private Email email;

    @Embedded
    private Name name;

    @Column(name = "password")
    private String password;

    @Embedded
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    @Column(name = "login_count", nullable = false, columnDefinition = "int default 0")
    private int loginCount;

    @Column(name = "last_login_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginAt;

    public Member(UUID id, Email email, Name name, String password, Tier tier, SocialType socialType) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.tier = tier;
        this.socialType = socialType;
        this.loginCount = 0;
        this.lastLoginAt = null;
    }

    protected Member() {
        super();
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
