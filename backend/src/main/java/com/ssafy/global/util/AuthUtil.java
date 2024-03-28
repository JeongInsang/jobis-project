package com.ssafy.global.util;

import com.ssafy.domain.users.entity.Users;
import com.ssafy.domain.users.exception.UserNotFoundException;
import com.ssafy.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final UserRepository userRepository;

    public Integer getLoginUserId() {
        try {
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            return Integer.valueOf(userId);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Users getLoginUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findById(Integer.valueOf(userId))
                .orElseThrow(UserNotFoundException::new);
    }

}