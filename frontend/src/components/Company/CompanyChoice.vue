<template>
    <div class="container">
        <div>
            <button @click="startTournament" v-if="!isTournamentStarted">대진 시작</button>
            <button @click="resetTournament" v-if="winner">다시하기</button>
            <!-- 다시하기 버튼 추가 -->
            <div v-if="!winner">
                <h2 style="text-align: center">{{ currentRound }}</h2>
                <transition name="fade" mode="out-in">
                    <div
                        v-if="currentMatchIndex < matches.length"
                        class="current-match-display"
                        :key="currentMatchIndex">
                        <!-- 기업 1 카드 -->
                        <div class="card" @click="selectWinner(currentMatchIndex, 0)">
                            <div class="image">
                                <Visualization />
                            </div>
                            <div class="text">
                                <span>{{ matches[currentMatchIndex][0] }}</span>
                                <p>성장성 : 1</p>
                                <p>안정성 : 2</p>
                                <p>수익성 : 3</p>
                                <p>활동성 : 4</p>
                                <p>가용성 : 5</p>
                                <br />
                                <span>전체 분석 내용</span>
                                <p>이 기업은 특허 개수를 바탕으로 성장성이 뛰어나고 어쩌구저쩌구</p>
                            </div>
                        </div>
                        <!-- 기업 2 카드 -->
                        <div class="card" @click="selectWinner(currentMatchIndex, 1)">
                            <div class="image">
                                <Visualization />
                            </div>
                            <div class="text">
                                <span>{{ matches[currentMatchIndex][1] }}</span>
                                <p>성장성 : 5</p>
                                <p>안정성 : 4</p>
                                <p>수익성 : 3</p>
                                <p>활동성 : 2</p>
                                <p>가용성 : 1</p>
                                <br />
                                <span>전체 분석 내용</span>
                                <p>
                                    이 기업은 30년간 안정적으로 운영된 기업으로 복지가 좋고 그렇다나
                                </p>
                            </div>
                        </div>
                    </div>
                </transition>
            </div>
            <div v-else>
                <h2>축하합니다!</h2>
                <p>{{ winner }}가 코드 선택 월드컵에서 우승했습니다!</p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import Visualization from './Visualization.vue';
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
const currentMatchIndex = ref(0);
let currentRound = ref('16강');
const selectionConfirmed = ref(false);

// 대진 생성 함수
const createMatches = () => {
    const newMatches = [];
    for (let i = 0; i < companies.length; i += 2) {
        newMatches.push([companies[i], companies[i + 1]]);
    }
    matches.value = newMatches; // 새 배열을 할당하여 Vue가 변경을 감지하도록 함
};

const isTournamentStarted = ref(false);
// 대진 시작 함수
const startTournament = () => {
    isTournamentStarted.value = true; // 대진 시작 시 true로 설정
    if (!matches.value.length || currentRound.value === '16강') {
        matches.value = [];
        winners.value = [];
        winner.value = '';
        currentMatchIndex.value = 0;
        currentRound.value = '16강';
        createMatches(); // 대진 생성
    }
};
// 대회를 재설정하는 함수
const resetTournament = () => {
    isTournamentStarted.value = false; // 대회 재설정 시 false로 설정하여 버튼을 다시 표시
    matches.value = []; // 대진 배열을 비웁니다.
    winners.value = []; // 우승자 배열을 비웁니다.
    winner.value = ''; // 최종 우승자를 비웁니다.
    currentMatchIndex.value = 0; // 현재 대진 인덱스를 초기화합니다.
    currentRound.value = '16강'; // 현재 라운드를 '16강'으로 초기화합니다.
    createMatches(); // 대진 생성
};

// 기업 선택하는 함수
const selectWinner = (matchIndex, winnerIndex) => {
    winners.value.push(matches.value[matchIndex][winnerIndex]);

    // 선택 확인 딜레이 시작
    selectionConfirmed.value = false;
    setTimeout(() => {
        selectionConfirmed.value = true;
        // 다음 대진으로 이동
        currentMatchIndex.value++;

        // 모든 대진이 끝나면 다음 라운드로
        if (currentMatchIndex.value >= matches.value.length) {
            startNextRound();
        }
    }, 50); // 1초 딜레이
};

// 다음 라운드를 시작하는 함수
const startNextRound = () => {
    if (winners.value.length === 1) {
        winner.value = winners.value[0];
    } else {
        matches.value = winners.value
            .map((val, idx, arr) => (idx % 2 === 0 ? [val, arr[idx + 1]] : null))
            .filter(Boolean);
        winners.value = [];
        currentMatchIndex.value = 0;

        if (matches.value.length === 4) {
            currentRound.value = '8강';
        } else if (matches.value.length === 2) {
            currentRound.value = '4강';
        } else if (matches.value.length === 1) {
            currentRound.value = '결승';
        }
    }
};
</script>
<style scoped>
@import url('@/assets/choice.css');
</style>
