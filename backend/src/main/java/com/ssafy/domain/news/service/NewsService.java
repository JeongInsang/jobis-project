package com.ssafy.domain.news.service;

import com.ssafy.domain.company.repository.CompanyRepositoryTest;    // 이 부분 고치기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
import com.ssafy.domain.news.dto.NewsDtoMapper;
import com.ssafy.domain.news.dto.response.NewsResponse;
import com.ssafy.domain.news.entity.News;
import com.ssafy.domain.news.repository.NewsRepository;

import com.ssafy.global.response.code.ErrorCode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class NewsService {

    private final NewsRepository newsRepository;
    private final CompanyRepositoryTest companyRepositoryTest;    // 이 부분 고치기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Transactional(readOnly = false)
    public List<NewsResponse> findNews(Long companyId, String name) {
        List<News> newsList = newsRepository.findNewsByCompanyName(name);

        if(newsList.isEmpty()) {
            log.info(name+"("+companyId+") 기사를 DB에서 찾을 수 없습니다.");

            List<Map<String, String>> getNews = get(name);

            if(getNews.isEmpty()) {
                throw new RuntimeException(ErrorCode.NEWS_NOT_FOUND.getMessage());
            }

            List<NewsResponse> getNewsList = new ArrayList<>();

            for (Map<String, String> getNew : getNews) {
                getNewsList.add(NewsResponse.builder()
                                .title(getNew.get("title"))
                                .content(getNew.get("content"))
                                .date(getNew.get("date"))
                                .link(getNew.get("link"))
                                .build());
                newsList.add(new News(getNew, companyRepositoryTest.findCompanyById(companyId))); //  이 부분 고치기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }

            newsRepository.saveAll(newsList);
            return getNewsList;
        }

        return NewsDtoMapper.newsEntityToDtoList(newsList);
    }

    private List<Map<String, String>> get(String name) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8000").build();

        List<Map<String, String>> response = webClient.get().uri("/news/{name}", name)
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Map<String, String>>>() {}).block();

        return response;
    }

    private void writeNews() {

    }

}
