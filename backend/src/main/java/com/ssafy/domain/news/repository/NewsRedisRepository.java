package com.ssafy.domain.news.repository;

import com.ssafy.domain.news.entity.NewsRedis;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NewsRedisRepository extends CrudRepository<NewsRedis, String> {

    Optional<NewsRedis> findById(String companyId);

}
