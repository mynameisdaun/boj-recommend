package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        em.clear();
    }

    @DisplayName("회원을 저장한다")
    @Test
    void save() {
        UUID id = UUID.randomUUID();
        Member member = new Member(id, new Email("no-exist"), new Name("꼬북이"), "sample-password", new Tier(11), SocialType.W);
        Member saved = memberRepository.save(member);
        em.flush();
        assertThat(saved).isNotNull();
        assertAll(
                () -> assertThat(saved.getEmail().getValue()).isEqualTo("no-exist"),
                () -> assertThat(saved.getName().getValue()).isEqualTo("꼬북이"),
                () -> assertThat(passwordEncoder.matches("sample-password", saved.getPassword())),
                () -> assertThat(saved.getTier().getLevel()).isEqualTo(11),
                () -> assertThat(saved.getLoginCount()).isEqualTo(0),
                () -> assertThat(saved.getLastLoginAt()).isNull(),
                () -> assertThat(saved.getCreatedAt()).isNotNull(),
                () -> assertThat(saved.getUpdatedAt()).isNotNull(),
                () -> assertThat(saved.isDeleted()).isFalse()
        );
    }

    @DisplayName("이미 존재하는 이메일로 새로운 회원이 가입할 수 없다")
    @Test
    void save_fail_exist() {
        assertThatThrownBy(() -> {
            memberRepository.save(new Member(UUID.randomUUID(), new Email("daun9870jung"), new Name("꼬북이"), "sample-password", new Tier(11), SocialType.W));
            em.flush();
        }).hasCauseInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("회원을 조회한다")
    @Test
    void findById() {
        Member find = memberRepository.findById(UUID.fromString("f1860abc-2ea1-411b-bd4a-baa44f0d5580"))
                .orElseThrow(NoSuchElementException::new);
        assertThat(find).isNotNull();
        assertAll(
                () -> assertThat(find.getEmail().getValue()).isEqualTo("daun9870jung"),
                () -> assertThat(find.getName().getValue()).isEqualTo("정다운"),
                () -> assertThat(find.getTier().getLevel()).isEqualTo(15),
                () -> assertThat(find.getLoginCount()).isEqualTo(0),
                () -> assertThat(find.getLastLoginAt()).isNotNull(),
                () -> assertThat(find.getCreatedAt()).isNotNull(),
                () -> assertThat(find.getUpdatedAt()).isNotNull(),
                () -> assertThat(find.isDeleted()).isFalse()
        );
    }

    @Test
    void findMemberByEmail() {
    }

    @Test
    void existsMemberByEmail() {
    }
}
