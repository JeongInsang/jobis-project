package com.ssafy.domain.company.dto.response;

import com.ssafy.domain.company.entity.AssetRateType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyRateResponse {

    private Integer financialRateId;

    private Integer companyId;

    private AssetRateType assetRateType;

    private float firstQuarter;

    private float secondQuarter;

    private float thirdQuarter;

}
