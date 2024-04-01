package com.ssafy.domain.users.service;

import com.ssafy.domain.users.entity.SocialType;
import com.ssafy.domain.users.entity.Users;
import com.ssafy.domain.users.repository.UserRepository;
import com.ssafy.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public boolean checkEmail(String email) {
        log.info("== 이메일 검증 : {} ==", email);
        return !userRepository.existsByEmail(email);
    }

    public void logout(String accessToken) {
        String userId = jwtUtil.extractId(accessToken);
        refreshTokenService.deleteRefreshTokenByUserId(userId);

        SecurityContext context = SecurityContextHolder.getContext();
        log.info("============logout context : " + context);
        // 인증 객체 삭제
        context.setAuthentication(null);
        // Security Context 삭제
        SecurityContextHolder.clearContext();
        log.info("============ after context : " + SecurityContextHolder.getContext());
    }

    /**
     * 소셜타입과 소셜 id로 유저 찾기
     * 이미 가입했던 유저와 첫 회원가입하는 유저를 판별하기위해 작성
     * 예외를 던지지 않는 이유는 없을 때 회원가입 시키기 위함
     */
    public Users findUserBySocialTypeAndId(SocialType socialType, String socialId) {
        return userRepository.findBySocialTypeAndEmail(socialType, socialId)
                .orElse(null);
    }

    public Users findUserByEmailAndSocialType(SocialType socialType, String socialId) {
        return userRepository.findBySocialTypeAndSocialId(socialType, socialId)
                .orElse(null);
    }

}
