package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.Company;
import com.ssafy.domain.company.entity.CompanyYouth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyYouthRepository extends JpaRepository<CompanyYouth, Integer> {

    // 기업 청년친화 조회
    List<CompanyYouth> findAllByCompany(Company company);
}
