package com.ssafy.domain.news.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class NewsRequest {

    private Long newsId;

    private Long companyId;

    private String title;

    private String content;

    private String date;

    private String link;

}
