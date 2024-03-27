package com.ssafy.domain.company.service;

import com.ssafy.domain.company.dto.CompanyDtoMapper;
import com.ssafy.domain.company.dto.CompanyQuarterInfoDtoMapper;
import com.ssafy.domain.company.dto.CompanyRateDtoMapper;
import com.ssafy.domain.company.dto.CompanyYouthDtoMapper;
import com.ssafy.domain.company.dto.response.CompanyQuarterInfoResponse;
import com.ssafy.domain.company.dto.response.CompanyRateResponse;
import com.ssafy.domain.company.dto.response.CompanyResponse;
import com.ssafy.domain.company.dto.response.CompanyYouthResponse;
import com.ssafy.domain.company.entity.Company;
import com.ssafy.domain.company.entity.CompanyQuarterInfo;
import com.ssafy.domain.company.entity.CompanyRate;
import com.ssafy.domain.company.entity.CompanyYouth;
import com.ssafy.domain.company.repository.CompanyQuarterInfoRepository;
import com.ssafy.domain.company.repository.CompanyRateRepository;
import com.ssafy.domain.company.repository.CompanyRepository;
import com.ssafy.domain.company.repository.CompanyYouthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyYouthRepository companyYouthRepository;
    private final CompanyRateRepository companyRateRepository;
    private final CompanyQuarterInfoRepository companyQuarterInfoRepository;

    // 기업 기본정보 조회
    public List<CompanyResponse> findCompany(Integer id) {
        List<Company> company = companyRepository.findCompanyById(id);
        return CompanyDtoMapper.companyEntityToDtoList(company);
    }


    // 기업 재무비율 조회
    public List<CompanyRateResponse> findCompanyRate(Integer id){
        List<CompanyRate> companyRate = companyRateRepository.findCompanyRateById(id);
        return CompanyRateDtoMapper.companyRateEntityToDtoList(companyRate);
    }



    // 기업 청년친화 조회
    public List<CompanyYouthResponse> findCompanyYouth(Integer id){
        List<CompanyYouth> companyYouth = companyYouthRepository.findCompanyYouthById(id);
        return CompanyYouthDtoMapper.companyYouthEntityToDtoList(companyYouth);
    }


    // 기업 분기정보 조회
    public List<CompanyQuarterInfoResponse> findCompanyQuarterInfo(Integer id){
        List<CompanyQuarterInfo> companyQuarterInfo = companyQuarterInfoRepository.findCompanyQuarterInfoById(id);
        return CompanyQuarterInfoDtoMapper.companyQuarterInfoToDtoList(companyQuarterInfo);
    }
}
