package com.ssafy.domain.worldcup.repository;

import com.ssafy.domain.worldcup.entity.Worldcup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorldcupRepository extends JpaRepository<Worldcup, Long> {

    @Query(value = "SELECT * FROM worldcup ORDER BY RAND() LIMIT 16", nativeQuery = true)
    List<Worldcup> findRandomWorldcups();

}
