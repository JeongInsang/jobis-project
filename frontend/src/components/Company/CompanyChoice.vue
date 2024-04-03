<template>
    <div class="container">
        <div>
            <button @click="startTournamentAndgetInfo" v-if="!isTournamentStarted">
                대진 시작
            </button>
            <button @click="resetTournament" v-if="winner">다시하기</button>
            <div v-if="!winner">
                <transition :name="isNextRound ? 'fade-next-round' : 'fade'" mode="out-in">
                    <h2 style="text-align: center" :key="currentRound">
                        {{ currentRound }}
                    </h2>
                </transition>
                <transition :name="isNextRound ? 'fade-next-round' : 'fade'" mode="out-in">
                    <div
                        v-if="currentMatchIndex < matches.length"
                        class="current-match-display"
                        :key="currentMatchIndex">
                        <div v-for="(match, index) in matches[currentMatchIndex]" :key="index">
                            <div class="card" @click="selectWinner(currentMatchIndex, index)">
                                <img
                                    :src="`src/assets/img/worldcup/${match.worldcupId}.jpg`"
                                    alt="나와라 제발"
                                    class="image" />

                                <div class="text">
                                    <span>{{ match.name }}</span>
                                    <p>활동성 : {{ match.activity }}</p>
                                    <p>효율성 : {{ match.efficiency }}</p>
                                    <p>성장성 : {{ match.growth }}</p>
                                    <p>수익성 : {{ match.profitability }}</p>
                                    <p>안정성 : {{ match.stability }}</p>
                                    <br />
                                    <span>전체 분석 내용</span>
                                    <p style="width: 380px">{{ match.description }}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </transition>
            </div>
            <div v-else>
                <h2>축하합니다!</h2>
                <p>{{ winner.name }}가 코드 선택 월드컵에서 우승했습니다!</p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore();
const isNextRound = ref(false);
const companies = ref([]);
const matches = ref([]);
const winners = ref([]);
const winner = ref('');
const currentMatchIndex = ref(0);
let currentRound = ref('16강');
const selectionConfirmed = ref(false);
const API_URL = store.API_URL;
const accessToken = localStorage.getItem('accessToken');
const startTournamentAndgetInfo = function () {
    companiesInfo();
    startTournament();
};
const getWorldcupImgPath = (worldcupId) => {
    return new URL(`@/assets/img/worldcup/${worldcupId}.jpg`, import.meta.url).href;
};
const companiesInfo = function () {
    axios({
        method: 'get',
        url: `${API_URL}/api/worldcup`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            companies.value = res.data.data;
            // console.log(companies.value);
            createMatches();
        })
        .catch((err) => {
            console.log(err);
        });
};

const createMatches = () => {
    const newMatches = [];
    for (let i = 0; i < companies.value.length; i += 2) {
        newMatches.push([companies.value[i], companies.value[i + 1]]);
        // console.log(companies.value);
    }
    matches.value = newMatches;
};

const isTournamentStarted = ref(false);

const startTournament = () => {
    isTournamentStarted.value = true;
    if (!matches.value.length || currentRound.value === '16강') {
        createMatches();
    }
};

const resetTournament = () => {
    isTournamentStarted.value = false;
    matches.value = [];
    winners.value = [];
    winner.value = '';
    currentMatchIndex.value = 0;
    currentRound.value = '16강';
};

const nextMatch = ref(null);

const selectWinner = (matchIndex, winnerIndex) => {
    winners.value.push(matches.value[matchIndex][winnerIndex]);
    selectionConfirmed.value = false;
    setTimeout(() => {
        selectionConfirmed.value = true;
        currentMatchIndex.value++;
        if (currentMatchIndex.value >= matches.value.length) {
            startNextRound();
        }
    }, 50);
};

const startNextRound = () => {
    let nextRoundName;
    if (winners.value.length === 8) {
        nextRoundName = '8강';
    } else if (winners.value.length === 4) {
        nextRoundName = '4강';
    } else if (winners.value.length === 2) {
        nextRoundName = '결승';
    } else if (winners.value.length === 1) {
        nextRoundName = '우승';
    }

    currentRound.value = nextRoundName;

    setTimeout(() => {
        if (winners.value.length === 1) {
            winner.value = winners.value[0];
        } else {
            matches.value = winners.value
                .map((val, idx, arr) => (idx % 2 === 0 ? [val, arr[idx + 1]] : null))
                .filter(Boolean);
            winners.value = [];
            currentMatchIndex.value = 0;
            currentRound.value = nextRoundName;
        }

        const isNextRound = ref(null);
        isNextRound.value = true;
        setTimeout(() => (isNextRound.value = false), 500);
    }, 1000);
};
</script>

<style scoped>
@import url('@/assets/choice.css');

.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.2s ease;
}

.fade-next-round-enter-active,
.fade-next-round-leave-active {
    transition: opacity 0.2s ease-out;
}
</style>
