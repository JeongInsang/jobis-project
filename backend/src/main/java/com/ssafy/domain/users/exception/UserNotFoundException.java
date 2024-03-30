package com.ssafy.domain.users.exception;

import com.ssafy.global.response.code.ErrorCode;
import com.ssafy.global.response.code.ResponseCode;
import com.ssafy.global.response.exception.RestApiException;

public class UserNotFoundException extends RestApiException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
