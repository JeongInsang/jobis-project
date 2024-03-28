package com.ssafy.domain.news.repository;

import com.ssafy.domain.news.entity.News;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT * FROM news ORDER BY news_id DESC LIMIT 3", nativeQuery = true)
    List<News> findNewsByCompanyName(String name);

}
