package com.ssafy.global.util;

import com.ssafy.domain.users.entity.Users;
import com.ssafy.domain.users.exception.UserNotFoundException;
import com.ssafy.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthUtil {

    private final UserRepository userRepository;

    public Integer getLoginUserId() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return Integer.valueOf(userId);
    }

    public Users getLoginUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findById(Integer.valueOf(userId))
                .orElseThrow(UserNotFoundException::new);
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("isAuthenticated 메서드 : {}", SecurityContextHolder.getContext().getAuthentication().getName());
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }

}