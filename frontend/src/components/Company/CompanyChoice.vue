<template>
    <div class="container">
        <div>
            <button @click="startTournament" v-if="!isTournamentStarted">대진 시작</button>
            <button @click="resetTournament" v-if="winner">다시하기</button>
            <!-- 다시하기 버튼 추가 -->
            <div v-if="!winner">
                <!-- 페이드 애니메이션을 적용한 라운드 표시 -->
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
                        <!-- 기업 1 카드 -->
                        <div class="card" @click="selectWinner(currentMatchIndex, 0)">
                            <!-- 시각화 영역 -->
                            <div class="image">
                                <canvas class="chart-canvas" ref="chartCanvas1"></canvas>
                            </div>
                            <!-- 기업 정보 표시 -->
                            <div class="text">
                                <span>{{ matches[currentMatchIndex][0].name }}</span>
                                <!-- 기업의 특성 값들을 반복문을 통해 표시 -->
                                <div
                                    v-for="(value, key) in matches[currentMatchIndex][0]
                                        .visualizationData"
                                    :key="key">
                                    <p>{{ key }} : {{ value }}</p>
                                </div>
                                <br />
                                <span>전체 분석 내용</span>
                                <p style="width: 380px">
                                    이 기업은 특허 개수를 바탕으로 성장성이 뛰어나고 어쩌구저쩌구
                                </p>
                            </div>
                        </div>
                        <!-- 기업 2 카드 -->
                        <div class="card" @click="selectWinner(currentMatchIndex, 1)">
                            <!-- 시각화 영역 -->
                            <div class="image">
                                <canvas class="chart-canvas" ref="chartCanvas2"></canvas>
                            </div>
                            <!-- 기업 정보 표시 -->
                            <div class="text">
                                <span>{{ matches[currentMatchIndex][1].name }}</span>
                                <!-- 기업의 특성 값들을 반복문을 통해 표시 -->
                                <div
                                    v-for="(value, key) in matches[currentMatchIndex][1]
                                        .visualizationData"
                                    :key="key">
                                    <p>{{ key }} : {{ value }}</p>
                                </div>
                                <br />
                                <span>전체 분석 내용</span>
                                <p style="width: 380px">
                                    이 기업은 특허 개수를 바탕으로 성장성이 뛰어나고 어쩌구저쩌구
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
import Visualization from './Visualization.vue';
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import Chart from 'chart.js/auto';
import 'chartjs-adapter-date-fns';
import 'chartjs-plugin-datalabels';

const store = useCounterStore();

const companies = [
    {
        name: '기업1',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업2',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업3',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업4',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업5',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업6',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업7',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업8',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업9',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업10',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업11',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업12',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업13',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업14',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업15',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
    {
        name: '기업16',
        visualizationData: {
            안정성: 9,
            성장성: -9,
            활동성: 4,
            수익성: -2,
            가용성: 5,
        },
    },
];
const matches = ref([]);
const winners = ref([]);
const winner = ref('');
const currentMatchIndex = ref(0);
let currentRound = ref('16강');
const selectionConfirmed = ref(false);

const chartCanvas1 = ref(null);
const chartCanvas2 = ref(null);
// 차트 인스턴스를 저장할 변수 선언

let chartInstance1 = null;
let chartInstance2 = null;

// 대진 생성 함수 수정
const createMatches = () => {
    const newMatches = [];
    for (let i = 0; i < companies.length; i += 2) {
        newMatches.push([companies[i], companies[i + 1]]);
    }
    matches.value = newMatches;
    // 첫 번째 대진에 대해 차트 그리기
    if (newMatches.length > 0) {
        console.log(newMatches[0][0].visualizationData);
        console.log(newMatches[0][1].visualizationData);
        chartInstance1 = renderChart(
            chartCanvas1,
            newMatches[0][0].visualizationData,
            chartInstance1
        );
        chartInstance2 = renderChart(
            chartCanvas2,
            newMatches[0][1].visualizationData,
            chartInstance2
        );
    }
};

// 차트 렌더링 함수 수정
const renderChart = (canvasRef, data, chartInstance) => {
    // 이전 차트 인스턴스가 존재하면 제거
    if (chartInstance !== null) {
        chartInstance.destroy();
    }
    if (canvasRef.value !== null) {
        const ctx = canvasRef.value.getContext('2d');
        // 새 차트 인스턴스 생성 및 할당
        return new Chart(ctx, {
            type: 'radar',
            data: {
                labels: Object.keys(data),
                datasets: [
                    {
                        label: '점수',
                        data: Object.values(data),
                        backgroundColor: 'rgba(0, 123, 255, 0.7)',
                        borderColor: 'rgba(0, 123, 255, 1)',
                        borderWidth: 1,
                    },
                ],
            },
            options: {
                scales: {
                    r: {
                        max: 9,
                        min: -9,
                        stepSize: 3,
                    },
                },
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    datalabels: {
                        display: true,
                        color: '#000',
                        font: {
                            weight: 'bold',
                        },
                    },
                },
            },
        });
    }
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
    let nextRoundName; // 다음 라운드 이름을 임시 저장할 변수

    // 다음 라운드 이름 결정
    if (winners.value.length === 8) {
        nextRoundName = '8강';
    } else if (winners.value.length === 4) {
        nextRoundName = '4강';
    } else if (winners.value.length === 2) {
        nextRoundName = '결승';
    } else if (winners.value.length === 1) {
        nextRoundName = '우승';
    }

    // 2초 동안 nextRoundName을 currentRound에 할당하여 표시
    currentRound.value = nextRoundName;

    setTimeout(() => {
        // 2초 지연 후 로직 실행
        if (winners.value.length === 1) {
            winner.value = winners.value[0]; // 우승자 결정
        } else {
            matches.value = winners.value
                .map((val, idx, arr) => (idx % 2 === 0 ? [val, arr[idx + 1]] : null))
                .filter(Boolean); // 다음 라운드 대진 생성
            winners.value = []; // 우승자 배열 초기화
            currentMatchIndex.value = 0; // 대진 인덱스 초기화

            // 실제 라운드명 업데이트
            currentRound.value = nextRoundName;
        }

        // 애니메이션 재설정
        const isNextRound = ref(null);
        isNextRound.value = true;
        setTimeout(() => (isNextRound.value = false), 500);
    }, 1000); // 2초 지연
};
</script>

<style scoped>
@import url('@/assets/choice.css');
/* 페이드 애니메이션 정의 */
/* 페이드 아웃에서 인으로 애니메이션 정의 */
.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.2s ease;
}
/* startNextRound 호출 시 적용될 애니메이션 스타일 */
.fade-next-round-enter-active,
.fade-next-round-leave-active {
    transition: opacity 0.2s ease-out;
}
</style>
