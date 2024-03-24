package com.ssafy.domain.worldcup.controller;

import com.ssafy.domain.worldcup.dto.response.WorldcupResponse;
import com.ssafy.domain.worldcup.service.WorldcupService;
import com.ssafy.global.response.code.SuccessCode;
import com.ssafy.global.response.structure.SuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/worldcup")
public class WorldcupController {

    private static final Logger logger = LoggerFactory.getLogger(WorldcupController.class);

    private final WorldcupService worldcupService;

    @GetMapping("/")
    public ResponseEntity<Object> worldcupList() {
        logger.info("REQUEST: Worldcup list");

        List<WorldcupResponse> randomWorldcups = worldcupService.findRandomWorldcups();

        return SuccessResponse.createSuccess(SuccessCode.FIND_WORLDCUP_SUCCESS, randomWorldcups);
    }

}
