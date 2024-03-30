package com.ssafy.domain.users.controller;

import com.ssafy.domain.company.dto.response.CompanyStatementResponse;
import com.ssafy.domain.users.dto.response.TendencyResponse;
import com.ssafy.domain.users.entity.Tendency;
import com.ssafy.domain.users.service.UserService;
import com.ssafy.global.response.code.SuccessCode;
import com.ssafy.global.response.structure.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
@Tag(name = "회원기업성향")
public class UserController {

    private final UserService userService;
//    @PostMapping("/tendency")
//    public ResponseEntity<Object> usersTendencyCreate() {
//        userService.applyUsersTendency();
//        return SuccessResponse.createSuccess(SuccessCode.APPLY_TENDENCY_SUCCESS);
//    }


    // 기업 성향 조회

    @GetMapping("/tendency")
    public ResponseEntity<Object> UsersTendency(){
        List<TendencyResponse> tendencyResponseList = userService.findTendency();
        return SuccessResponse.createSuccess(SuccessCode.FIND_TENDENCY_SUCCESS, tendencyResponseList);
    }
}
