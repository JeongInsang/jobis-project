package com.ssafy.security.util;

import com.ssafy.domain.users.dto.JwtDto;
import com.ssafy.domain.users.entity.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final Key key;

    @Value("${jwt.access-expired}")
    private Long accessTokenExpired;
    @Value("${jwt.refresh-expired}")
    private Long refreshTokenExpired;

    private final static String BEARER_TYPE = "Bearer";
    private final static String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private final static String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private final static String CLAIM_USER_ID_KEY = "userId";


    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = secret.getBytes();
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     *
     * accessToken, refreshToken을 생성하고
     * DTO에 해당 정보를 담아 리턴해주는 메서드
     *
     */
    public JwtDto generateJwtDto(Authentication authentication) {
        log.info("== jwtDto 생성 ==");
        log.info("authentication info {}", authentication.toString());

        String accessToken = Jwts.builder()
                .subject(ACCESS_TOKEN_SUBJECT)
                .claim(CLAIM_USER_ID_KEY, authentication.getName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpired))
                .signWith(key)
                .compact();

        String refreshToken = Jwts.builder()
                .subject(REFRESH_TOKEN_SUBJECT)
                .claim(CLAIM_USER_ID_KEY, authentication.getName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpired))
                .signWith(key)
                .compact();

        return JwtDto.builder()
                .type(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }



}
