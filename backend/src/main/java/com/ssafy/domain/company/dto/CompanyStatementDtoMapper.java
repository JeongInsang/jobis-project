package com.ssafy.domain.company.dto;

import com.ssafy.domain.company.dto.response.CompanyRateResponse;
import com.ssafy.domain.company.dto.response.CompanyStatementResponse;
import com.ssafy.domain.company.entity.CompanyRate;
import com.ssafy.domain.company.entity.CompanyStatement;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyStatementDtoMapper {
    public static CompanyStatementResponse companyStatementEntityToDto(final CompanyStatement companyStatement){
        return CompanyStatementResponse.builder()
                .financialStatementId(companyStatement.getId())
                .companyId(companyStatement.getCompany().getId())
                .assetValueType(companyStatement.getAssetValueType())
                .firstQuarter(companyStatement.getFirstQuarter())
                .secondQuarter(companyStatement.getSecondQuarter())
                .thirdQuarter(companyStatement.getThirdQuarter())
                .build();
    }

    public static List<CompanyStatementResponse> companyStatementEntityToDtoList(final List<CompanyStatement> companyStatementList){
        return companyStatementList.stream()
                .map(CompanyStatementDtoMapper::companyStatementEntityToDto)
                .collect(Collectors.toList());
    }
}
