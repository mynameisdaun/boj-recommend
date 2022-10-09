package com.daun.word.domain.recommend.controller;

import com.daun.word.domain.member.domain.Member;
import com.daun.word.domain.member.domain.vo.Email;
import com.daun.word.domain.member.domain.vo.Nickname;
import com.daun.word.domain.member.domain.vo.SocialType;
import com.daun.word.domain.recommend.service.RecommendService;
import com.daun.word.global.vo.Tier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {
    private final RecommendService recommendService;

    @PostMapping("")
    public ResponseEntity<?> recommend() {
        Member member = new Member(2, new Email("daun9870jung"), "$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS", new Nickname("다우니"), new Tier(15), SocialType.W, 1, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        return ResponseEntity.ok(recommendService.recommendForMember(member));
    }
}
