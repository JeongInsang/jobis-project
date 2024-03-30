package com.ssafy.domain.company.dto;

import com.ssafy.domain.company.dto.response.CompanySearchResponse;
import com.ssafy.domain.company.entity.Company;

import java.util.List;
import java.util.stream.Collectors;

public class CompanySearchDtoMapper {

    public static CompanySearchResponse companySearchResponse(final Company company){
        return CompanySearchResponse.builder()
                .name(company.getName())
                .address(company.getAddress())
                .type(company.getType())
                .build();
    }

    public static List<CompanySearchResponse> companySearchResponseList(final List<Company> companyList){
        return companyList.stream()
                .map(CompanySearchDtoMapper::companySearchResponse)
                .collect(Collectors.toList());
    }
}
