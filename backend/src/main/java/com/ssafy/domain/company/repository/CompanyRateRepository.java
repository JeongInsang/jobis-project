package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.Company;
import com.ssafy.domain.company.entity.CompanyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRateRepository extends JpaRepository<CompanyRate, Integer> {

    // 기업 청년친화 조회
    List<CompanyRate> findAllByCompany(Company company);
}