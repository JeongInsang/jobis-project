package com.ssafy.domain.company.exception;

import com.ssafy.global.response.code.ErrorCode;
import com.ssafy.global.response.exception.RestApiException;

public class CompanyNotFoundException extends RestApiException {
    public CompanyNotFoundException() {
        super(ErrorCode.COMPANY_NOT_FOUND);
    }
}
