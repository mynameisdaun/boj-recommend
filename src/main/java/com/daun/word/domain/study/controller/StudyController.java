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

import static com.daun.word.global.constant.ApiResponseCode.CREATED;
import static com.daun.word.global.constant.ApiResponseCode.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("study")
@Api(tags = "Study API")
public class StudyController {
    private final StudyService studyService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> findAll() {
        return ResponseEntity.ok(new ApiResponse(OK, studyService.findAll()));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> study(@RequestBody @Valid StudySaveRequest request) {
        return ResponseEntity.status(201).body(new ApiResponse(CREATED, new StudyDTO(studyService.save(request))));
    }
}
