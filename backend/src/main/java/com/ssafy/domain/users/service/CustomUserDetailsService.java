package com.ssafy.domain.users.service;

import com.ssafy.domain.users.entity.Users;
import com.ssafy.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당유저없음"));
    }

    private UserDetails createUserDetails(Users user) {
        return new User(
                String.valueOf(user.getId()), // userPk로 생성
                user.getPassword(),
                Collections.emptyList()); // 권한 정보를 빈 리스트로 전달하여 권한 없이 유저 생성 (roletype 없기때문)
    }
}
