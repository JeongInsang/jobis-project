package com.ssafy.domain.company.dto;

import com.ssafy.domain.company.dto.response.CompanyRateResponse;
import com.ssafy.domain.company.entity.CompanyRate;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyRateDtoMapper {
    public static CompanyRateResponse companyRateResponse(final CompanyRate companyRate){
        return CompanyRateResponse.builder()
                .financialRateId(companyRate.getId())
                .companyId(companyRate.getCompany().getId())
                .assetRateType(companyRate.getAssetRateType())
                .firstQuarter(companyRate.getFirstQuarter())
                .          secondQuarter(companyRate.getSecondQuarter())
                .thirdQuarter(companyRate.getThirdQuarter())
                .build();
    }

    public static List<CompanyRateResponse> companyRateEntityToDtoList(final List<CompanyRate> companyRateList){
        return companyRateList.stream()
                .map(CompanyRateDtoMapper::companyRateResponse)
                .collect(Collectors.toList());
    }
}
