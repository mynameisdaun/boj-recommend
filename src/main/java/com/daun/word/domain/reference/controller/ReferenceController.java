package com.daun.word.domain.reference.controller;

import com.daun.word.domain.reference.dto.RefRegisterRequest;
import com.daun.word.domain.reference.dto.RefRegisterResponse;
import com.daun.word.domain.reference.dto.ReferenceDTO;
import com.daun.word.domain.reference.service.ReferenceService;
import com.daun.word.global.dto.ApiResponse;
import com.daun.word.global.vo.URL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.daun.word.global.constant.ApiResponseCode.CREATED;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceService referenceService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RefRegisterRequest request) {
        return ResponseEntity.status(CREATED.getCode())
                .body(new ApiResponse(CREATED, new RefRegisterResponse(referenceService.register(request))));
    }
}
