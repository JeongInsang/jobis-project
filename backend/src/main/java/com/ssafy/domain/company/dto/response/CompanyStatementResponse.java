package com.ssafy.domain.company.dto.response;

import com.ssafy.domain.company.entity.AssetValueType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyStatementResponse {

    private Integer financialStatementId;

    private Integer companyId;

    private AssetValueType assetValueType;

    private Long firstQuarter;

    private Long secondQuarter;

    private Long thirdQuarter;

}
