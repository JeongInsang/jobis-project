package com.ssafy.domain.company.controller;


import com.ssafy.domain.company.dto.response.CompanyQuarterInfoResponse;
import com.ssafy.domain.company.dto.response.CompanyRateResponse;
import com.ssafy.domain.company.dto.response.CompanyResponse;
import com.ssafy.domain.company.dto.response.CompanyYouthResponse;
import com.ssafy.domain.company.service.CompanyService;
import com.ssafy.global.response.code.SuccessCode;
import com.ssafy.global.response.structure.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    private final CompanyService companyService;
    /**
     * 기업 기본정보 조회
     * @param id
     * @return
     */
    @GetMapping("/info/{companyId}")
    public ResponseEntity<Object> companyInfo(@PathVariable("companyId") Integer id) {
        log.info("companyInfo로 받은 id : {}", id);
        List<CompanyResponse> companyResponseList = companyService.findCompany(id);

        return SuccessResponse.createSuccess(SuccessCode.FIND_BASE_SUCCESS, companyResponseList);
    }


    /**
     * 기업 재무비율 조회
     * @param id
     * @return
     */
    @GetMapping("/rate/{companyId}")
    public ResponseEntity<Object> companyRate(@PathVariable("companyId") Integer id) {
        List<CompanyRateResponse> companyRateResponseList = companyService.findCompanyRate(id);
        return SuccessResponse.createSuccess(SuccessCode.FIND_RATE_SUCCESS, companyRateResponseList);
    }


    /**
     * 기업 청년친화 조회
     * @param id
     * @return
     */
    @GetMapping("/youthcompany/{companyId}")
    public ResponseEntity<Object> companyYouth(@PathVariable("companyId") Integer id) {
        List<CompanyYouthResponse> companyYouthResponseList = companyService.findCompanyYouth(id);

        return SuccessResponse.createSuccess(SuccessCode.FIND_YOUTHCOMPANY_SUCCESS, companyYouthResponseList);
    }

    /**
     * 기업 분기정보 조회
     * @param id
     * @return
     */
    @GetMapping("/quarter/{companyId}")
    public ResponseEntity<Object> companyQuarter(@PathVariable("companyId") Integer id) {
        List<CompanyQuarterInfoResponse> companyQuarterInfoResponseList = companyService.findCompanyQuarterInfo(id);

        return SuccessResponse.createSuccess(SuccessCode.FIND_COMPANYQUARTERINFO_SUCCESS, companyQuarterInfoResponseList);
    }



}