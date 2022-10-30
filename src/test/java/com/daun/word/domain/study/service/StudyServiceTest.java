package com.daun.word.domain.study.service;

import com.daun.word.domain.member.domain.repository.FakeMemberRepository;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.study.domain.Study;
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
        fakeMemberRepository.save(member_1());
        fakeMemberRepository.save(member_2());
        fakeMemberRepository.save(member_3());
        studyService = new StudyService(
                new FakeStudyRepository(),
                new FakeStudyMemberRepository(),
                new MemberService(fakeMemberRepository, new BCryptPasswordEncoder()),
                new DefaultHashService()
        );
    }

    @DisplayName(value = "스터디를 생성한다")
    @Test
    void save() throws Exception {
        //given
        String leader = member_1().getEmail().getValue();
        List<String> members = Arrays.asList(member_1().getEmail().getValue(), member_2().getEmail().getValue());
        StudySaveRequest request = new StudySaveRequest(leader, "name", "1234", members);
        //when
        Study study = studyService.save(request);
        //then
        assertThat(study).isNotNull();
        assertAll(
                () -> assertThat(study.getId()).isInstanceOf(UUID.class),
                () -> assertThat(study.getStudyName()).isInstanceOf(Name.class),
                () -> assertThat(study.getStudyName().getValue()).isEqualTo("name"),
                () -> assertThat(study.getHash()).isInstanceOf(String.class),
                () -> assertThat(study.getStudyMembers().size()).isEqualTo(2),
                () -> assertThat(study.getStudyMembers().containsAll(Arrays.asList(member_1(), member_2())))
        );
    }

    @DisplayName(value = "스터디 리더가 회원이 아니라면 스터디를 생성할 수 없다")
    @Test
    void save_fail_not_joined_leader() throws Exception {
        //given
        String leader = "no-exist-leader";
        List<String> members = Arrays.asList(member_1().getEmail().getValue(), member_2().getEmail().getValue());
        StudySaveRequest request = new StudySaveRequest(leader, "name", "1234", members);
        //when&&then
        assertThatThrownBy(() -> studyService.save(request))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 회원 입니다");
    }

    @DisplayName(value = "스터디원이 회원이 아니라면 스터디를 생성할 수 없다")
    @Test
    void save_fail_not_joined_member() throws Exception {
        //given
        String leader = member_1().getEmail().getValue();
        List<String> members = Arrays.asList(member_1().getEmail().getValue(), member_2().getEmail().getValue(), member_4().getEmail().getValue());
        StudySaveRequest request = new StudySaveRequest(leader, "name", "1234", members);
        //when&&then
        assertThatThrownBy(() -> studyService.save(request))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("존재하지 않는 회원 입니다");
    }


}
