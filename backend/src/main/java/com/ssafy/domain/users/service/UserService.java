package com.ssafy.domain.users.service;

import com.ssafy.domain.users.dto.request.RegisterRequest;
import com.ssafy.domain.users.entity.Users;
import com.ssafy.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {

        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());
        request.setPassword(password);

        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 해당 이메일로 가입"); // 이메일중복
        }

        Users user = Users.createUser(request);
        userRepository.save(user);
        log.info("가입할 유저이메일 = {}", user.getEmail());
    }

    public Users socialRegister(Users user) {
        return userRepository.save(user);
    }

//    private void validateDuplicateMember(UserDto user) {
//        Users findUser = findUser(user.getId());
//        if (findUser != null) {
//            throw new IllegalStateException("이미 가입된 회원입니다.");
//        }
//    }

    public Users findUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 유저가 없다"));
    }

}
