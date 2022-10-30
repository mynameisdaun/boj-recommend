package com.daun.word.domain.study.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import com.daun.word.domain.study.domain.repository.StudyMemberRepository;
import com.daun.word.domain.study.domain.repository.StudyRepository;
import com.daun.word.domain.study.dto.StudySaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    private final StudyMemberRepository studyMemberRepository;

    private final MemberService memberService;

    private final StudyHashService studyHashService;


    /**
     * 스터디 생성
     * 스터디 비밀번호 key는 sha256 알고리즘으로 Hash
     *
     * @param request StudySaveRequest
     * @return Study
     */
    @Transactional
    public Study save(final StudySaveRequest request) {
        checkArgument(request != null);
        final Member leader = memberService.findByEmail(request.getLeader());
        final Study study = studyRepository.save(new Study(UUID.randomUUID(), leader, request.getStudyName(), studyHashService.sha256(request.getKey())));
        studyRepository.save(study);
        final List<StudyMember> studyMembers = request.getMembers()
                .stream()
                .map(m -> studyMemberRepository.save(new StudyMember(study, memberService.findByEmail(new Email(m.getEmail())))))
                .collect(toList());
        study.enrollMember(studyMembers);
        return study;
    }

    /**
     * ID로 스터디 조회
     *
     * @param id UUID
     * @return Study
     * @throws NoSuchElementException 존재하지 않는 스터디 입니다.
     */
    @Transactional(readOnly = true)
    public Study findById(UUID id) {
        return studyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 스터디 입니다."));
    }
}
