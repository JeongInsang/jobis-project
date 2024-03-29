package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.Company;
import com.ssafy.domain.company.entity.CompanyScrap;
import com.ssafy.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyScrapRepository extends JpaRepository<CompanyScrap, Integer> {

    // 관심기업목록 취소
    void deleteCompanyScrapByUserAndCompany(Company company, Users user);

}