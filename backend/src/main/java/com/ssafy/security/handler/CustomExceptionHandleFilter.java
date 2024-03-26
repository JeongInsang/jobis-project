package com.ssafy.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.global.response.code.ErrorCode;
import com.ssafy.global.response.code.ResponseCode;
import com.ssafy.global.response.exception.RestApiException;
import com.ssafy.global.response.structure.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.OutputStream;

@Component
@Slf4j
public class CustomExceptionHandleFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RestApiException e) {
            log.info("=== jwt 에러 발생 : {} ===", e.getResponseCode());

            ErrorResponse errorResponse = ErrorResponse.of(e.getResponseCode(), e.getResponseCode().getMessage());
            response.setStatus(e.getResponseCode().getHttpStatus().value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            try (OutputStream os = response.getOutputStream()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(os, errorResponse);
                os.flush();
            }
        }
    }

}
