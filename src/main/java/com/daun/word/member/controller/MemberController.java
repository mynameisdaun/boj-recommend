package com.daun.word.member.controller;

import com.daun.word.config.security.Jwt;
import com.daun.word.member.domain.vo.Email;
import com.daun.word.member.dto.RegisterRequest;
import com.daun.word.member.service.MemberService;
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
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(memberService.register(request));
    }

    @GetMapping("/exist")
    public ResponseEntity<?> checkEmail(@RequestParam("email") Email email) {
        return null;
    }

}
