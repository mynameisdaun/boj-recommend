package com.daun.word.oauth.kakao.controller;

import com.daun.word.oauth.kakao.service.KakaoOAuthClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/oauth")
public class
OAuthController {

    private final KakaoOAuthClient kakaoOAuthClient;

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakao_token(@RequestParam("code") String code) {
        return kakaoOAuthClient.token(code);
    }
}
