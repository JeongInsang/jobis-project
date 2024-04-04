from datetime import datetime, timezone
import calendar
import re
import csv

# 현재 시간을 로그로 기록하는 함수
def log_time(log_file, prefix):
    # 현재 UTC 시간을 timezone-aware datetime 객체로 얻습니다.
    now_utc = datetime.now(timezone.utc)
    with open(log_file, 'a', encoding='utf-8') as log_f:
        # UTC 타임스탬프를 로그 파일에 기록합니다.
        log_f.write(f"{prefix} {now_utc.timestamp() * 1000}\n")

def split_company_address(patent_name):
    # 정규 표현식을 사용하여 기업명과 주소 분리
    match = re.match(r"^(.+?)(\((.+)\))?$", patent_name)
    if match:
        company_name = match.group(1).strip()  # 기업명 추출
        address = match.group(3) if match.group(3) else ""  # 주소 추출, 주소가 없다면 빈 문자열
        return company_name, address
    else:
        return patent_name, ""  # 매치되지 않는 경우, 주소 없이 기업명만 반환

def normalize_company_name(company_name):
    company_name = re.sub(r'\(.*?\)|주식회사|㈜|\(주\)|\s*Co\.?\s*Ltd\.?', '', company_name, flags=re.IGNORECASE)
    company_name = company_name.strip().strip('"').strip("'")
    return company_name

# 주요 작업
def match_companies_and_patents():
    start_time = datetime.now()
    log_file = "time_log.txt"
    input_file1 = 'company_normalize_list.csv'
    input_file2 = 'patent_normalize_list.csv'
    output_file = "matched_records.csv"

    # 시작 시간 로깅
    log_time(log_file, "StartTime")

    with open(input_file1, 'r', encoding='utf-8') as company_file, \
            open(input_file2, 'r', encoding='utf-8') as patent_file, \
            open(output_file, 'w', newline='', encoding='utf-8') as output_f:

        company_reader = csv.reader(company_file)
        patent_reader = csv.reader(patent_file)
        output_writer = csv.writer(output_f)

        # 기업 목록을 메모리에 로드
        companies = [row for row in company_reader]

        working_patent_count = 0

        for patent_row in patent_reader:
            try:
                isChecked = False
                parts = [patent_row[0], patent_row[1], patent_row[2]]
                patent_date = parts[0]
                patent_names = parts[1].split('|')  # '|'로 분리된 patent_name을 분리
                patent_summary = parts[2]

                # 분리된 patent_names에 대한 처리
                for patent_name in patent_names:
                    if isChecked:
                        break
                    company_name, company_address = split_company_address(patent_name)
                    company_name = normalize_company_name(company_name)

                    # 기업 목록 순회
                    for company_row in companies:
                        # 기업명과 주소가 출원인에 포함되어 있는지 확인
                        if (company_row[0] == company_name) and (company_row[1] in company_address):
                            # 매칭되는 경우, 정보를 출력 파일에 작성
                            output_writer.writerow([company_row[0], company_row[1], patent_date, patent_name, patent_summary])
                            # 매칭 시간 로깅
                            log_time(log_file, "MatchTime")
                            isChecked = True
                            break  # 매칭된 후 다음 patent_name으로 넘어갑니다.

            except Exception as e:
                print(f"Error processing row: {patent_row}")
                print(e)

            working_patent_count += 1
            if working_patent_count % 100 == 0:  # 진행 상태를 로그로 남깁니다.
                print(f"{working_patent_count} patents processed.")

    # 종료 시간 로깅
    log_time(log_file, "EndTime")

# 매칭 로직 실행
match_companies_and_patents()
