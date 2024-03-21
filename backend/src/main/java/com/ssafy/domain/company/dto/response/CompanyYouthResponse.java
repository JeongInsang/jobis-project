package com.ssafy.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyYouthResponse {

    private Long youthCompanyId;

    private boolean flexibleWorkArrangement;

    private boolean nineToSix;

    private boolean refreshHoliday;

    private boolean supportChild;

    private boolean supportSomethingElse;

    private boolean cafeteria;

    private boolean dormCommuteBus;

    private boolean businessLounge;

    private boolean gym;

    private boolean childcareFacility;

    private boolean supportSelfDevelopment;

    private boolean supportCultureLife;

    private boolean supportEducation;

    private boolean companyClub;

    private boolean goToSchoolLater;

    private Long salary;

    private float employmentRate;

    private float youthRate;

    private float youthHireRate;

    private float youthHireIncreasingRate;

    private float youthHireIncreasingCnt;

}
