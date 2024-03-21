package com.ssafy.domain.news.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewsResponse {

    private Long newsId;

    private String title;

    private String content;

    private String date;

    private String link;

}
