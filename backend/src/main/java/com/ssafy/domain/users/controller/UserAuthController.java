package com.ssafy.domain.users.controller;

import com.ssafy.domain.users.dto.request.RegisterRequest;
import com.ssafy.domain.users.service.UserService;
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
@RequestMapping("/auth")
@Slf4j
@Tag(name = "회원가입, 로그인 관리")
public class UserAuthController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "회원가입")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {
        log.info("register 메서드 호출");
        String res = userService.register(request);
        return ResponseEntity.ok(res);
    }


}
