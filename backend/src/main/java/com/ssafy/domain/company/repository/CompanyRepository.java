package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, Integer> {


    // 기업 기본정보 조회
    List<Company> findCompanyById(Integer id);



}
