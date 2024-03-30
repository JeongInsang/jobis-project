package com.ssafy.domain.company.dto.response;

import com.ssafy.global.entity.BaseTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyScrapResponse extends BaseTime {

    private Integer scrapId;

    private Integer usersId;

    private Integer companyId;

}
