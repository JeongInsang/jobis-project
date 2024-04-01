package com.ssafy.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanySearchResponse {

    private String name;

    private String address;

    private String type;

}
