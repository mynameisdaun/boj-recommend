package com.daun.word.domain.study.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.domain.recommend.service.RecommendService;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.StudyMember;
import com.daun.word.domain.study.domain.repository.StudyJpaRepository;
import com.daun.word.domain.study.domain.repository.StudyMemberJpaRepository;
import com.daun.word.domain.study.domain.repository.StudyRepository;
import com.daun.word.domain.study.dto.StudySaveRequest;
import com.daun.word.global.utils.HashUtils;
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

    private final StudyJpaRepository studyJpaRepository;

    private final StudyMemberJpaRepository studyMemberRepository;

    private final MemberService memberService;

    private final RecommendService recommendService;

    private final StudyHashService studyHashService;

    private final ProblemService problemService;

    @Transactional
    public Study save(final StudySaveRequest request) {
        checkArgument(request != null);
        final Member leader = memberService.findByEmail(request.getLeader());
        final Study study = studyJpaRepository.save(new Study(UUID.randomUUID(), leader, request.getStudyName(), HashUtils.sha256(request.getKey())));
        studyRepository.save(study);
        final List<StudyMember> studyMembers = request.getMembers()
                .stream()
                .map(m-> new StudyMember(study, memberService.findByEmail(new Email(m.getEmail()))))
                .collect(toList());
        studyMemberRepository.saveAll(studyMembers);
        return study;
    }

    @Transactional(readOnly = true)
    public Study findById(UUID id) {
        return studyRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
