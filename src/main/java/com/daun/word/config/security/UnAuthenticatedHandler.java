package com.daun.word.config.security;

import com.daun.word.global.constant.ApiResponseCode;
import com.daun.word.global.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UnAuthenticatedHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(ApiResponseCode.UNAUTHORIZED.getCode());
        ApiResponse message = new ApiResponse(ApiResponseCode.UNAUTHORIZED, "인증되지 않은 사용자 입니다");
        response.setHeader("content-type", "application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(message));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
