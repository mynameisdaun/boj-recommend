package com.daun.word.domain.study.domain.repository;

import com.daun.word.Fixture.Fixture;
import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import com.daun.word.global.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class StudyRepositoryTest {

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        em.clear();
    }


    @DisplayName(value = "스터디를 저장한다")
    @Test
    void save() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        Member daun9870jung = Fixture.daun9870jung();
        Study study = new Study(id, daun9870jung, new Name("test-name"), "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
        StudyMember sm = new StudyMember(study, daun9870jung);
        study.enrollMember(Arrays.asList(sm));
        //when
        Study saved = studyRepository.save(study);
        em.flush();
        //then
        assertThat(saved).isNotNull();
        assertAll(
                () -> assertThat(saved.getId()).isEqualTo(id),
                () -> assertThat(saved.getHash()).isEqualTo("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"),
                () -> assertThat(saved.getStudyName().getValue()).isEqualTo("test-name"),
                () -> assertThat(saved.getLeader()).isEqualTo(daun9870jung),
                () -> assertThat(saved.getStudyMembers().size()).isEqualTo(1),
                () -> assertThat(saved.getStudyMembers().get(0)).isEqualTo(sm)
        );
    }

    @DisplayName(value = "id로 스터디를 조회한다")
    @Test
    void findById() throws Exception {
        //given
        UUID id = UUID.fromString("1ee72417-fea2-2ff3-4a80-dac3d075b046");
        //when
        Study find = studyRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        //then
        assertThat(find).isNotNull();
        assertAll(
                () -> assertThat(find.getId()).isEqualTo(id),
                () -> assertThat(find.getHash()).isNotEmpty(),
                () -> assertThat(find.getStudyName().getValue()).isEqualTo("example-study"),
                () -> assertThat(find.getLeader().getEmail().getValue()).isEqualTo("daun9870jung"),
                () -> assertThat(find.getStudyMembers().size()).isEqualTo(4),
                () -> assertThat(find.getStudyMembers().stream().map(sm -> sm.getMember().getEmail().getValue())).containsAll(Arrays.asList("daun9870jung", "pine98", "shp7724", "baggomsoon96"))
        );
    }

    @DisplayName(value = "존재하지 않는 스터디는 조회할 수 없다")
    @Test
    void findById_no_exist() throws Exception {
        //given&&when
        Optional<Study> no_exist = studyRepository.findById(UUID.randomUUID());
        //then
        assertThat(no_exist).isNotNull();
        assertAll(
                () -> assertThat(no_exist.isPresent()).isFalse()
        );
    }
}
