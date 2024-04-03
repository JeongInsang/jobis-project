package com.ssafy.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompanySearchResponse {

    private Integer companyId;

    private String name;

    private String address;

    private String type;

    private boolean isScraped;

}
