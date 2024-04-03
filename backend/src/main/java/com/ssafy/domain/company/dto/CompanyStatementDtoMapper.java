package com.ssafy.domain.company.dto;

import com.ssafy.domain.company.dto.response.CompanyStatementResponse;
import com.ssafy.domain.company.entity.AssetValueType;
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
                // 필터링을 통해 assetValueType이 특정 값들을 제외한 항목들만 선택
                .filter(statement -> !shouldExcludeAssetValueType(statement.getAssetValueType()))
                .map(CompanyStatementDtoMapper::companyStatementEntityToDto)
                .collect(Collectors.toList());
    }

    // assetValueType이 특정 값들을 제외할지 여부를 결정하는 메서드
    private static boolean shouldExcludeAssetValueType(AssetValueType assetValueType) {
        // 제외할 값들을 여기에 추가
        return assetValueType == AssetValueType.ADDITIONAL_PAID_IN_CAPITAL ||
                assetValueType == AssetValueType.RETAINED_EARNINGS ||
                assetValueType == AssetValueType.CAPITAL_ADJUSTMENTS;
    }
}