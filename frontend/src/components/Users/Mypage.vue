<template>
    <!-- <TableTest /> -->
    <MypageTest />
</template>

<script setup>
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import { ref, onMounted } from 'vue';
import TableTest from '@/components/PageTest/TableTest.vue';
import MypageTest from '@/components/PageTest/MypageTest.vue';

const store = useCounterStore();
const scrapData = ref([]);
const scrapId = ref(null);

onMounted(() => {
    // getUserData();
});

const getUserData = function () {
    axios({
        method: 'get',
        url: `${store.API_URL}/user/scrap`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            scrapData.value = res.data;
            console.log('scrapData : ', scrapData.value);
            console.log('구독 정보를 가져왔습니다.');
        })
        .catch((err) => {
            console.log('구독 정보 로딩 실패', err);
        });
};

// follow 취소
const unfollow = (storeId) => {
    axios({
        method: 'delete',
        url: `${store.API_URL}//company/scrap/${scrapId.value}`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            console.log('팔로우 취소 성공');
            getUserData();
        })
        .catch((err) => {
            console.log('팔로우 취소 실패', err);
        });
};
</script>

<style scoped></style>
