package com.ssafy.domain.news.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewsResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long newsId;

    private String title;

    private String content;

    private String date;

    private String link;

}
