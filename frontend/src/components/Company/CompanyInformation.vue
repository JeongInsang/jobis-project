<template>
    <div>
        <div v-if="companyData" class="company-info">
            <!-- 기존의 company-info div 내용 -->
            <div class="info-card">
                <h1>기업 상세 정보</h1>
                <div class="card-content">
                    <div class="details">
                        <h2>{{ companyData.name }}</h2>
                        <p>회사주소: {{ companyData.address }}</p>
                        <div><strong>설립일자 :</strong> {{ companyData.foundAt }}</div>
                        <div><strong>대표자명 :</strong> {{ companyData.Owner }}</div>
                        <div><strong>자본금 :</strong> {{ companyData.capital }}</div>
                        <div><strong>종업원수 :</strong> {{ companyData.employeeCnt }}</div>
                        <div><strong>업종 :</strong> {{ companyData.type }}</div>
                        <div><strong>주요제품명 :</strong> {{ companyData.productName }}</div>
                        <div>
                            <strong>청년친화강소기업여부 :</strong> {{ companyData.youthCompany }}
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
import { useCounterStore } from '@/stores/counter';

const companyData = ref(null);
const accessToken = localStorage.getItem('accessToken');
const store = useCounterStore();
onMounted(() => {
    getcompanyInformation();
});

const API_URL = store.API_URL;
const getcompanyInformation = function () {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/info/1`,
    })
        .then((res) => {
            companyData.value = res.data.data;
            console.log(companyData.value);
            console.log('데이터 받음');
        })
        .catch((err) => {
            console.log(err);
            console.log('실패');
        });
};
</script>

<style scoped>
@import url('@/assets/information.css');
</style>
