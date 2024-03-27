<template>
    <div>
        <div v-if="companyData" class="company-info">
            <!-- 기존의 company-info div 내용 -->
            <h1>기업 상세 정보</h1>
            <class class="info-card">
                <div class="card-content">
                    <div class="details">
                        <h2>기업명: {{ companyData.name }}</h2>
                        <p>회사주소: {{ companyData.address }}</p>
                        <div><strong>설립일자 :</strong> {{ companyData.founded }}</div>
                        <div><strong>대표자명 :</strong> {{ companyData.ceo }}</div>
                        <div><strong>자본금 :</strong> {{ companyData.capital }}</div>
                        <div><strong>종업원수 :</strong> {{ companyData.employees }}</div>
                        <div><strong>업종 :</strong> {{ companyData.industry }}</div>
                        <div><strong>주요제품명 :</strong> {{ companyData.products }}</div>
                        <div>
                            <strong>청년친화강소기업여부 :</strong> {{ companyData.youthFriendly }}
                        </div>
                    </div>
                </div>
            </class>
        </div>
        <BalanceSheet />
        <FrinancialRatio />
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import BalanceSheet from '@/components/Company/BalanceSheet.vue';
import FrinancialRatio from '@/components/Company/FinancialRatio.vue';

const companyData = ref(null);
const accessToken = localStorage.getItem('accessToken');

onMounted(() => {
    getCompanyDetail();
});

const getCompanyDetail = () => {
    axios({
        method: 'get',
        url: 'YOUR_API_ENDPOINT',
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            companyData.value = res.data;
        })
        .catch((err) => {
            console.error(err);
        });
};
</script>

<style scoped>
.company-info {
    text-align: center;
}

.info-card {
    width: 100%;
    margin-top: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0 2px 4px rgba (0, 0, 0, 0.1);
    background-color: #f9f9f9;
}

.card-content {
    display: flex;
    align-items: center;
    justify-content: start;
    border-bottom: 2px solid #eee;
    padding-bottom: 20px;
    margin-bottom: 20px;
}

.company-logo {
    width: 100px;
    height: 100px;
    object-fit: contain;
    border-radius: 50%;
    margin-right: 20px;
}

.details {
    text-align: left;
}

.financial-section,
.indicators-section {
    text-align: left;
    width: 100%;
}

.financial-statements,
.indicators {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
}

.financial-statements th,
.financial-statements td,
.indicators th,
.indicators td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: right;
}

.financial-statements th,
.indicators th {
    background-color: #f2f2f2;
}

/* 테이블의 첫 번째 열(계정명)을 왼쪽 정렬합니다. */
.financial-statements td:first-child,
.indicators td:first-child {
    text-align: left;
}

/* 로딩 중 메시지 스타일 */
.loading-message {
    font-size: 18px;
    color: #666;
}
</style>
