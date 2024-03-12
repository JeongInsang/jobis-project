<template>
    <div class="card-container">
        <div class="card" v-for="(card, index) in cards" :key="index">
            <!-- 카드 내용 -->
            <h2>{{ card.title }}</h2>
            <p>{{ card.description }}</p>
        </div>
    </div>
</template>

<script setup>
const cards = [
    { title: '카드 1', description: '카드 1의 내용입니다.' },
    { title: '카드 2', description: '카드 2의 내용입니다.' },
    { title: '카드 3', description: '카드 3의 내용입니다.' },
    { title: '카드 4', description: '카드 4의 내용입니다.' },
    { title: '카드 5', description: '카드 5의 내용입니다.' },
    { title: '카드 6', description: '카드 6의 내용입니다.' },
    { title: '카드 7', description: '카드 7의 내용입니다.' },
    { title: '카드 8', description: '카드 8의 내용입니다.' },
    { title: '카드 9', description: '카드 9의 내용입니다.' },
    { title: '카드 10', description: '카드 10의 내용입니다.' },
];
</script>

<style scoped>
@import url('@/assets/top10companylist.css');
</style>
