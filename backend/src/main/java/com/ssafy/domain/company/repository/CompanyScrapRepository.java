package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.Company;
import com.ssafy.domain.company.entity.CompanyScrap;
import com.ssafy.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyScrapRepository extends JpaRepository<CompanyScrap, Integer> {

    // 관심기업목록 조회
    List<CompanyScrap> findAllByUser(Users user);

    // 관심기업목록 취소
    void deleteCompanyScrapByCompanyAndUser(Company company, Users user);

    boolean existsByCompanyAndUser(Company company, Users user);

}