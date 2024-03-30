package com.ssafy.domain.company.repository.companySearch;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.domain.company.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.*;

public interface CompanySearchRepositoryCustom extends JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {
//    Page<Company> findBySearchOption(Pageable pageable, String keyword, List<String> location, List<String> type);
}
