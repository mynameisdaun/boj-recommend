package com.daun.word.domain.study.service;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.problem.domain.Problem;
import com.daun.word.domain.problem.service.ProblemService;
import com.daun.word.domain.recommend.domain.Recommend;
import com.daun.word.domain.recommend.service.RecommendService;
import com.daun.word.domain.study.domain.Study;
import com.daun.word.domain.study.domain.repository.StudyRepository;
import com.daun.word.domain.study.dto.*;
import com.daun.word.global.Id;
import com.daun.word.global.utils.HashUtils;
import com.daun.word.global.vo.YesNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    private final MemberService memberService;

    private final RecommendService recommendService;

    private final StudyHashService studyHashService;

    private final ProblemService problemService;

    @Transactional
    public StudyAssignResponse studyAssign(StudyAssignRequest request) {
        Study study = studyRepository.findById(Id.of(Study.class, request.getId()))
                .orElseThrow(NoSuchElementException::new);
        Problem problem = problemService.findById(Id.of(Problem.class, request.getProblemId()));
        Recommend assign = recommendService.assign(study, problem);
        return new StudyAssignResponse(assign.getProblem());
    }

    @Transactional
    public StudyRecommendResponse studyRecommend(StudyRecommendRequest request) throws AuthenticationException, IOException {
        Study study = studyRepository.findById(Id.of(Study.class, request.getId()))
                .orElseThrow(NoSuchElementException::new);
        study.auth(request.getKey(), studyHashService);

        List<Recommend> recommendForStudy = recommendService.recommendForStudy(study);
        List<Recommend> recommendForMember = new ArrayList<>();
        for (Member m : study.getMembers()) {
            recommendForMember.add(recommendService.recommendForMember_v2(m).get(0));
        }
        return new StudyRecommendResponse(recommendForStudy, recommendForMember);
    }

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
