package com.ssafy.domain.users.controller;

import com.ssafy.domain.users.dto.request.RegisterRequest;
import com.ssafy.domain.users.service.UserAuthService;
import com.ssafy.domain.users.service.UserService;
import com.ssafy.global.response.code.SuccessCode;
import com.ssafy.global.response.structure.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
@Tag(name = "회원가입, 로그인 관리")
public class UserAuthController {

    private final UserService userService;
    private final UserAuthService userAuthService;

    @PostMapping("/register")
    @Operation(summary = "회원가입")
    public ResponseEntity<Object> register(
            @Valid @RequestBody RegisterRequest request) {
        log.info("register 메서드 호출");
        userService.register(request);
        return SuccessResponse.createSuccess(SuccessCode.JOIN_SUCCESS);
    }

    @GetMapping("/check")
    @Operation(summary = "이메일 중복조회")
    public ResponseEntity<Object> check(
            @RequestParam String email
    ) {
        final boolean check = userAuthService.checkEmail(email);
        if(check){
            return SuccessResponse.createSuccess(SuccessCode.CHECK_EMAIL_GOOD, true);
        } else {
            return SuccessResponse.createSuccess(SuccessCode.CHECK_EMAIL_BAD, false);
        }
    }
}
