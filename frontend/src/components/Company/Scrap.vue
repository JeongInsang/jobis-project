<template>
    <main>
        <section class="py-5 text-center container">
            <div class="row py-lg-5">
                <div class="col-lg-6 col-md-8 mx-auto">
                    <h1 class="fw-light">스크랩한 회사목록</h1>
                    <p class="lead text-body-secondary">좋은 회사들을 놓치지 마세요.</p>
                </div>
            </div>
        </section>
        <div class="container">
            <div class="row g-3">
                <div class="col-md-4" v-for="(company, index) in companyDummyList" :key="index">
                    <div class="card shadow-sm" v-if="company.scrap">
                        <div class="position-relative">
                            <img
                                class="bd-placeholder-img card-img-top"
                                width="100%"
                                height="100%"
                                src="@/assets/img/jobis.png"
                                alt="" />

                            <!-- 스크랩 버튼 -->
                            <button
                                type="button"
                                class="position-absolute top-0 end-0 border-0 container1"
                                @click="cancle(company.scrapId)">
                                <!-- <i class="bi bi-bookmark-star"></i> -->
                                <i class="bi bi-bookmark-check-fill"></i>
                            </button>
                        </div>
                        <div class="card-body">
                            <p class="card-text">
                                {{ company.companyname }}
                            </p>
                            <p>{{ company.companytype }}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <small class="text-body-secondary">{{ company.region }}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const API_URL = ref(null);
const accessToken = ref(null);

const companyDummyList = ref([
    {
        companyname: '이지소프트',
        companytype: '제조업',
        region: '서울',
        scrapId: 1,
        scrap: true,
    },
    {
        companyname: '(주)넥스틴',
        companytype: '금융업',
        region: '부산',
        scrapId: 2,
        scrap: true,
    },
    {
        companyname: '주식회사 클라우드시스템',
        companytype: '서비스업',
        region: '대구',
        scrapId: 3,
        scrap: true,
    },
    {
        companyname: '넥스엔정보기술(주)',
        companytype: 'IT업',
        region: '인천',
        scrapId: 4,
        scrap: true,
    },
    {
        companyname: '부곡스텐레스(주)',
        companytype: '제조업',
        region: '광주',
        scrapId: 5,
        scrap: true,
    },
    {
        companyname: '단암시스템즈 주식회사',
        companytype: '금융업',
        region: '대전',
        scrapId: 6,
        scrap: true,
    },
    {
        companyname: '파크시스템스(주)',
        companytype: '서비스업',
        region: '울산',
        scrapId: 7,
        scrap: true,
    },
    {
        companyname: '라온시큐어(주)',
        companytype: 'IT업',
        region: '세종',
        scrapId: 8,
        scrap: true,
    },
    {
        companyname: '(주)플래티어',
        companytype: '제조업',
        region: '경기',
        scrapId: 9,
        scrap: true,
    },
    {
        companyname: '한국무라타전자(주)',
        companytype: '금융업',
        region: '강원',
        scrapId: 10,
        scrap: true,
    },

    // More sample data...
]);

onMounted(() => {
    getCompanyList();
});
const companyList = ref(null);
const getCompanyList = () => {
    axios({
        method: 'get',
        url: `${API_URL}/user/scrap`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            companyList.value = res.data;
            console.log(companyList.value);
        })
        .catch((err) => {
            console.log('스크랩 데이터 가져오기 실패', err);
        });
};

const isCancle = ref(true);
const cancle = (scrapId) => {
    axios({
        method: 'delete',
        url: `${API_URL}/company/scrap/${scrapId}`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            console.log('스크랩 취소 완료');
            // 스크랩 취소 후 다시 리스트 불러와서 갱신 함
            getCompanyList();
        })
        .catch((err) => {
            console.log('스크랩 취소 실패', err);
        });
    isCancle.value = false;
};
</script>

<style scoped>
/* 스크랩 버튼 */

.container1 {
    width: 45px;
    height: 45px;
    background-color: transparent;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    border-radius: 10px;
    color: rgba(255, 255, 0, 0.744);
}

.container1:hover {
    background-color: white;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.041);
}
.container1:active {
    transform: scale(0.9);
}
</style>
