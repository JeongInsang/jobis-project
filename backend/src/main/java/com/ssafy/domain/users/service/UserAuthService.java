package com.ssafy.domain.users.service;

import com.ssafy.domain.users.entity.SocialType;
import com.ssafy.domain.users.entity.Users;
import com.ssafy.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAuthService {

    private final UserRepository userRepository;

    public boolean checkEmail(String email) {
        return !userRepository.existsByEmail(email);
    }

    /**
     * 소셜타입과 소셜 id로 유저 찾기
     * 이미 가입했던 유저와 첫 회원가입하는 유저를 판별하기위해 작성
     * 예외를 던지지 않는 이유는 없을 때 회원가입 시키기 위함
     */
    public Users findUserBySocialTypeAndId(SocialType socialType, String socialId) {
        return userRepository.findBySocialTypeAndSocialId(socialType, socialId)
                .orElse(null);
    };

}
