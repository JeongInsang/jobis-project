package com.ssafy.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.domain.users.dto.JwtDto;
import com.ssafy.domain.users.dto.response.JwtResponse;
import com.ssafy.domain.users.service.RefreshTokenService;
import com.ssafy.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

/**
 * CustomUsernamePasswordAuthenticationFilter 에서 인증 성공 후 실행되는 핸들러
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.refresh-expired}")
    private Long refreshTokenExpired;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("=== success handler ===");
        // 인증 성공 시 동작 정의

        // 토큰 생성
        JwtDto jwtDto = jwtUtil.generateJwtDto(authentication);

        refreshTokenService.addRefreshToken(Long.valueOf(authentication.getName()), jwtDto.getRefreshToken());

        JwtResponse jwtResponse = JwtResponse.builder()
                .type(jwtDto.getType())
                .accessToken(jwtDto.getAccessToken())
                .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try(OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, ResponseEntity.ok(jwtResponse));
            os.flush();
        }


    }


}
