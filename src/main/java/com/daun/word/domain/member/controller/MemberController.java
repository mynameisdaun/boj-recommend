package com.daun.word.domain.member.controller;

import com.couchbase.client.deps.io.netty.buffer.DuplicatedByteBuf;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.dto.RegisterRequest;
import com.daun.word.domain.member.dto.RegisterResponse;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.global.dto.ApiResponse;
import javassist.bytecode.DuplicateMemberException;
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
        return ResponseEntity.ok(new ApiResponse(new RegisterResponse(memberService.register(request))));
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ApiResponse> duplicate_member() {
        return ResponseEntity.status(409).body(new ApiResponse("이미 가입된 회원입니다."));
    }


}
