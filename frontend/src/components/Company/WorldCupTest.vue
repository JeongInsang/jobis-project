<template>
    <div class="container">
        <div>
            <button @click="startTournamentAndgetInfo" v-if="!isTournamentStarted">
                대진 시작
            </button>
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
                        <!-- 대진 정보를 표시하는 카드 반복 -->
                        <div v-for="(match, index) in matches[currentMatchIndex]" :key="index">
                            <div class="card" @click="selectWinner(currentMatchIndex, index)">
                                <!-- 시각화 영역 -->
                                <div class="image">
                                    <canvas :ref="'chartCanvas' + index"></canvas>
                                </div>
                                <!-- 기업 정보 표시 -->
                                <div class="text">
                                    <span>{{ match.name }}</span>
                                    <!-- 기업의 특성 값들을 반복문을 통해 표시 -->
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
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import Chart from 'chart.js/auto';
import 'chartjs-adapter-date-fns';
import 'chartjs-plugin-datalabels';

onMounted(() => {
    // companiesInfo();
});
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
const chartCanvas1 = ref(null);
const chartCanvas2 = ref(null);
const chartCanvasRefs = ref([]); // 수정: chartCanvasRefs 배열 추가
const accessToken = localStorage.getItem('accessToken');

const startTournamentAndgetInfo = function () {
    companiesInfo();
    startTournament();
};

// 차트 인스턴스를 저장할 변수 선언
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
            console.log(companies.value);
            createMatches(); // 데이터를 받은 후에 대진 생성
            createChartInstances(); // 데이터를 받은 후에 차트 인스턴스 생성
        })
        .catch((err) => {
            console.log(err);
        });
};
let chartInstances = [];

// 대진 생성 함수 수정
const createMatches = () => {
    const newMatches = [];
    for (let i = 0; i < companies.value.length; i += 2) {
        newMatches.push([companies.value[i], companies.value[i + 1]]);
        console.log(companies.value);
    }
    matches.value = newMatches;
};
// 차트 인스턴스를 저장할 변수
let charts = [];

// 차트 인스턴스 생성 함수
function createChartInstances() {
    const canvasElements = document.querySelectorAll('.radar-chart');

    canvasElements.forEach((canvas, index) => {
        const ctx = canvas.getContext('2d');
        const company = companies.value[index];

        const data = {
            labels: ['활동성', '효율성', '성장성', '수익성', '안정성'],
            datasets: [
                {
                    label: company.name,
                    data: [
                        company.activity,
                        company.efficiency,
                        company.growth,
                        company.profitability,
                        company.stability,
                    ],
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1,
                },
            ],
        };

        const options = {
            scales: {
                r: {
                    max: 9,
                    min: -9,
                    stepSize: 3,
                },
            },
        };

        const chart = new Chart(ctx, {
            type: 'radar',
            data: data,
            options: options,
        });

        // 생성된 차트 인스턴스를 변수에 저장
        charts.push(chart);

        // 차트 캔버스의 참조를 chartCanvasRefs 배열에 할당
        chartCanvasRefs.value.push(canvas);
    });
}

// 차트 렌더링 함수 수정
const renderChart = (canvasRef, data, chartInstance) => {
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
    // 캔버스 참조가 null이 아닌 경우에만 차트 인스턴스 반환
    return chartInstance;
};

const isTournamentStarted = ref(false);
// 대진 시작 함수
// 대진 시작 함수 내부에서 차트 인스턴스 생성 함수 호출
const startTournament = () => {
    isTournamentStarted.value = true;
    if (!matches.value.length || currentRound.value === '16강') {
        createMatches(); // 대진 생성
        createChartInstances(); // 차트 인스턴스 생성
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
    // createMatches(); // 대진 생성
};
const nextMatch = ref(null);
// 기업 선택하는 함수
const selectWinner = (matchIndex, winnerIndex) => {
    chartInstances.forEach((chartInstance) => {
        if (chartInstance) {
            chartInstance.destroy();
        }
    });
    winners.value.push(matches.value[matchIndex][winnerIndex]);
    console.log(matches.value[matchIndex][winnerIndex]);
    console.log(matches.value);
    // 선택 확인 딜레이 시작
    selectionConfirmed.value = false;
    setTimeout(() => {
        selectionConfirmed.value = true;
        // 다음 대진으로 이동
        currentMatchIndex.value++;
        console.log(currentMatchIndex.value);
        // 모든 대진이 끝나면 다음 라운드로
        if (currentMatchIndex.value >= matches.value.length) {
            startNextRound(); // 다음 라운드 시작 함수 호출
        } else {
            matches.value[currentMatchIndex.value].forEach((match, index) => {
                const chartInstance = renderChart(
                    chartCanvasRefs[index],
                    match.visualizationData,
                    chartInstances[index]
                );
                chartInstances[index] = chartInstance;
            });
        }
    }, 50); // 선택 확인 딜레이 시간
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

.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.2s ease;
}

.fade-next-round-enter-active,
.fade-next-round-leave-active {
    transition: opacity 0.2s ease-out;
}
</style>
