//package com.ssafy.domain.company.repository.companySearch;
//
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.ssafy.domain.company.entity.Company;
//import com.ssafy.domain.company.entity.QCompany;
//import io.micrometer.common.util.StringUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.data.support.PageableExecutionUtils;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class CompanySearchRepositoryCustomImpl {
//
//    private final JPAQueryFactory jpaQueryFactory;
//
//    public List<Company> companySearch(CompanySearchCondit
//
//}
//
//
////    List<Company> company = queryFactory
////            .select(new Q)
//
//
//
//
//
////    public CompanySearchRepositoryCustomImpl() {
////        // ㅇㅇ
////        super(Company.class);
////    }
//
////    @Override
////    public Page<Company> findBySearchOption(Pageable pageable, String keyword, List<String> location, List<String> type) {
////        QCompany qCompany = QCompany.company;
////        BooleanBuilder builder = new BooleanBuilder();
////
////        if (!StringUtils.isEmpty(keyword)) {
////            builder.and(qCompany.name.contains(keyword));
////        }
////
////        if (location != null && !location.isEmpty()) {
////            BooleanExpression locationExpression = qCompany.address.in(location);
////            builder.and(locationExpression);
////        }
////
////        if (type != null && !type.isEmpty()) {
////            BooleanExpression typeExpression = qCompany.type.in(type);
////            builder.and(typeExpression);
////        }
////
////        return applyPagination(pageable, queryFactory -> queryFactory
////                .selectFrom(qCompany)
////                .where(builder)
////                .fetch());
////    }
////}
////public class CompanySearchRepositoryCustomImpl extends QuerydslRepositorySupport implements CompanySearchRepositoryCustom {
////
////    private JPAQueryFactory queryFactory;
////
////    public CompanySearchRepositoryCustomImpl() {
////        super(Company.class);
////    }
////
////    @Override
////    public Page<Company> findBySearchOption(Pageable pageable, String keyword, List<String> location, List<String> type) {
//////        QCompany qCompany = QCompany.company;
////        JPQLQuery<Company> query = queryFactory.selectFrom(company)
////                .where(eqName(keyword),
////                        eqAddress(location), eqType(type), containName(keyword));
////
////        List<Company> companies = this.getQuerydsl()
////                .applyPagination(pageable, query)
////                .fetch();  // 리스트를 받아온다
////
////        return new PageImpl<Company>(companies, pageable, query.fetchCount());
////    }
////
////
//////    private final JPAQueryFactory queryFactory;
//////
//////    public List<Company> searchCompanies(String keyword, String location, String type) {
//////        QCompany qCompany = QCompany.company;
//////        List<BooleanExpression> expressions = new ArrayList<>();
//////
//////        log.info("CompanyService 진입");
//////
//////        if (keyword != null) {
//////            expressions.add(qCompany.name.contains(keyword));
//////            log.info("keyword = {}", keyword);
//////        }
//////
//////        if (location != null) {
//////            expressions.add(qCompany.address.contains(location));
//////            log.info("location = {}", location);
//////        }
//////
//////        if (type != null) {
//////            expressions.add(qCompany.type.eq(type));
//////            log.info("type = {}", type);
//////        }
//////
//////        BooleanExpression finalExpression = expressions.stream()
//////                .reduce(BooleanExpression::and)
//////                .orElse(null);
//////
//////        log.info("BooleanExpression : {}", finalExpression);
//////        return queryFactory
//////                .selectFrom(qCompany)
//////                .where(finalExpression)
//////                .fetch();
//////    }
////
////
////
////
//////    private BooleanExpression eqAddress(List<String> location){
//////        if(location == null || location.isEmpty()) {
//////            return null;
//////        }
//////        return company.location.eq(address);
//////    }
////
////    private BooleanExpression eqName(String name) {
////        if(name == null || name.isEmpty()) {
////            return null;
////        }
////        return company.name.eq(name);
////    }
////
////
////    private List <BooleanExpression> eqAddress(List<String> location) {
////        if(location == null || location.isEmpty()) {
////            return null;
////        }
////        return company.address.eq(location);
////    }
////
////
////    private BooleanExpression eqType(List<String> type) {
////        if(type == null || type.isEmpty()) {
////            return null;
////        }
////        return company.type.eq(type);
////    }
////
////}
