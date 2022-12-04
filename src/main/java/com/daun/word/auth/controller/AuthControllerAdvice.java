package com.daun.word.auth.controller;

import com.daun.word.auth.exception.NotAuthenticatedException;
import com.daun.word.auth.exception.NotAuthorizedException;
import com.daun.word.global.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.daun.word.global.constant.ApiResponseCode.FORBIDDEN;
import static com.daun.word.global.constant.ApiResponseCode.UNAUTHORIZED;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ApiResponse> notAuthorized(NotAuthorizedException e) {
        return ResponseEntity.status(UNAUTHORIZED.getCode()).body(new ApiResponse(UNAUTHORIZED, e.getMessage()));
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<ApiResponse> notAuthenticated(NotAuthenticatedException e) {
        return ResponseEntity.status(FORBIDDEN.getCode()).body(new ApiResponse(FORBIDDEN, e.getMessage()));
    }
}
