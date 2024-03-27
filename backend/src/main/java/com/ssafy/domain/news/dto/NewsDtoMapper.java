package com.ssafy.domain.news.dto;

import com.ssafy.domain.news.dto.response.NewsResponse;
import com.ssafy.domain.news.entity.NewsRedis;

import java.util.List;
import java.util.stream.Collectors;

public class NewsDtoMapper {

    public static NewsResponse newsRedisToDto(final NewsRedis newsRedis) {
        List<NewsResponse.Article> articles = newsRedis.getNews().stream()
                .map(article -> new NewsResponse.Article(article.getTitle(), article.getContent(), article.getDate(), article.getLink()))
                .collect(Collectors.toList());

        List<NewsResponse.Keyword> keywords = newsRedis.getKeywords().stream()
                .map(keyword -> new NewsResponse.Keyword(keyword.getKeyword(), keyword.getImportance(), keyword.isSentiment()))
                .collect(Collectors.toList());

        return NewsResponse.builder()
                .companyId(newsRedis.getId())
                .news(articles)
                .keywords(keywords)
                .build();
    }

}
