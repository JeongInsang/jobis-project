package com.ssafy.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CompanyScrapResponse{

    private Integer scrapId;

    private Integer usersId;

    private Integer companyId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
