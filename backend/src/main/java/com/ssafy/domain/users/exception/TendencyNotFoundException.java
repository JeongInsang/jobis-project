package com.ssafy.domain.users.exception;

import com.ssafy.global.response.code.ErrorCode;
import com.ssafy.global.response.exception.RestApiException;

public class TendencyNotFoundException extends RestApiException {

    public TendencyNotFoundException(){
        super(ErrorCode.TENDENCY_NOT_FOUND);
    }
}
