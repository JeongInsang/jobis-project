package com.ssafy.domain.company.repository;

import com.ssafy.domain.company.entity.CompanyQuarterInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyQuarterInfoRepository extends JpaRepository<CompanyQuarterInfo, Integer> {

    // 기업 청년친화 조회
    List<CompanyQuarterInfo> findCompanyQuarterInfoById(Integer id);
}