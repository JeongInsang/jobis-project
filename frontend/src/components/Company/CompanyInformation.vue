<template>
    <div>
        <div v-if="companyData" class="company-info">
            <!-- 기존의 company-info div 내용 -->
            <div class="info-card">
                <h1>기업 상세 정보</h1>
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
            </div>
        </div>
        <BalanceSheet />
        <FrinancialRatio />
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

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
@import url('@/assets/information.css');
</style>
