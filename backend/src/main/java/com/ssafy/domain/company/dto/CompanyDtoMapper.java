package com.ssafy.domain.company.dto;

import com.ssafy.domain.company.dto.response.CompanyResponse;
import com.ssafy.domain.company.entity.Company;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyDtoMapper {

    public static CompanyResponse companyEntityToDto(final Company company) {
        return CompanyResponse.builder()
                .companyId(company.getId())
                .name(company.getName())
                .foundAt(company.getFoundAt())
                .owner(company.getOwner())
                .form(company.getForm())
                .capital(company.getCapital())
                .employeeCnt(company.getEmployeeCnt())
                .type(company.getType())
                .productName(company.getProductName())
                .address(company.getAddress())
                .factor(company.getFactor())
                .build();
    }

    public static List<CompanyResponse> companyEntityListToDtoList(final List<Company> companyList) {
        return companyList.stream()
                .map(CompanyDtoMapper::companyEntityToDto)
                .collect(Collectors.toList());
    }

}
