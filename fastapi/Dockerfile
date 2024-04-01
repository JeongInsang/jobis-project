FROM python:3.9

# 작업 디렉토리 설정
WORKDIR /app

# requirements.txt 파일을 컨테이너에 복사
COPY requirements.txt .

# requirements.txt에 명시된 패키지 설치
RUN pip install --no-cache-dir -r requirements.txt

# 현재 디렉토리의 모든 파일을 컨테이너의 작업 디렉토리로 복사
COPY . .

# Uvicorn을 사용하여 호스트 0.0.0.0, 포트 8000에서 FastAPI 애플리케이션 실행
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]
