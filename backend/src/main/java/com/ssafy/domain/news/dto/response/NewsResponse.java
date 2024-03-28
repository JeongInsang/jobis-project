package com.ssafy.domain.news.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class NewsResponse {

    private String companyId;

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
