package com.ssafy.domain.recommendation.repository;

import com.ssafy.domain.company.entity.Company;
import com.ssafy.domain.recommendation.entity.Recommendation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {

    @Query("SELECT r.company FROM Recommendation r WHERE r.worldcup.id = :id")
    List<Company> findCompanyByWorldcupId(Integer id);

}
