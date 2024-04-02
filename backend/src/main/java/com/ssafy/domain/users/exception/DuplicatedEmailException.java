package com.ssafy.domain.users.exception;

import com.ssafy.global.response.code.ErrorCode;
import com.ssafy.global.response.code.ResponseCode;
import com.ssafy.global.response.exception.RestApiException;

public class DuplicatedEmailException extends RestApiException {

    public DuplicatedEmailException() {
        super(ErrorCode.DUPLICATED_EMAIL);
    }
}
