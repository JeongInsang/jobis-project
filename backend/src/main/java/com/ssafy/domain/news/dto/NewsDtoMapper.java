package com.ssafy.domain.news.dto;

import com.ssafy.domain.news.dto.response.NewsResponse;
import com.ssafy.domain.news.entity.News;

import java.util.List;
import java.util.stream.Collectors;

public class NewsDtoMapper {

    public static NewsResponse newsEntityToDto(final News news) {
        return NewsResponse.builder()
                .newsId(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .date(news.getDate())
                .link(news.getLink())
                .build();
    }

    public static List<NewsResponse> newsEntityToDtoList(final List<News> newsList) {
        return newsList.stream()
                .map(NewsDtoMapper::newsEntityToDto)
                .collect(Collectors.toList());
    }

}
