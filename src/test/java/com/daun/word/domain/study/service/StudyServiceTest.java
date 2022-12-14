package com.daun.word.domain.study.service;

import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import com.daun.word.domain.study.domain.repository.FakeStudyMemberRepository;
import com.daun.word.domain.study.domain.repository.FakeStudyRepository;
import com.daun.word.domain.study.dto.StudySaveRequest;
import com.daun.word.global.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.daun.word.Fixture.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class StudyServiceTest {

    private StudyService studyService;

    @BeforeEach
    public void setUp() {
        FakeMemberRepository fakeMemberRepository = new FakeMemberRepository();
        fakeMemberRepository.save(daun9870jung());
        fakeMemberRepository.save(member_2());
        fakeMemberRepository.save(member_3());
        FakeStudyRepository fakeStudyRepository = new FakeStudyRepository();
        fakeStudyRepository.save(study());
        FakeStudyMemberRepository fakeStudyMemberRepository = new FakeStudyMemberRepository();
        fakeStudyMemberRepository.save(new StudyMember(study(), daun9870jung()));
        fakeStudyMemberRepository.save(new StudyMember(study(), member_2()));
        study().enrollMember(new StudyMember(study(), daun9870jung()));
        study().enrollMember(new StudyMember(study(), member_2()));

        studyService = new StudyService(
                fakeStudyRepository,
                new FakeStudyMemberRepository(),
                new MemberService(fakeMemberRepository, new BCryptPasswordEncoder()),
                new DefaultHashService()
        );
    }

    @DisplayName(value = "???????????? ????????????")
    @Test
    void save() throws Exception {
        //given
        String leader = daun9870jung().getEmail().getValue();
        List<String> members = Arrays.asList(daun9870jung().getEmail().getValue(), member_2().getEmail().getValue());
        StudySaveRequest request = new StudySaveRequest(leader, "name", "1234", members);
        //when
        Study study = studyService.create(request);
        //then
        assertThat(study).isNotNull();
        assertAll(
                () -> assertThat(study.getId()).isInstanceOf(UUID.class),
                () -> assertThat(study.getStudyName()).isInstanceOf(Name.class),
                () -> assertThat(study.getStudyName().getValue()).isEqualTo("name"),
                () -> assertThat(study.getHash()).isInstanceOf(String.class),
                () -> assertThat(study.getStudyMembers().size()).isEqualTo(2),
                () -> assertThat(study.getStudyMembers().containsAll(Arrays.asList(daun9870jung(), member_2())))
        );
    }

    @DisplayName(value = "????????? ????????? ????????? ???????????? ???????????? ????????? ??? ??????")
    @Test
    void save_fail_not_joined_leader() throws Exception {
        //given
        String leader = "no-exist-leader";
        List<String> members = Arrays.asList(daun9870jung().getEmail().getValue(), member_2().getEmail().getValue());
        StudySaveRequest request = new StudySaveRequest(leader, "name", "1234", members);
        //when&&then
        assertThatThrownBy(() -> studyService.create(request))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("???????????? ?????? ?????? ?????????");
    }

    @DisplayName(value = "??????????????? ????????? ???????????? ???????????? ????????? ??? ??????")
    @Test
    void save_fail_not_joined_member() throws Exception {
        //given
        String leader = daun9870jung().getEmail().getValue();
        List<String> members = Arrays.asList(daun9870jung().getEmail().getValue(), member_2().getEmail().getValue(), member_unidentified().getEmail().getValue());
        StudySaveRequest request = new StudySaveRequest(leader, "name", "1234", members);
        //when&&then
        assertThatThrownBy(() -> studyService.create(request))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("???????????? ?????? ?????? ?????????");
    }

    @DisplayName(value = "ID??? ???????????? ????????????")
    @Test
    void findById() throws Exception {
        //given
        UUID id = study().getId();
        //when
        Study study = studyService.findById(id);
        //then
        assertThat(study).isNotNull();
        assertAll(
                () -> assertThat(study.getId()).isEqualTo(id)
        );
    }

    @DisplayName(value = "???????????? ?????? ????????? ?????????")
    @Test
    void findById_fail_no_exist() throws Exception {
        //given&&when&&then
        assertThatThrownBy(() -> studyService.findById(UUID.randomUUID()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("???????????? ?????? ????????? ?????????");
    }
}
