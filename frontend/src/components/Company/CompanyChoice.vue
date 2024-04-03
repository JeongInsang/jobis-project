<template>
    <div class="container1">
        <div>
            <h2>기업선호 월드컵</h2>
            <div v-if="!winner">
                <transition :name="isNextRound ? 'fade-next-round' : 'fade'" mode="out-in">
                    <h2 style="text-align: center" :key="currentRound">
                        {{ currentRound }}
                    </h2>
                </transition>
                <button
                    @click="startTournamentAndgetInfo"
                    v-if="!isTournamentStarted"
                    class="learn-more">
                    게임 시작!
                </button>
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
            <div v-else style="width: 55rem">
                <!-- 수정된 부분: 추가 정보 확인 버튼 클릭 시, winner의 name 정보만을 표시 -->
                <h3>{{ winner.name }}가 선호기업 선택 월드컵에서 우승했습니다!</h3>
                <div v-if="winnerInfoLoaded" style="margin-top: 2rem">
                    <h4 style="margin-bottom: 2rem">성향과 비슷한 추천 기업 목록</h4>
                    <div class="card-container">
                        <div v-for="(info, index) in win" :key="index" class="unique-card">
                            <div class="background-overlay"></div>
                            <div class="card-content">
                                <div class="card-title">{{ info.name }}</div>
                                <div class="card-description">{{ info.type }}</div>
                                <div>활동성 : {{ info.factor.activity.toFixed(6) }}</div>
                                <div>안정성 : {{ info.factor.stability.toFixed(6) }}</div>
                                <div>수익성 : {{ info.factor.profitability.toFixed(6) }}</div>
                                <div>성장성 : {{ info.factor.growth.toFixed(6) }}</div>
                                <div>효율성 : {{ info.factor.efficiency.toFixed(6) }}</div>
                                <!-- <div class="card-description">{{ info.address }}</div> -->
                            </div>
                        </div>
                    </div>
                    <button @click="resetTournament" v-if="winner" class="learn-more">
                        다시하기
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import { watch } from 'vue';

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
const winnerInfoLoaded = ref(false);

const getWinnerInfo = () => {
    winnerInfo();
};
const win = ref('');
const winnerInfo = function () {
    axios({
        method: 'get',
        url: `${API_URL}/api/recommendation/${winner.value.worldcupId}`, // winner의 정보로 API 요청
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            win.value = res.data.data;
            winnerInfoLoaded.value = true; // winner 정보가 로드되었음을 표시
            console.log(win.value);
            console.log(winnerInfoLoaded.value);
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
watch(winner, (newValue) => {
    if (newValue !== '') {
        getWinnerInfo();
    }
});
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
.card-container {
    display: flex;
    flex-wrap: wrap;
    gap: 3rem;
}

.unique-card {
    display: flex;
    position: relative;
    width: 16rem;
    height: 12rem;
    overflow: hidden;
    border-radius: 15px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    transition:
        transform 0.3s,
        box-shadow 0.3s;
}

.unique-card:hover {
    transform: scale(1.1);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
}

.card-content {
    padding: 10px;
    text-align: center;
    color: #888;
    z-index: 2;
    transition: color 0.3s;
}

.unique-card:hover .card-content {
    color: #ff7e5f;
}

.background-overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(52, 73, 94, 0.7);
    border-radius: 15px;
    opacity: 0;
    transition: opacity 0.3s;
}

.unique-card:hover .background-overlay {
    opacity: 1;
}

.card-title {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
}

.card-description {
    padding-top: 0;
    padding-bottom: 5px;
    display: block;
    padding-left: 5px;
    font-size: 14px;
}
</style>
