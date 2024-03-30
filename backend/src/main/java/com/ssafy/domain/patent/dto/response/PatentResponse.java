package com.ssafy.domain.patent.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PatentResponse {

    private Long patentId;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int companyId;

    private String patentName;

    private String patentDate;

    private String patentSummary;

}
