package com.ssafy.domain.company.dto;

import com.ssafy.domain.company.dto.response.CompanyQuarterInfoResponse;
import com.ssafy.domain.company.entity.CompanyQuarterInfo;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyQuarterInfoDtoMapper {

    public static CompanyQuarterInfoResponse companyQuarterInfoEntityToDto(final CompanyQuarterInfo companyQuarterInfo){
        return CompanyQuarterInfoResponse.builder()
                .quarterInfoId(companyQuarterInfo.getId())
                .companyId(companyQuarterInfo.getCompany().getId())
                .firstQuarterDate(companyQuarterInfo.getFirstQuarterDate())
                .secondQuarterDate(companyQuarterInfo.getSecondQuarterDate())
                .thirdQuarterDate(companyQuarterInfo.getThirdQuarterDate())
                .build();

    }

    public static List<CompanyQuarterInfoResponse> companyQuarterInfoToDtoList(final List<CompanyQuarterInfo> companyQuarterInfoList){
        return companyQuarterInfoList.stream()
                .map(CompanyQuarterInfoDtoMapper::companyQuarterInfoEntityToDto)
                .collect(Collectors.toList());
    }
}
