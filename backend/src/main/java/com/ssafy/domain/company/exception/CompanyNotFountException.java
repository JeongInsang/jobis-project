package com.ssafy.domain.company.exception;

import com.ssafy.global.response.code.ErrorCode;
import com.ssafy.global.response.exception.RestApiException;

public class CompanyNotFountException extends RestApiException {
    public CompanyNotFountException() {
        super(ErrorCode.COMPANY_NOT_FOUND);
    }
}
