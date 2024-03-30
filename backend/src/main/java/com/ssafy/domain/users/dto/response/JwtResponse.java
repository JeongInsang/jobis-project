package com.ssafy.domain.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(name = "JWT 토큰 응답 모델")
@Getter
@Builder
@AllArgsConstructor
public class JwtResponse {

    String type;
    String accessToken;

}
