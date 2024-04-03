<template>
    <div class="card">
        <div v-if="companyData" class="company-info">
            <!-- 기존의 company-info div 내용 -->
            <div class="info-card">
                <h1>기업 상세 정보</h1>
                <div class="card-content">
                    <div class="details">
                        <div class="d-flex">
                            <h2>{{ companyData.name }}</h2>
                            <div style="margin-left: 0.5rem">
                                <div v-if="scraped" @click="cancleScrap">
                                    <button class="Btn">
                                        <i class="bi bi-star-fill fs-5 star"></i>
                                    </button>
                                </div>
                                <div v-else @click="scrap">
                                    <button class="Btn">
                                        <i class="bi bi-star fs-5 star"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
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
                    <div>고용률 : {{ youthCompanyData[0].employmentRate }}</div>
                    <div>연봉 : {{ youthCompanyData[0].salary }}</div>
                    <div>청년비율 : {{ youthCompanyData[0].youthRate }}</div>
                    <div>청년고용률: {{ youthCompanyData[0].youthHireRate }}</div>
                    <div>청년고용증가인원 : {{ youthCompanyData[0].youthHireIncreasingCnt }}</div>
                    <div>청년고용증가율 : {{ youthCompanyData[0].youthHireIncreasingRate }}</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import { useRoute } from 'vue-router';
import router from '@/router';

const companyData = ref(null);
const youthCompanyData = ref(null);
const accessToken = localStorage.getItem('accessToken');
const store = useCounterStore();
const scraped = ref(null);
const route = useRoute();

const companyId = ref(route.params.companyId);
onMounted(() => {
    getCompanyInformation();
});

const API_URL = store.API_URL;
const getCompanyInformation = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/info/${companyId.value}`,
    })
        .then((res) => {
            companyData.value = res.data.data;
            scraped.value = res.data.data.scraped;
            if (companyData.value.youthCompany) {
                getYouthCompanyInformation(); // 청년친화강소기업 여부가 true면 정보를 가져옴
            }
            console.log(companyData.value, 'companyData 입니다.');
            // console.log(scraped.value, 'scraped입니다.');
        })
        .catch((err) => {
            console.log(err);
        });
};

const getYouthCompanyInformation = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/youthcompany/${companyId.value}`,
    })
        .then((res) => {
            // 청년친화강소기업 정보를 가져와서 처리
            console.log(res.data);
            youthCompanyData.value = res.data.data;
        })
        .catch((err) => {
            console.log(err);
        });
};

// 스크랩 로직
const scrap = () => {
    if (scraped.value == false) {
        if (!store.isLogin) {
            router.push({ name: 'login' });
            window.alert('로그인 해주세요');
        } else {
            axios({
                method: 'post',
                url: `${API_URL}/api/company/scrap/${companyId.value}`,
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            })
                .then((res) => {
                    console.log('스크랩 성공 했습니다.', res);
                })
                .catch((err) => {
                    console.log('스크랩 실패 했습니다.', err);
                });
        }
    }
};
const cancleScrap = () => {
    axios({
        method: 'delete',
        url: `${API_URL}/api/company/scrap/${companyId.value}`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            console.log('scrap 취소 성공', res);
        })
        .catch((err) => {
            console.log('scrap 취소 실패', err);
        });
};
</script>

<style scoped>
@import url('@/assets/information.css');
@import url('@/assets/scrapbtn.css');
</style>
