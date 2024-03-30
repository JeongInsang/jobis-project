package com.ssafy.domain.company.dto;

import com.ssafy.domain.company.dto.response.CompanyScrapResponse;
import com.ssafy.domain.company.entity.CompanyScrap;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyScrapDtoMapper {

    public static CompanyScrapResponse companyScrapEntityToDto (final CompanyScrap companyScrap){
        return CompanyScrapResponse.builder()
                .scrapId(companyScrap.getId())
                .usersId(companyScrap.getUser().getId())
                .companyId(companyScrap.getCompany().getId())
                .build();
    }


    public static List<CompanyScrapResponse> companyScrapResponseList(final List<CompanyScrap> companyScrapList){
        return companyScrapList.stream()
                .map(CompanyScrapDtoMapper::companyScrapEntityToDto)
                .collect(Collectors.toList());
    }

}
