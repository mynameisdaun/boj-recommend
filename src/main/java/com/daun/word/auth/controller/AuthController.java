package com.daun.word.auth.controller;

import com.daun.word.auth.dto.AuthenticationRequest;
import com.daun.word.auth.service.AuthService;
import com.daun.word.config.security.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService AuthService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("")
    public ResponseEntity<?> auth(@Valid @RequestBody AuthenticationRequest request) {
        try {
            JwtAuthenticationToken authToken = new JwtAuthenticationToken(request.getEmail(), request.getPassword(), request.getSocialType());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("details: {}", authentication.getDetails());
            ResponseEntity.ok(authentication.getDetails());
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
