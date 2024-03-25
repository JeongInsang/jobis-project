package com.ssafy.domain.patent.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PatentResponse {

    private Long patentId;

    private int companyId;

    private String patentName;

    private String patentDate;

    private String patentSummary;

}
