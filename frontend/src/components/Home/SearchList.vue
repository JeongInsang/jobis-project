<template>
    <div>
        <div class="section">
            <div class="container">
                <div class="hot-top-10">🔍기업 필터 리스트</div>
                <!-- 기업 리스트 자리 -->
            </div>
        </div>
        <div class="div">
            <div class="row" v-for="company in dummyData" :key="company.id">
                <div class="div2">
                    <div class="text">{{ company.name }}</div>
                </div>
                <div class="div2">
                    <div class="text2">{{ company.industrial }}</div>
                </div>
                <div class="div2">
                    <div class="text2">{{ company.detailRole }}</div>
                </div>
                <div class="div2">
                    <div class="frame-1">
                        <div class="div3">{{ company.address }}</div>
                    </div>
                </div>
            </div>
        </div>

        <ul>
            <li v-for="result in results" :key="result.id">{{ result.name }}</li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue';

// 가짜 결과 데이터
const dummyData = [
    {
        id: 1,
        name: '결과 1',
        industrial: '제조업',
        detailRole: '펄프, 종이 및 종이제품 제조업',
        address: '대구 달성군 옥포읍 시저로 92-7',
    },
    {
        id: 2,
        name: '결과 2',
        industrial: '서비스업',
        detailRole: '컴퓨터 프로그래밍, 시스템 통합 및 관리업',
        address: '서울시 강남구',
    },
    {
        id: 3,
        name: '결과 3',
        industrial: '건설업',
        detailRole: '주택 건설업',
        address: '경기도 수원시',
    },
    {
        id: 4,
        name: '결과 4',
        industrial: '금융 및 보험업',
        detailRole: '기타 보험업',
        address: '부산광역시 해운대구',
    },
    {
        id: 5,
        name: '결과 5',
        industrial: '도매 및 소매업',
        detailRole: '자동차 및 부품 판매업',
        address: '인천광역시 중구',
    },
    {
        id: 6,
        name: '결과 6',
        industrial: '운수 및 창고업',
        detailRole: '육상 운송 및 파이프라인 운송업',
        address: '대전광역시 유성구',
    },
    {
        id: 7,
        name: '결과 7',
        industrial: '정보통신업',
        detailRole: '통신 장비 제조업',
        address: '경상남도 창원시',
    },
    {
        id: 8,
        name: '결과 8',
        industrial: '부동산업',
        detailRole: '부동산 임대업',
        address: '대구광역시 달서구',
    },
    {
        id: 9,
        name: '결과 9',
        industrial: '전기, 가스, 증기 및 공기 조절 공급업',
        detailRole: '전기 시설 설치 공사업',
        address: '서울시 종로구',
    },
    {
        id: 10,
        name: '결과 10',
        industrial: '사업시설 관리 및 사업 지원 서비스업',
        detailRole: '사업시설 관리 및 사업 운영 관리 서비스업',
        address: '경기도 성남시',
    },
];
</script>

<style>
@import url('@/assets/searchlist.css');
</style>
