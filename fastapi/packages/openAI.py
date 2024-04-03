from dotenv import load_dotenv
import os

import requests
import ast

load_dotenv()

def generate_prompt_for_job_seekers(news_articles):
    article_texts = ""
    for article in news_articles:
        article_texts += f"Title: {article['title']}\nContent: {article['content'][0:5000]}\nDate: {article['date']}\n\n"

    prompt = (
        "기사들을 분석하여 구직자들에게 중요한 회사의 특징을 나타내는 키워드를 찾아내세요. 키워드는 회사의 비즈니스 방향, 기술력, 미래 비전, 기업 문화, 가치관, 산업 내 위치 등을 "
        "반영해야 합니다. '시장', '회사', '팀'과 같이 너무 일반적인 용어나 회사 이름을 제외한, 구체적이고 직관적인 키워드 10개를 선정하세요. 각 키워드는 13글자를 넘지 않는 범위에서 "
        "선정하고, \n\n"
        "각 키워드에 대해 구직자에게의 중요도를 0에서 10까지의 척도로 평가하며, 키워드가 회사에 대한 긍정적(True) 또는 부정적(False)인 시각을 나타내는지 분석하세요. "
        "이 키워드들을 바탕으로 회사의 현재 상황과 성장 전망, 구직자에게 왜 매력적인 고용주인지에 대한 한국어로 된 상세한 요약을 제공해주세요. "
        "\n\n"
        "분석 결과는 다음과 같이 정확하게 구성되어야 합니다:\n"
        "{\n"
        "'keywords': [\n"
        "{ 'keyword': '구체적인 키워드', 'importance': 숫자 값, 'sentiment': True/False },\n"
        "...],\n"
        "'summary': '키워드로부터 얻은 통찰을 바탕으로 한 회사의 현재 상황과 성장 전망에 대한 한국어 요약.'\n"
        "}\n\n"
        "다음 기사들에서 제공된 세부 사항을 기반으로 분석하세요:\n\n"
        f"{article_texts}"
    )

    return prompt

def extract_keywords_from_result(analysis_result):
    content = analysis_result['choices'][0]['message']['content']

    try:
        # 문자열의 파이썬 표현식으로부터 안전하게 객체를 생성합니다.
        content_obj = ast.literal_eval(content)
    except Exception as e:
        print(f"Error parsing content: {e}")
        return None

    # 'keywords' 정보를 추출하여 반환합니다.
    keywords = content_obj.get('keywords', [])
    return keywords

def call_openapi(news_articles):
    if(news_articles == []):
        return {}
    api_key = os.getenv('API_KEY')
    prompt = generate_prompt_for_job_seekers(news_articles)

    headers = {
        'Authorization': f'Bearer {api_key}',
        'Content-Type': 'application/json'
    }
    payload = {
        "model": "gpt-3.5-turbo",
        "messages": [
            {"role": "system", "content": "You are a helpful assistant."},
            {"role": "user", "content": prompt}
        ]
    }

    response = requests.post('https://api.openai.com/v1/chat/completions', headers=headers, json=payload)
    analysis_result = response.json()

    result = {
        'news': news_articles,
        'keywords': extract_keywords_from_result(analysis_result)
    }

    return result
