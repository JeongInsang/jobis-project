package com.ssafy.domain.company.dto;

import com.ssafy.domain.company.dto.response.CompanyYouthResponse;
import com.ssafy.domain.company.entity.CompanyYouth;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyYouthDtoMapper {

    public static CompanyYouthResponse companyYouthEntityToDto(final CompanyYouth companyYouth){
        return CompanyYouthResponse.builder()
                .youthCompanyId(companyYouth.getId())
                .flexibleWorkArrangement(companyYouth.isFlexibleWorkArrangement())
                .nineToSix(companyYouth.isNineToSix())
                .refreshHoliday(companyYouth.isRefreshHoliday())
                .supportChild(companyYouth.isSupportChild())
                .supportSomethingElse(companyYouth.isSupportSomethingElse())
                .cafeteria(companyYouth.isCafeteria())
                .dormCommuteBus(companyYouth.isDormCommuteBus())
                .businessLounge(companyYouth.isBusinessLounge())
                .gym(companyYouth.isGym())
                .childcareFacility(companyYouth.isChildcareFacility())
                .supportSelfDevelopment(companyYouth.isSupportSelfDevelopment())
                .supportCultureLife(companyYouth.isSupportCultureLife())
                .supportEducation(companyYouth.isSupportEducation())
                .companyClub(companyYouth.isCompanyClub())
                .goToSchoolLater(companyYouth.isGoToSchoolLater())
                .salary(companyYouth.getSalary())
                .employmentRate(companyYouth.getEmploymentRate())
                .youthRate(companyYouth.getYouthRate())
                .youthHireRate(companyYouth.getYouthHireRate())
                .youthHireIncreasingRate(companyYouth.getYouthHireIncreasingRate())
                .youthHireIncreasingCnt(companyYouth.getYouthHireIncreasingCnt())
                .build();
    }


    public static List<CompanyYouthResponse> companyYouthEntityToDtoList(final List<CompanyYouth> companyYouthList){
        return companyYouthList.stream()
        .map(CompanyYouthDtoMapper::companyYouthEntityToDto)
                .collect(Collectors.toList());
    }

}
