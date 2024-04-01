package com.ssafy.domain.users.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TendencyRequest {

    private double stability;

    private double profitability;

    private double activity;

    private double growth;

    private double efficiency;

}
