package com.ssafy.domain.users.service;

import com.ssafy.domain.users.entity.RefreshToken;
import com.ssafy.domain.users.exception.JwtInvalidException;
import com.ssafy.domain.users.exception.UserNotFoundException;
import com.ssafy.domain.users.repository.RefreshTokenRedisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Value("${jwt.refresh-expired}")
    private Long JWT_EXPIRED;

    public void addRefreshToken(Long userId, String tokenValue) {
        RefreshToken refreshToken =  RefreshToken.builder()
                .id(userId.toString())
                .refreshToken(tokenValue)
                .refreshTokenExpired(JWT_EXPIRED)
                .build();
        refreshTokenRedisRepository.save(refreshToken);
    }

    public String findRefreshToken(String userId) {
        return refreshTokenRedisRepository.findById(userId)
                .orElseThrow(JwtInvalidException::new)
                .getRefreshToken();
    }

    public void deleteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRedisRepository.delete(refreshToken);
    }


}
