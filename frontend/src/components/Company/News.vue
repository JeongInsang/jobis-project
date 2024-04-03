<template>
    <div>뉴스 리스트</div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore();
const accessToken = localStorage.getItem('accessToken');
const NewsList = ref([]);
const keyWord = ref([]);

const getNewsData = function () {
    axios({
        method: 'get',
        url: `${store.API_URL}/api/news/1818/구름`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            NewsList.value = res.data;
            keyWord.value = res.data.data.keyWords;
            console.log(NewsList.value);
            console.log(keyWord.value);
        })
        .catch((err) => {
            console.log(err);
        });
};
onMounted(() => {
    getNewsData();
});
</script>

<style scoped></style>
