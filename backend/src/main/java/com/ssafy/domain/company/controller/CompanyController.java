package com.ssafy.domain.company.controller;


import com.ssafy.domain.company.dto.response.*;
import com.ssafy.domain.company.entity.CompanyScrap;
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


//    /**
//     * 관심기업 스크랩
//     * @param id
//     * @return
//     */
//    @GetMapping("/scrap/{companyId}")
//    public ResponseEntity<Object> companyCreateScrap(@PathVariable("companyId") Integer id) {
//        companyService.companyScrap(id);
//
//        return SuccessResponse.createSuccess(SuccessCode.CREATE_SCRAP_SUCCESS);
//    }

    // 관심 기업 목록 조회
//    @GetMapping("/{userId}")
//    public ResponseEntity<Object> companyScrap(@PathVariable("userId") Integer id) {
//        List<companyScrap> companyQuarterInfoResponseList = companyService.findCompanyQuarterInfo(id);
//
//        Users user = userRepository.findById(userId)
//                .orElseThrow(UserNotFoundException::new);
//
//        List<CompanyScrap> companyScrapList = companyScrapRepository.findByUser(user);
//        return ResponseEntity.ok(companyScrapList);
//    }
//
//    // 관심 기업 추가
//    @PostMapping
//    public ResponseEntity<Object> addCompanyScrap(
//            @RequestParam("companyId") Integer companyId,
//            @RequestParam("userId") Integer userId) {
//        Company company = companyRepository.findById(companyId)
//                .orElseThrow(CompanyNotFoundException::new);
//        Users user = userRepository.findById(userId)
//                .orElseThrow(UserNotFoundException::new);
//
//        CompanyScrap companyScrap = companyService.companyScrap(company, user);
//        return new ResponseEntity<>(companyScrap, HttpStatus.CREATED);
//    }
//
//    // 관심 기업 삭제
//    @DeleteMapping("/{companyScrapId}")
//    public ResponseEntity<Void> deleteCompanyScrap(@PathVariable("companyScrapId") Integer companyScrapId) {
//        companyScrapRepository.deleteById(companyScrapId);
//        return ResponseEntity.noContent().build();
//    }


}