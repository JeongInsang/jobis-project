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
                <div class="col-md-4" v-for="(company, index) in companyList" :key="index">
                    <div class="card shadow-sm">
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
                                @click="cancle(company.companyId)">
                                <!-- <i class="bi bi-bookmark-star"></i> -->
                                <i class="bi bi-bookmark-check-fill"></i>
                            </button>
                        </div>
                        <div class="card-body">
                            <p class="card-text">
                                {{ company.name }}
                            </p>
                            <p>{{ company.type }}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <small class="text-body-secondary">{{ company.address }}</small>
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
import { useCounterStore } from '@/stores/counter';
const store = useCounterStore();

const API_URL = store.API_URL;
const accessToken = localStorage.getItem('accessToken');

onMounted(() => {
    getCompanyList();
});
const companyList = ref(null);
const getCompanyList = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/user/scrap`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            companyList.value = res.data.data;
            console.log(companyList.value);
        })
        .catch((err) => {
            console.log('스크랩 데이터 가져오기 실패', err);
        });
};

const isCancle = ref(true);
const cancle = (companyId) => {
    axios({
        method: 'delete',
        url: `${API_URL}/api/company/scrap/${companyId}`,
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
