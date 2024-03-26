package com.ssafy.domain.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "이메일은 필수 입력 항목입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다")
    private String password;

}
