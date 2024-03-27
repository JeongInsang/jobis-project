package com.ssafy.domain.news.dto;

import org.springframework.data.redis.core.RedisHash;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@RedisHash("NewsData")
@Data
public class NewsData {

    private List<Article> news;

    private List<Keyword> keywords;

    @Data
    @AllArgsConstructor
    public static class Article {
        private String title;
        private String content;
        private String date;
        private String link;
    }

    @Data
    @AllArgsConstructor
    public static class Keyword {
        private String keyword;
        private int importance;
        private boolean sentiment;
    }

}
