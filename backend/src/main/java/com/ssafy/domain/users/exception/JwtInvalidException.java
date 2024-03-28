package com.ssafy.domain.users.exception;

import com.ssafy.global.response.code.ErrorCode;
import com.ssafy.global.response.code.ResponseCode;
import com.ssafy.global.response.exception.RestApiException;

public class JwtInvalidException extends RestApiException {
    public JwtInvalidException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
