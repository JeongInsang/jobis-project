package com.ssafy.domain.company.controller;


import com.ssafy.domain.company.dto.response.*;
import com.ssafy.domain.company.service.CompanyService;
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
@RequestMapping("/company")
@Slf4j
@Tag(name = "기업API")
public class CompanyController {

    private final CompanyService companyService;


    // 기업검색









    /**
     * 기업 기본정보 조회
     * @param id
     * @return
     */
    @GetMapping("/info/{companyId}")
    public ResponseEntity<Object> companyInfo(@PathVariable("companyId") Integer id) {
        log.info("companyInfo로 받은 id : {}", id);
        CompanyResponse companyResponseList = companyService.findCompany(id);
        return SuccessResponse.createSuccess(SuccessCode.FIND_BASE_SUCCESS, companyResponseList);
    }

    /**
     * 기업 재무재표 조회
     * @param id
     * @return
     */

    @GetMapping("/statement/{companyId}")
    public ResponseEntity<Object> companyStatement(@PathVariable("companyId") Integer id){
        List<CompanyStatementResponse> companyStatementResponseList = companyService.findCompanyStatement(id);
        return SuccessResponse.createSuccess(SuccessCode.FIND_STATEMENT_SUCCESS, companyStatementResponseList);
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
        return SuccessResponse.createSuccess(SuccessCode.FIND_COMPANYYOUTH_SUCCESS, companyYouthResponseList);
    }

    /**
     * 기업 분기정보 조회
     * @param id
     * @return
     */
    @GetMapping("/quarter/{companyId}")
    public ResponseEntity<Object> companyQuarterInfo(@PathVariable("companyId") Integer id) {
        List<CompanyQuarterInfoResponse> companyQuarterInfoResponseList = companyService.findCompanyQuarterInfo(id);
        return SuccessResponse.createSuccess(SuccessCode.FIND_COMPANYQUARTERINFO_SUCCESS, companyQuarterInfoResponseList);
    }


    /**
     * 관심기업 스크랩
     * @param id
     * @return
     */
    @PostMapping("/scrap/{companyId}")
    public ResponseEntity<Object> companyScrapCreate(@PathVariable("companyId") Integer id) {
        companyService.companyScrap(id, 1);  // 수정해야댐
        return SuccessResponse.createSuccess(SuccessCode.CREATE_SCRAP_SUCCESS);
    }


    /**
     * 관심기업 스크랩 취소
     * @param id
     * @return
     */
    @DeleteMapping("/scrap/{companyScrapId}")
    public ResponseEntity<Object> companyScrapDelete(@PathVariable("companyId") Integer id) {
        companyService.companyScrapCancel(id, 1);   // 수정해야댇
        return SuccessResponse.createSuccess(SuccessCode.CANCEL_SCRAP_SUCCESS);
    }

}