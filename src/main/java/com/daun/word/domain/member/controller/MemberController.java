package com.daun.word.domain.member.controller;

import com.daun.word.config.security.JwtAuthentication;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.dto.RegisterAuthRequest;
import com.daun.word.domain.member.dto.MemberDTO;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.domain.member.dto.RegisterResponse;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.member.service.RegisterService;
import com.daun.word.domain.member.service.UpdateMemberInfoService;
import com.daun.word.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.daun.word.global.constant.ApiResponseCode.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    private final RegisterService registerService;

    private final UpdateMemberInfoService updateMemberInfoService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(new ApiResponse(OK, new RegisterResponse(registerService.register(request))));
    }

    @GetMapping("/{email}/auth")
    public ResponseEntity<ApiResponse> checkAssignment(@PathVariable String email) {
        return ResponseEntity.ok(new ApiResponse(OK, new RegisterResponse(registerService.auth_assignments(new Email(email)))));
    }

    @PostMapping("/{email}/auth")
    public ResponseEntity<ApiResponse> authenticate(@Valid @RequestBody RegisterAuthRequest request) {
        return ResponseEntity.ok(new ApiResponse(OK, new MemberDTO(registerService.authenticate(request))));
    }

    @PostMapping("/{email}/update")
    public ResponseEntity<ApiResponse> update(@AuthenticationPrincipal JwtAuthentication authentication) {
        return ResponseEntity.ok(new ApiResponse(OK, new MemberDTO(updateMemberInfoService.solved(authentication))));
    }
}
