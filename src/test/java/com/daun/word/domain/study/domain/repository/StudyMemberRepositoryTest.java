package com.daun.word.domain.study.domain.repository;

import com.daun.word.Fixture.Fixture;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class StudyMemberRepositoryTest {

    @Autowired
    private StudyMemberRepository studyMemberRepository;

    @DisplayName(value = "스터디 회원을 저장한다")
    @Test
    void save() throws Exception {
        //given
        Study s = Fixture.study();
        Member m = Fixture.member_2();
        StudyMember sm = new StudyMember(s, m);
        //when
        StudyMember save = studyMemberRepository.save(sm);
        //then
        assertThat(save).isNotNull();
        assertAll(
                () -> assertThat(save.getId()).isPositive(),
                () -> assertThat(save.getMember().getId()).isEqualTo(m.getId()),
                () -> assertThat(save.getStudy().getId()).isEqualTo(s.getId())
        );
    }

}
