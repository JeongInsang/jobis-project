package com.ssafy.domain.users.controller;

import com.ssafy.global.util.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
@Tag(name = "회원정보 관리")
public class UserController {

    private final AuthUtil authUtil;

    @GetMapping("/test")
    public ResponseEntity<Integer> test() {
        log.info("test 메서드 호출");
        return ResponseEntity.ok(authUtil.getLoginMemberId());
    }

}
