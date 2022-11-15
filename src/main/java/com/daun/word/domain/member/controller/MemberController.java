package com.daun.word.domain.member.controller;

import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(new ApiResponse(memberService.register(request)));
    }


}
