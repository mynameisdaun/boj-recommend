package com.daun.word.domain.assignment.service;

import com.daun.word.domain.assignment.domain.Assignment;
import com.daun.word.domain.assignment.domain.repository.AssignmentRepository;
import com.daun.word.domain.assignment.dto.AssignmentRequest;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.recommend.service.RecommendService;
import com.daun.word.domain.study.service.StudyService;
import com.daun.word.global.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentService {

    private final MemberService memberService;

    private final AssignmentRepository assignmentRepository;

    private final RecommendService recommendService;

    private final StudyService studyService;

    @Transactional
    public Assignment findById(Id<Assignment, Integer> id) {
        return assignmentRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Assignment save(AssignmentRequest request) {
        Assignment assignment = new Assignment(request);
        assignmentRepository.save(new Assignment(request));
        return assignment;
    }
}
