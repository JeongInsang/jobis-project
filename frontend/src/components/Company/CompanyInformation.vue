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
                                <div v-if="!isLogin">
                                    <button class="Btn" @click="goLogin">
                                        <i class="bi bi-star fs-5 star"></i>
                                    </button>
                                </div>
                                <div v-else>
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
                        </div>
                        <p>회사주소: {{ companyData.address }}</p>
                        <div><strong>설립일자 :</strong> {{ companyData.foundAt }}</div>
                        <div><strong>대표자명 :</strong> {{ companyData.owner }}</div>
                        <div><strong>자본금 :</strong> {{ companyData.capital.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") }}원</div>
                        <div><strong>종업원수 :</strong> {{ companyData.employeeCnt }}명</div>
                        <div><strong>업종 :</strong> {{ companyData.type }}</div>
                        <div><strong>주요제품명 :</strong> {{ companyData.productName }}</div>
                    </div>
                </div>
                <!-- <strong v-if="companyData.youthCompany">청년친화강소기업여부 :</strong> -->
                <div v-if="companyData.youthCompany">
                    <div>고용률 : {{ youthCompanyData[0].employmentRate }}%</div>
                    <div style="margin-top: 0.6em;">평균월급 : {{ youthCompanyData[0].salary.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") }}원</div>
                    <div style="margin-top: 0.6em;">청년비율 : {{ youthCompanyData[0].youthRate }}%</div>
                    <div style="margin-top: 0.6em;">청년고용률: {{ youthCompanyData[0].youthHireRate }}%</div>
                    <div style="margin-top: 0.6em;">청년고용증가인원 : {{ youthCompanyData[0].youthHireIncreasingCnt }}명</div>
                    <div style="margin-top: 0.6em;">청년고용증가율 : {{ youthCompanyData[0].youthHireIncreasingRate }}%</div>
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
import { getCompanyDetail, getYouthCompany, isScraped, addScrap, deleteScrap } from '@/apis/api/company';


const companyData = ref(null);
const youthCompanyData = ref(null);
const accessToken = localStorage.getItem('accessToken');
const store = useCounterStore();
const scraped = ref(null);

const route = useRoute();
const isLogin = ref(false);

const companyId = ref(route.params.companyId);
onMounted(() => {
    isLogin.value = store.isLogin;
    getCompanyInformation();
});

const API_URL = store.API_URL;

function goLogin() {
  // 로그인 페이지로 이동
  const con = confirm('로그인이 필요한 활동입니다. \n로그인 페이지로 이동하시겠습니까?');
  if(con) {
    router.push({ name: 'login' });
  }
}

const getCompanyInformation = () => {
    getCompanyDetail(companyId.value,
        (res) => {
            companyData.value = res.data.data;
            if (companyData.value.youthCompany) {
                getYouthCompanyInformation(); // 청년친화강소기업 여부가 true면 정보를 가져옴
            }
            console.log(companyData.value, 'companyData 입니다.');

            if(isLogin.value){
                getScrap();
            }
            scraped.value = res.data.data.isScraped;
            console.log(scraped.value, 'scraped입니다.');
        },
        (err) => {
            console.log(err);
        }
    );
};

const getYouthCompanyInformation = () => {
    getYouthCompany(
        companyId.value,
        (res) => {
            // 청년친화강소기업 정보를 가져와서 처리
            console.log(res.data);
            youthCompanyData.value = res.data.data;
        },
        (err) => {
            console.log(err);
        }
    );
};

// 스크랩 로직
const scrap = () => {
    if (scraped.value == false) {
        addScrap(companyId.value,
            (res) => {
                console.log('스크랩 성공 했습니다.', res);
                getCompanyInformation();
            },
            (err) => {
                console.log('스크랩 실패 했습니다.', err);
            }
        );
    }
};
// scrap true/false 유무 확인하는 로직
const getScrap = () => {
    isScraped(
        companyId.value,
        (res) => {
            console.log('스크랩 true/false 가져옵니다.', res);
            scraped.value = res.data.data;
        },
        (err) => {
            console.log('스크랩 true/false 가져오기 실패', err);
        }
    );
}

const cancleScrap = () => {
    console.log(scraped.value)

    deleteScrap(
        companyId.value,
        (res) => {
            console.log('scrap 취소 성공', res);
            getCompanyInformation();
        },
        (err) => {
            console.log('scrap 취소 실패', err);
        }
    );
};
</script>

<style scoped>
@import url('@/assets/information.css');
@import url('@/assets/scrapbtn.css');
</style>
