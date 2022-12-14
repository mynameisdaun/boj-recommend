package com.daun.word.domain.member.domain;

import com.daun.word.auth.exception.NotAuthenticatedException;
import com.daun.word.config.security.Jwt;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Password;
import com.daun.word.domain.member.domain.vo.Role;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.domain.member.exception.DuplicateMemberException;
import com.daun.word.domain.member.exception.IllegalRegisterProcessException;
import com.daun.word.global.vo.BaseEntity;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "member")
@ToString
@Getter
@EqualsAndHashCode(of = "id")
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

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(UUID id, Email email, Name name, String password, Tier tier, SocialType socialType, Role role) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.tier = tier;
        this.socialType = socialType;
        this.loginCount = 0;
        this.lastLoginAt = null;
        this.role = role;
    }

    public Member(SolvedAcMember solvedAcMember, RegisterRequest request, PasswordEncoder encoder) {
        this(UUID.randomUUID(),
                request.getEmail(),
                request.getName(),
                encoder.encode(request.getPassword().getValue()),
                new Tier(solvedAcMember.getTier()),
                SocialType.W,
                Role.ROLE_GUEST);
    }

    protected Member() {
        super();
    }

    /* api token ?????? */
    public String accessToken(Jwt jwt) {
        Jwt.Claims claims = Jwt.Claims.of(UUID.randomUUID(), email, role);
        return jwt.newToken(claims);
    }

    /* ????????? */
    public void login(PasswordEncoder passwordEncoder, Password password) {
        if (!passwordEncoder.matches(password.getValue(), this.password)) {
            throw new NotAuthenticatedException("??????????????? ????????????.");
        }
        this.lastLoginAt = new Date();
        this.loginCount++;
    }

    public Member tempRegister() {
        if (this.isUser()) {
            throw new DuplicateMemberException("?????? ????????? ???????????????.");
        }
        if (this.isTempMember()) {
            throw new IllegalRegisterProcessException("?????? ??????????????? ??????????????????.");
        }
        this.role = Role.ROLE_TEMP;
        return this;
    }

    public boolean isTempMember() {
        return this.role == Role.ROLE_TEMP;
    }

    public Member authenticate() {
        if (this.role != Role.ROLE_TEMP) {
            throw new IllegalRegisterProcessException("?????? ????????? ???????????????.");
        }
        this.role = Role.ROLE_USER;
        return this;
    }

    public boolean isUser() {
        return this.role == Role.ROLE_USER;
    }

    public String email() {
        return this.email.getValue();
    }

}
