package com.ssafy.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JwtDto {

    String accessToken;
    String refreshToken;

}
