<template>
    <div>
        <div v-if="!winner">
            <div v-for="(match, index) in matches" :key="index">
                <h2>대진 {{ index + 1 }}</h2>
                <p>1. {{ match[0] }} vs 2. {{ match[1] }}</p>
                <button @click="selectWinner(index, 0)">1번 기업 선택</button>
                <button @click="selectWinner(index, 1)">2번 기업 선택</button>
            </div>
        </div>
        <div v-else>
            <h2>축하합니다!</h2>
            <p>{{ winner }}가 기업 선택 월드컵에서 우승했습니다!</p>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const companies = [
    '기업 A',
    '기업 B',
    '기업 C',
    '기업 D',
    '기업 E',
    '기업 F',
    '기업 G',
    '기업 H',
    '기업 I',
    '기업 J',
    '기업 K',
    '기업 L',
    '기업 M',
    '기업 N',
    '기업 O',
    '기업 P',
];
const matches = ref([]);
const winners = ref([]);
const winner = ref('');

const shuffleCompanies = () => {
    for (let i = companies.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [companies[i], companies[j]] = [companies[j], companies[i]];
    }
};

const createMatches = () => {
    for (let i = 0; i < companies.length; i += 2) {
        matches.value.push([companies[i], companies[i + 1]]);
    }
};

const selectWinner = (matchIndex, winnerIndex) => {
    winners.value.push(matches.value[matchIndex][winnerIndex]);
    matches.value.splice(matchIndex, 1);
    if (matches.value.length === 0) {
        startNextRound();
    }
};

const startNextRound = () => {
    if (winners.value.length === 1) {
        winner.value = winners.value[0];
    } else {
        matches.value = [];
        matches.value.push(
            ...winners.value
                .map((val, idx, arr) => (idx % 2 === 0 ? [val, arr[idx + 1]] : null))
                .filter(Boolean)
        );
        winners.value = [];
    }
};

onMounted(() => {
    shuffleCompanies();
    createMatches();
});
</script>

<style scoped>
/* Add your styles here */
</style>
