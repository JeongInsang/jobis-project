package com.ssafy.domain.company.dto.response;

import com.ssafy.domain.common.embeddable.Factor;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponse {

    private Integer companyId;

    private String name;

    private String foundAt;

    private String owner;

    private String form;

    private Long capital;

    private int employeeCnt;

    private String type;

    private String productName;

    private String address;

    private Factor factor;

    private boolean youthCompany;

    private Long views;

    private boolean isScraped;

}
