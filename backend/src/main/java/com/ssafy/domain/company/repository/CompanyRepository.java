package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.Company;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // 상위기업 top10 조회
    List<Company> findByOrderByViewsDesc(Limit limit);
}
