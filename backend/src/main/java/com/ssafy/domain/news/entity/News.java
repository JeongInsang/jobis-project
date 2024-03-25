package com.ssafy.domain.news.entity;

import com.ssafy.domain.company.entity.Company;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @NotNull
    @Column(name = "date")
    private String date;

    @NotNull
    @Column(name = "link", columnDefinition = "TEXT")
    private String link;

    /**
     * 연관관계
     */
    private void setCompany(final Company company) {
        this.company = company;
    }

    @Builder
    public News(final Map<String, String> newsMap, final Company company) {
        setCompany(company);
        this.title = newsMap.get("title");
        this.content = newsMap.get("content");
        this.date = newsMap.get("date");
        this.link = newsMap.get("link");
    }

}
