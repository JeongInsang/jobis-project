package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.Company;
import com.ssafy.domain.company.entity.CompanyStatement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyStatementRepository extends JpaRepository<CompanyStatement, Integer> {

    // 기업 재무재표 조회
    List<CompanyStatement> findAllByCompany(Company company);
}
