package com.daun.word.domain.study.controller;

import com.daun.word.domain.study.dto.StudyDTO;
import com.daun.word.domain.study.dto.StudySaveRequest;
import com.daun.word.domain.study.service.StudyService;
import com.daun.word.global.dto.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("study")
@Api(tags = "Study API")
public class StudyController {
    private final StudyService studyService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(studyService.findAll());
    }

    @PostMapping("")
    @ApiOperation(value = "study 생성", notes = "study를 생성한다")
    public ResponseEntity<?> study(@RequestBody @Valid StudySaveRequest request) {
        return ResponseEntity.ok(new ApiResponse(new StudyDTO(studyService.save(request))));
    }
}
