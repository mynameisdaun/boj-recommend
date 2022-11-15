package com.daun.word.domain.recommend.controller;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.service.MemberService;
import com.daun.word.domain.recommend.dto.RecommendResponse;
import com.daun.word.domain.recommend.service.RecommendService;
import com.daun.word.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> recommend(@RequestParam @NotNull String handle) {
        Member member = memberService.findByEmail(new Email(handle));
        return ResponseEntity.ok(new ApiResponse(new RecommendResponse(recommendService.recommend()));
    }
}
