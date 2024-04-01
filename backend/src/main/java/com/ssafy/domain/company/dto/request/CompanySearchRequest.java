package com.ssafy.domain.company.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompanySearchRequest {

    private String keyword;

    private List<String> location;

    private List<String> type;


}
