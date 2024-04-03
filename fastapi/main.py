from packages.naver_scrapper import perform_news_search
from packages.openAI import call_openapi

from typing import Optional

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

import logging
import time
import re

logger = logging.getLogger(__name__)
logging.basicConfig(filename='news.log', level=logging.INFO)

app = FastAPI()

# CORS 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# scrapper 설정
lastpage = 15
MAX_ARTICLES = 3

@app.get('/news/{name}')
def call_perform_news_search(name: Optional[str] = None):
    logger.info("Started At: " + time.strftime('%c', time.localtime(time.time())))
    logger.info(f"Company name: {name}")

    result = {}

    try:
        result = call_openapi(perform_news_search(normalize_company_name(name), lastpage, MAX_ARTICLES))
        logger.info("Finished At: " + time.strftime('%c', time.localtime(time.time())))

    except Exception as e:
        logger.info(f"({time.localtime(time.time())})ERROR: {str(e)}")

    return result

def normalize_company_name(company_name):
    company_name = re.sub(r'\(.*?\)|주식회사|㈜|\(주\)|\s*Co\.?\s*Ltd\.?', '', company_name, flags=re.IGNORECASE)
    company_name = company_name.strip().strip('"').strip("'")
    return company_name

if __name__ == '__main__':
    call_perform_news_search()
