<template>
    <div>기업정보 상세페이지(시각화, 뉴스, 특허 포함) >>> 컴포넌트화</div>
    <License />
    <News />
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import License from '@/components/Company/License.vue';
import News from '@/components/Company/News.vue';

const store = useCounterStore();
const accessToken = localStorage.getItem('accessToken');

onMounted(() => {
    getCompanyDetail();
    getLicenseData();
    getNewsData();
});

const getCompanyDetail = function () {
    axios({
        method: 'get',
        url: ``,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            console.log(res.data);
        })
        .catch((err) => {
            console.log(err);
        });
};

const getNewsData = function () {
    axios({
        method: 'get',
        url: ``,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            console.log(res.data);
        })
        .catch((err) => {
            console.log(err);
        });
};

const getLicenseData = function () {
    axios({
        method: 'get',
        url: ``,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            console.log(res.data);
        })
        .catch((err) => {
            console.log(err);
        });
};
</script>

<style scoped></style>
