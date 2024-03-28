from dotenv import load_dotenv
import os

import difflib
import requests
from bs4 import BeautifulSoup
from datetime import datetime

load_dotenv()

# 유사도검사
def calculate_similarity(str1, str2):
    str1_bytes = bytes(str1, 'utf-8')
    str2_bytes = bytes(str2, 'utf-8')
    str1_bytes_list = list(str1_bytes)
    str2_bytes_list = list(str2_bytes)

    sm = difflib.SequenceMatcher(None, str1_bytes_list, str2_bytes_list)
    similarity = sm.ratio()

    return similarity

# 검색
def perform_news_search(keyword, lastpage, max_articles):
    base_url = os.getenv('BASE_URL')
    qkeyword = f'"{keyword}"'

    founded_articles = 0
    flag = False
    news_articles = []

    for i in range(1, lastpage * 10, 10):
        if flag:
            break

        params = {
            'where': 'news',
            'sm': 'tab_jum',
            'query': qkeyword,
            'start': i,
            'nso': 'so:r,p:1y',
            'sort': 1
        }

        query_string = "&".join([f"{key}={value}" for key, value in params.items()])

        response = requests.get(f'{base_url}?{query_string}')

        html = response.text
        soup = BeautifulSoup(html, "html.parser")

        if soup.select_one(".api_noresult_wrap"):
            print("검색결과 없음")
            break

        articles = soup.select("div.info_group")

        for article in articles:
            links = article.select("a.info")
            if len(links) >= 2:
                url = links[1].attrs["href"]
                response = requests.get(url, headers={'User-agent': 'Mozilla/5.0'})
                html = response.text
                soup = BeautifulSoup(html, "html.parser")

                try:
                    title, content, date = get_article_details(response, soup)

                    if keyword not in title.text:
                        continue

                    skip_article = check_similarity(news_articles, title, content)

                    if skip_article:
                        continue

                    news_articles.append({
                        'title': title.text.strip(),
                        'content': content.text.strip(),
                        'date': date,
                        'link': url
                    })

                    founded_articles += 1
                    print(f'{founded_articles} 기사 찾음')

                    if founded_articles >= max_articles:
                        print(f"최대 {max_articles} 개의 기사 찾음. 종료.")
                        flag = True
                        break

                except Exception as e:
                    print(f"에러 발생: {e}")

    return news_articles

# 기사 정보 수집
def get_article_details(response, soup):
    if "entertain" in response.url:
        title = soup.select_one(".end_tit")
        content = soup.select_one("#articeBody")

    elif "sports" in response.url:
        title = soup.select_one("h4.title")
        content = soup.select_one("#newsEndContents")
        date = datetime.strptime(soup.find('meta', {'property': 'og:update_time'}).get('content'), "%Y%m%d%H%M%S")

    else:
        title = soup.select_one(".media_end_head_headline")
        content = soup.select_one("#dic_area")
        date_element = soup.select_one(".media_end_head_info_datestamp_time")
        date = date_element.get('data-date-time') if date_element else None

    return title, content, date

# 비슷한 기사 스킵
def check_similarity(news_articles, title, content):
    skip_article = False
    for saved_article in news_articles:
        similarity_title = calculate_similarity(title.text.strip(), saved_article['title'])
        similarity_content = calculate_similarity(content.text.strip(), saved_article['content'])

        if similarity_title > 0.7 or similarity_content > 0.7:
            skip_article = True
            break

    return skip_article
