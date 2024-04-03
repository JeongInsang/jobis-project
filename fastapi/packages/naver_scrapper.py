import difflib
import requests
from bs4 import BeautifulSoup
import time
from datetime import datetime
import csv
import random
import os
import json
import re


def normalize_company_name(company_name):
    company_name = re.sub(r'\(.*?\)|주식회사|㈜|\(주\)|\s*Co\.?\s*Ltd\.?', '', company_name, flags=re.IGNORECASE)
    company_name = company_name.strip().strip('"').strip("'")
    return company_name


progress_file_path = 'progress.json'


# JSON 파일 읽기
def load_progress():
    if os.path.exists(progress_file_path):
        with open(progress_file_path, 'r') as file:
            return json.load(file)
    else:
        return {}


# JSON 파일 저장
def save_progress(progress):
    with open(progress_file_path, 'w') as file:
        json.dump(progress, file)


# 검색
def perform_news_search(normal_keyword, lastpage, max_articles):
    print(f'========={normal_keyword} 검색 중=========')
    base_url = os.getenv('BASE_URL')
    qkeyword = f'"{normal_keyword}"'

    founded_articles = 0
    flag = False
    news_articles = []
    start_time = time.time()

    for i in range(1, lastpage * 10, 10):
        if flag:
            break

        # 현재 시간과 시작 시간의 차이를 계산
        current_time = time.time()
        elapsed_time = current_time - start_time

        # 시간 제한 확인 (50초)
        if elapsed_time > 50:
            print("검색 시간이 50초를 초과했습니다.")
            return news_articles

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

        # print(soup)

        # 공지 텍스트 요소 가져오기
        notice_element = soup.find("h3", class_="title")

        # 공지 텍스트가 있는지 확인
        if notice_element:
            notice_text = notice_element.text.strip()
            if notice_text == "검색 서비스 이용이 제한되었습니다.":
                print("검색 벤")

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
                    if "entertain" in response.url or "sports" in response.url: continue
                    title, content, date = get_article_details(response, soup)

                    if normal_keyword not in title.text:
                        continue

                    skip_article = check_similarity(news_articles, title, content)

                    if skip_article:
                        continue

                    news_articles.append({
                        'title': title.text.strip(),
                        'content': content.text.strip(),
                        'date': date,
                        'url': url
                    })

                    founded_articles += 1
                    print(f'{founded_articles} 기사 찾음')

                    if founded_articles >= max_articles:
                        print(f"최대 {max_articles} 개의 기사 찾음. 종료.")
                        flag = True
                        break

                except Exception as e:
                    print(f"에러 발생: {e}")

        # 랜덤 대기시간 주기
        random_sleep = random.uniform(0.5, 1.5)
        time.sleep(random_sleep)

    return news_articles


# 기사 정보 수집
def get_article_details(response, soup):
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
            print(f"=========pass=============")
            skip_article = True
            break

    return skip_article


# 유사도검사
def calculate_similarity(str1, str2):
    str1_bytes = bytes(str1, 'utf-8')
    str2_bytes = bytes(str2, 'utf-8')
    str1_bytes_list = list(str1_bytes)
    str2_bytes_list = list(str2_bytes)

    sm = difflib.SequenceMatcher(None, str1_bytes_list, str2_bytes_list)
    similarity = sm.ratio()

    return similarity


if __name__ == '__main__':
    # 현재 날짜
    today_date = datetime.today().strftime('%Y-%m-%d')

    # 결과를 저장할 CSV 파일 경로
    csv_file_path = f'crawl_results_{today_date}.csv'

    # 이미 오늘의 파일이 있는지 확인
    if os.path.isfile(csv_file_path):
        # 이미 오늘의 파일이 있으면 파일을 읽어와서 기존 데이터를 가져옴
        with open(csv_file_path, newline='', encoding='utf-8') as csvfile:
            reader = csv.DictReader(csvfile)
            existing_data = list(reader)
    else:
        existing_data = []

    # keyword = input()
    lastpage = 15
    MAX_ARTICLES = 3

    # 이전에 저장된 진행 상황 불러오기
    progress = load_progress() or {'keyword_index': 0}

    with open('company_list.csv', newline='', encoding='utf-8') as csvfile:
        spamreader = csv.reader(csvfile)

        # 이전에 진행한 키워드부터 시작
        for idx, row in enumerate(spamreader):
            if idx < progress['keyword_index']:
                continue
            # 현재 키워드 인덱스 저장
            progress['keyword_index'] = idx

            # 진행 상황 저장
            save_progress(progress)

            keyword = row[0]
            address = row[1]
            print(address)
            normal_keyword = normalize_company_name(keyword)

            print(f'{keyword}, {normal_keyword}')

            start_time = time.time()
            news_results = perform_news_search(normal_keyword, address, keyword, lastpage, MAX_ARTICLES)
            end_time = time.time()

            # 탐색시간 측정
            print(end_time - start_time)

            # 결과를 CSV 파일에 추가
            with open(csv_file_path, 'a', newline='', encoding='utf-8-sig') as csvfile:
                fieldnames = ['company', 'address', 'title', 'content', 'date', 'url']
                writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

                for article in news_results:
                    writer.writerow(article)

            # 랜덤 대기시간 주기
            random_sleep = random.uniform(0.5, 1.5)
            time.sleep(random_sleep)
