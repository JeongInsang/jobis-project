package com.ssafy.domain.users.service;

import com.ssafy.domain.users.entity.RefreshToken;
import com.ssafy.domain.users.repository.RefreshTokenRedisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    public void addRefreshToken(Long memberId, String tokenValue) {
        RefreshToken refreshToken =  RefreshToken.builder()
                .id(memberId.toString())
                .refreshToken(tokenValue)
                .build();
        refreshTokenRedisRepository.save(refreshToken);
    }

    public void deleteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRedisRepository.delete(refreshToken);
    }


}
