package com.daun.word.global.dto;

import com.daun.word.global.constant.ApiResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public ApiResponse(ApiResponseCode code, T data) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
    }

    public ApiResponse(ApiResponseCode ok) {
    }
}
