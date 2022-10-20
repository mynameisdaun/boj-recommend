package com.daun.word.domain.study.controller;

import com.daun.word.domain.study.dto.StudyAssignRequest;
import com.daun.word.domain.study.dto.StudyRecommendRequest;
import com.daun.word.domain.study.dto.StudySaveRequest;
import com.daun.word.domain.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {
    private final StudyService studyService;

    @PostMapping("")
    public ResponseEntity<?> study(@RequestBody @Valid StudySaveRequest request) {
        return ResponseEntity.ok(studyService.save(request));
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assign(@RequestBody @Valid StudyAssignRequest request) {
        return ResponseEntity.ok(studyService.studyAssign(request));
    }

    @PostMapping("/recommend")
    public ResponseEntity<?> study_recommend(@RequestBody @Valid StudyRecommendRequest request) throws AuthenticationException, IOException {
        return ResponseEntity.ok(studyService.studyRecommend(request));
    }
}
