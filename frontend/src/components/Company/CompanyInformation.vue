<template>
    <div class="card">
        <div v-if="companyData" class="company-info">
            <!-- 기존의 company-info div 내용 -->
            <div class="info-card">
                <h1>기업 상세 정보</h1>
                <div class="card-content">
                    <div class="details">
                        <h2>{{ companyData.name }}</h2>
                        <p>회사주소: {{ companyData.address }}</p>
                        <div><strong>설립일자 :</strong> {{ companyData.foundAt }}</div>
                        <div><strong>대표자명 :</strong> {{ companyData.owner }}</div>
                        <div><strong>자본금 :</strong> {{ companyData.capital }}</div>
                        <div><strong>종업원수 :</strong> {{ companyData.employeeCnt }}</div>
                        <div><strong>업종 :</strong> {{ companyData.type }}</div>
                        <div><strong>주요제품명 :</strong> {{ companyData.productName }}</div>
                    </div>
                </div>
                <!-- <strong v-if="companyData.youthCompany">청년친화강소기업여부 :</strong> -->
                <div v-if="companyData.youthCompany">
                    <youthCompany />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import youthCompany from '@/components/Company/youthCompany.vue';

const companyData = ref(null);
const accessToken = localStorage.getItem('accessToken');
const store = useCounterStore();
onMounted(() => {
    getCompanyInformation();
});

const API_URL = store.API_URL;
const getCompanyInformation = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/info/1006`,
    })
        .then((res) => {
            companyData.value = res.data.data;
            if (companyData.value.youthCompany) {
                getYouthCompanyInformation(); // 청년친화강소기업 여부가 true면 정보를 가져옴
            }
            console.log(companyData.value);
        })
        .catch((err) => {
            console.log(err);
        });
};

const getYouthCompanyInformation = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/youthcompany/1006`,
    })
        .then((res) => {
            // 청년친화강소기업 정보를 가져와서 처리
            console.log(res.data);
        })
        .catch((err) => {
            console.log(err);
        });
};
</script>

<style scoped>
@import url('@/assets/information.css');
</style>
