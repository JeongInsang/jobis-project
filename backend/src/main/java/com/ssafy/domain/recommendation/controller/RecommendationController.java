package com.ssafy.domain.recommendation.controller;

import com.ssafy.domain.company.dto.response.CompanyResponse;
import com.ssafy.domain.recommendation.service.RecommendationService;
import com.ssafy.global.response.code.SuccessCode;
import com.ssafy.global.response.structure.SuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommendation")
public class RecommendationController {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationController.class);

    private final RecommendationService recommendationService;

    @GetMapping("/{companyId}")
    public ResponseEntity<Object> recommendationList(@PathVariable("companyId") Integer companyId) {
        logger.info("REQUEST: Recommendation list: {}", companyId);

        List<CompanyResponse> companyResponseList = recommendationService.findCompanyByRecommendationId(companyId);

        return SuccessResponse.createSuccess(SuccessCode.FIND_RECOMMENDATION_SUCCESS);
    }

}
