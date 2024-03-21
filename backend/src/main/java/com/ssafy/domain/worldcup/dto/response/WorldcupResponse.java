package com.ssafy.domain.worldcup.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WorldcupResponse {

    private Long worldcupId;

    private String name;

    private String description;

    private String img;

    private double stability;

    private double profitability;

    private double activity;

    private double growth;

    private double efficiency;

}
