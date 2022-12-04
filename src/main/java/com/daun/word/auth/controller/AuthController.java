package com.daun.word.auth.controller;

import com.daun.word.auth.dto.AuthRequest;
import com.daun.word.config.security.JwtAuthenticationToken;
import com.daun.word.global.constant.ApiResponseCode;
import com.daun.word.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("")
    public ResponseEntity<ApiResponse> authentication(@RequestBody AuthRequest authRequest) {
        JwtAuthenticationToken authToken = new JwtAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(new ApiResponse(ApiResponseCode.OK, authentication.getDetails()));
    }

}
