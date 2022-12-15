package com.daun.word.domain.member.controller;

import com.daun.word.domain.member.exception.DuplicateMemberException;
import com.daun.word.domain.member.exception.NoSuchMemberException;
import com.daun.word.domain.member.exception.IllegalRegisterProcessException;
import com.daun.word.global.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.daun.word.global.constant.ApiResponseCode.*;

@RestControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<ApiResponse> duplicate_member(DuplicateMemberException e) {
        e.printStackTrace();
        return ResponseEntity.status(CONFLICT.getCode()).body(new ApiResponse(CONFLICT, e.getMessage()));
    }

    @ExceptionHandler(NoSuchMemberException.class)
    public ResponseEntity<ApiResponse> noSuchMemberException(NoSuchMemberException e) {
        e.printStackTrace();

        return ResponseEntity.status(NOT_FOUND.getCode()).body(new ApiResponse(NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(IllegalRegisterProcessException.class)
    public ResponseEntity<ApiResponse> registerNotInProgressExceptionHandler(IllegalRegisterProcessException e) {
        e.printStackTrace();;;
        return ResponseEntity.status(CONFLICT.getCode()).body(new ApiResponse(CONFLICT, e.getMessage()));
    }
}
