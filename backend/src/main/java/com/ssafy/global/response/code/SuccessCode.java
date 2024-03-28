package com.ssafy.global.response.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements ResponseCode {

    /**
     * User
     */
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),

    LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃에 성공했습니다."),

    CHECK_EMAIL_GOOD(HttpStatus.OK, "사용 가능한 이메일입니다"),
    CHECK_EMAIL_BAD(HttpStatus.OK, "이미 사용 중인 이메일입니다"),

    JOIN_SUCCESS(HttpStatus.CREATED, "회원가입에 성공했습니다."),

    CHECK_PASSWORD_SUCCESS(HttpStatus.OK, "비밀번호 확인에 성공했습니다."),

    PASSWORD_UPDATE_SUCCESS(HttpStatus.OK, "비밀번호 변경에 성공했습니다."),

    FIND_SCRAP_SUCCESS(HttpStatus.OK, "기업 스크랩 목록 조회에 성공했습니다."),

    APPLY_TENDENCY_SUCCESS(HttpStatus.OK, "사용자성향 정보 입력에 성공했습니다."),

    FIND_TENDENCY_SUCCESS(HttpStatus.OK, "사용자성향 정보 조회에 성공했습니다."),

    FIND_ID_SUCCESS(HttpStatus.OK, "아이디 찾기에 성공했습니다."),

    FIND_PASSWORD_SUCCESS(HttpStatus.OK, "패스워드 찾기에 성공했습니다."),

    DELETE_USER_SUCCESS(HttpStatus.OK, "회원탈퇴에 성공했습니다."),

    EMAIL_SUCCESS(HttpStatus.OK, "인증번호 발송에 성공했습니다."),

    USER_BLOCK_SUCCESS(HttpStatus.OK, "유저 정지에 성공했습니다."),

    USER_DETAIL_SUCCESS(HttpStatus.OK, "회원정보 조회에 성공했습니다."),

    /**
     * Company
     */
    SEARCH_COMPANY_SUCCESS(HttpStatus.OK, "기업검색 목록 조회에 성공했습니다."),

    FIND_BASE_SUCCESS(HttpStatus.OK, "기업기본 정보 조회에 성공했습니다."),

    FIND_STATEMENT_SUCCESS(HttpStatus.OK, "기업재무재표 정보 조회에 성공했습니다."),

    FIND_RATE_SUCCESS(HttpStatus.OK, "기업재무비율 정보 조회에 성공했습니다."),

    FIND_COMPANYYOUTH_SUCCESS(HttpStatus.OK, "청년친화기업 정보 조회에 성공했습니다."),

    FIND_COMPANYQUARTERINFO_SUCCESS(HttpStatus.OK, "기업분기 정보 조회에 성공했습니다."),

    CREATE_SCRAP_SUCCESS(HttpStatus.OK, "관심기업 저장에 성공했습니다."),

    CANCEL_SCRAP_SUCCESS(HttpStatus.OK, "관심기업 취소에 성공했습니다."),

    /**
     * News
     */
    FIND_NEWS_SUCCESS(HttpStatus.OK, "기업관련 최신 뉴스 조회에 성공했습니다."),

    /**
     * Patent
     */
    FIND_PATENT_SUCCESS(HttpStatus.OK, "기업특허 목록 조회에 성공했습니다."),

    /**
     * Worldcup
     */
    FIND_WORLDCUP_SUCCESS(HttpStatus.OK, "가상기업 목록 조회에 성공했습니다."),

    FIND_RECOMMENDATION_SUCCESS(HttpStatus.OK, "추천기업 목록 조회에 성공했습니다."),

    /**
     * Token
     */
    UPDATE_TOKEN_SUCCESS(HttpStatus.OK, "토큰 재발급에 성공했습니다."),
    ;

    private final HttpStatus httpStatus;

    private final String message;

}
