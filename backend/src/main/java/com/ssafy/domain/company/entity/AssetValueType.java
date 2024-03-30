package com.ssafy.domain.company.entity;

/**
 * 유동자산
 * 비유동자산
 * 자산총계
 * 유동부채
 * 비유동부채
 * 부채총계
 * 자본금
 * 자본금잉여금
 * 이익잉여금
 * 자본조정
 * 자본총계
 * 매출액
 * 매출총이익
 * 영업이익
 * 영업외수익
 * 영업외비용
 * 당기순이익
 * 현금흐름표
 * 영업활동으로인한 현금흐름
 * 투자활동으로 인한 현금흐름
 * 재무활동으로 인한 현금흐름
 * 현금의 증가감소
 * 기말현금
 */

public enum AssetValueType {

    CURRENT_ASSETS,
    NON_CURRENT_ASSETS,
    TOTAL_ASSETS,
    CURRENT_LIABILITIES,
    NON_CURRENT_LIABILITIES,
    TOTAL_LIABILITIES,
    CAPITAL_STOCK,
    ADDITIONAL_PAID_IN_CAPITAL,
    RETAINED_EARNINGS,
    CAPITAL_ADJUSTMENTS,
    TOTAL_EQUITY,
    SALES_REVENUE,
    GROSS_PROFIT,
    OPERATING_INCOME,
    NON_OPERATING_INCOME,
    NON_OPERATING_EXPENSES,
    NET_INCOME,
    STATEMENT_OF_CASH_FLOWS,
    CASH_FLOWS_FROM_OPERATING_ACTIVITIES,
    CASH_FLOWS_FROM_INVESTING_ACTIVITIES,
    CASH_FLOWS_FROM_FINANCING_ACTIVITIES,
    NET_INCREASE_DECREASE_IN_CASH,
    CASH_AT_END_OF_PERIOD

}
