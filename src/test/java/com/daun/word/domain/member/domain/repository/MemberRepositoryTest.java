package com.daun.word.domain.member.domain.repository;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.QMember;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.global.vo.Name;
import com.daun.word.global.vo.Tier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import java.util.NoSuchElementException;
import java.util.Optional;
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
        //given
        UUID id = UUID.randomUUID();
        Member member = new Member(id, new Email("no-exist"), new Name("꼬북이"), "sample-password", new Tier(11), SocialType.W);
        //when
        Member saved = memberRepository.save(member);
        em.flush();
        //then
        assertThat(saved).isNotNull();
        assertAll(() -> assertThat(saved.getEmail().getValue()).isEqualTo("no-exist"), () -> assertThat(saved.getName().getValue()).isEqualTo("꼬북이"), () -> assertThat(passwordEncoder.matches("sample-password", saved.getPassword())), () -> assertThat(saved.getTier().getLevel()).isEqualTo(11), () -> assertThat(saved.getLoginCount()).isEqualTo(0), () -> assertThat(saved.getLastLoginAt()).isNull(), () -> assertThat(saved.getCreatedAt()).isNotNull(), () -> assertThat(saved.getUpdatedAt()).isNotNull(), () -> assertThat(saved.isDeleted()).isFalse());
    }

    @DisplayName("이미 존재하는 이메일로 새로운 회원이 가입할 수 없다")
    @Test
    void save_fail_exist() {
        //given&&when&then
        assertThatThrownBy(() -> {
            memberRepository.save(new Member(UUID.randomUUID(), new Email("daun9870jung"), new Name("꼬북이"), "sample-password", new Tier(11), SocialType.W));
            em.flush();
        }).hasCauseInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("아이디로 회원을 조회한다")
    @Test
    void findById() {
        //given&&when
        Member find = memberRepository.findById(UUID.fromString("f1860abc-2ea1-411b-bd4a-baa44f0d5580")).orElseThrow(NoSuchElementException::new);
        //then
        assertThat(find).isNotNull();
        assertAll(() -> assertThat(find.getEmail().getValue()).isEqualTo("daun9870jung"), () -> assertThat(find.getName().getValue()).isEqualTo("정다운"), () -> assertThat(find.getTier().getLevel()).isEqualTo(15), () -> assertThat(find.getLoginCount()).isEqualTo(0), () -> assertThat(find.getLastLoginAt()).isNotNull(), () -> assertThat(find.getCreatedAt()).isNotNull(), () -> assertThat(find.getUpdatedAt()).isNotNull(), () -> assertThat(find.isDeleted()).isFalse());
    }

    @DisplayName("존재하지 않는 회원은 아이디로 조회할 수 없다")
    @Test
    void findById_fail_no_exist() {
        //given&&when
        Optional<Member> empty = memberRepository.findById(UUID.randomUUID());
        //then
        assertThat(empty).isNotNull();
        assertThat(empty.isPresent()).isFalse();
    }

    @DisplayName(value = "이메일로 회원을 조회한다")
    @Test
    void findByEmail() throws Exception {
        //given&&when
        Member find = memberRepository.findByEmail(new Email("daun9870jung")).orElseThrow(NoSuchElementException::new);
        //then
        assertThat(find).isNotNull();
        assertAll(() -> assertThat(find.getId()).isEqualTo(UUID.fromString("f1860abc-2ea1-411b-bd4a-baa44f0d5580")), () -> assertThat(find.getEmail().getValue()).isEqualTo("daun9870jung"), () -> assertThat(find.getName().getValue()).isEqualTo("정다운"), () -> assertThat(find.getTier().getLevel()).isEqualTo(15), () -> assertThat(find.getLoginCount()).isEqualTo(0), () -> assertThat(find.getLastLoginAt()).isNotNull(), () -> assertThat(find.getCreatedAt()).isNotNull(), () -> assertThat(find.getUpdatedAt()).isNotNull(), () -> assertThat(find.isDeleted()).isFalse());
    }

    @DisplayName("존재하지 않는 회원은 이메일로 조회할 수 없다")
    @Test
    void findByEmail_fail_no_exist() {
        //given&&when
        Optional<Member> empty = memberRepository.findByEmail(new Email("no-exist-email"));
        //then
        assertThat(empty).isNotNull();
        assertThat(empty.isPresent()).isFalse();
    }

    @DisplayName("이메일로 중복되는 회원인지 확인할 수 있다")
    @Test
    void existsMemberByEmail() {
        //given
        Email email_exist = new Email("daun9870jung");
        Email email_no_exist = new Email("no-exist-email");
        //when
        boolean exist = memberRepository.existsMemberByEmail(email_exist);
        boolean no_exist = memberRepository.existsMemberByEmail(email_no_exist);
        //when
        assertAll(() -> assertThat(exist).isTrue(), () -> assertThat(no_exist).isFalse());
    }

    @DisplayName(value = "querydsltest")
    @Test
    void querydsl() throws Exception {
        UUID id = UUID.randomUUID();
        Member member = new Member(id, new Email("no-exist"), new Name("꼬북이"), "sample-password", new Tier(11), SocialType.W);
        em.persist(member);
        //when
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qmMember = new QMember("m");
        Member one = query.selectFrom(qmMember)
                .fetchOne();
        assertThat(one).isNotNull();
        assertAll(() -> assertThat(one.getEmail().getValue()).isEqualTo("no-exist"),
                () -> assertThat(one.getName().getValue()).isEqualTo("꼬북이"),
                () -> assertThat(passwordEncoder.matches("sample-password", one.getPassword())),
                () -> assertThat(one.getTier().getLevel()).isEqualTo(11),
                () -> assertThat(one.getLoginCount()).isEqualTo(0),
                () -> assertThat(one.getLastLoginAt()).isNull(),
                () -> assertThat(one.getCreatedAt()).isNotNull(),
                () -> assertThat(one.getUpdatedAt()).isNotNull(),
                () -> assertThat(one.isDeleted()).isFalse());
    }
}
