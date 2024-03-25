<template>
    <div>
        <h2>최근 뉴스 정보</h2>
        <ul>
            <li v-for="article in newsArticles" :key="article.id">
                {{ article.title }} - {{ article.date }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const newsArticles = ref([
    { id: 1, title: 'News Article 1', date: '2022-01-01' },
    { id: 2, title: 'News Article 2', date: '2022-02-01' },
    { id: 3, title: 'News Article 3', date: '2022-03-01' },
    // 더 많은 뉴스 리스트 아이템들...
]);
</script>

<style scoped>
/* 스타일링 */
</style>

<!-- <template>
    <div>뉴스 리스트</div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore();
const accessToken = localStorage.getItem('accessToken');

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
</script>

<style scoped></style> -->
