package com.daun.word.domain.study.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.repository.StudyRepository;
import com.daun.word.domain.study.dto.StudySaveRequest;
import com.daun.word.global.Id;
import com.daun.word.global.utils.HashUtils;
import com.daun.word.global.vo.YesNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    private final MemberService memberService;

    @Transactional
    public Study save(StudySaveRequest request) {
        Member leader = memberService.findByEmail(request.getLeader());
        List<Member> members = request.getMembers().
                stream()
                .map(memberService::findByEmail)
                .collect(toList());
        Study study = new Study(leader, request.getStudyName(), HashUtils.sha256(request.getKey()), YesNo.N, members);
        studyRepository.save(study);
        study.getMembers().stream().forEach(m -> studyRepository.saveStudyMember(Id.of(Study.class, study.getId()), m, YesNo.N));
        return study;
    }

    @Transactional(readOnly = true)
    public Study findById(Id<Study, Integer> id) {
        return studyRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
