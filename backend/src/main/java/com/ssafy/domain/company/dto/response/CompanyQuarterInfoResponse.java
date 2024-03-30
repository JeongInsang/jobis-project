package com.ssafy.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyQuarterInfoResponse {

    private Integer quarterInfoId;

    private Integer companyId;

    private String firstQuarterDate;

    private String secoundQuarterDate;

    private String thirdQuarterDate;

}
