package com.daun.word.auth.controller;

import com.daun.word.auth.dto.AuthenticationRequest;
import com.daun.word.auth.service.AuthService;
import com.daun.word.config.security.JwtAuthentication;
import com.daun.word.config.security.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService AuthService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody AuthenticationRequest request) {
        try {
            JwtAuthenticationToken authToken = new JwtAuthenticationToken(request.getEmail(), request.getPassword(), request.getSocialType());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            ResponseEntity.ok();
        } catch (AuthenticationException e) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return null;
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoCallBack(@RequestParam("code") String code) {
        return ResponseEntity.ok(AuthService.kakaoLogin(code));
    }


}
