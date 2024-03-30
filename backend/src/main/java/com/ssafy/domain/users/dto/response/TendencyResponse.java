package com.ssafy.domain.users.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TendencyResponse {

    private double stability;

    private double profitability;

    private double activity;

    private double growth;

    private double efficiency;
}
