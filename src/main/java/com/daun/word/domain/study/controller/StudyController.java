package com.daun.word.domain.study.controller;

import com.daun.word.config.security.JwtAuthentication;
import com.daun.word.domain.study.dto.StudyDTO;
import com.daun.word.domain.study.dto.StudyJoinRequest;
import com.daun.word.domain.study.dto.StudySaveRequest;
import com.daun.word.domain.study.service.StudyService;
import com.daun.word.global.dto.ApiResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        return ResponseEntity.status(CREATED.getCode()).body(new ApiResponse(CREATED, new StudyDTO(studyService.create(request))));
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse> join (
            @AuthenticationPrincipal JwtAuthentication auth,
            @RequestBody @Valid StudyJoinRequest request) {
        return ResponseEntity.status(OK.getCode()).body(new ApiResponse(OK, new StudyDTO(studyService.join(auth.getEmail(), request))));
    }
}
