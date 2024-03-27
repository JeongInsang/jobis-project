package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.CompanyScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyScrapRepository extends JpaRepository<CompanyScrap, Integer> {

    // 관심기업목록

}