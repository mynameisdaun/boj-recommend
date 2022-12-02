package com.daun.word.domain.member.controller;

import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.domain.member.dto.RegisterResponse;
import com.daun.word.domain.member.exception.DuplicateMemberException;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static com.daun.word.global.constant.ApiResponseCode.CONFLICT;
import static com.daun.word.global.constant.ApiResponseCode.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(new ApiResponse(OK, new RegisterResponse(memberService.register(request))));
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ApiResponse> duplicate_member(DuplicateMemberException e) {
        return ResponseEntity.status(CONFLICT.getCode()).body(new ApiResponse(CONFLICT, e.getMessage()));
    }


}
