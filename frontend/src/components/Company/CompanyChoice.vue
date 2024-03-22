<template>
    <div class="container">
        <div v-if="!winner">
            <!-- 현재 라운드 -->
            <h2 style="text-align: center">{{ currentRound }}</h2>

            <!-- 현재 대진만 표시 -->
            <div v-if="currentMatchIndex < matches.length" class="current-match-display">
                <div class="company-card">
                    <h3>1. {{ matches[currentMatchIndex][0] }}</h3>
                    <button @click="selectWinner(currentMatchIndex, 0)">1번 기업 선택</button>
                </div>
                <div class="company-card">
                    <h3>2. {{ matches[currentMatchIndex][1] }}</h3>
                    <button @click="selectWinner(currentMatchIndex, 1)">2번 기업 선택</button>
                </div>
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
// 대진 목록 배열
const matches = ref([]);
// 각 라운드의 승자들을 저장하는 배열
const winners = ref([]);
// 최종 우승자
const winner = ref('');
// 현재 표시 중인 대진의 인덱스
const currentMatchIndex = ref(0);
// 현재 라운드
let currentRound = ref('16강');

// 대진 생성 함수
const createMatches = () => {
    for (let i = 0; i < companies.length; i += 2) {
        matches.value.push([companies[i], companies[i + 1]]);
    }
};

// 기업 선택하는 함수
const selectWinner = (matchIndex, winnerIndex) => {
    winners.value.push(matches.value[matchIndex][winnerIndex]);

    // 다음 대진으로 이동
    currentMatchIndex.value++;

    // 모든 대진이 끝나면 다음 라운드로
    if (currentMatchIndex.value >= matches.value.length) {
        startNextRound();
    }
};

// 다음 라운드를 시작하는 함수
const startNextRound = () => {
    if (winners.value.length === 1) {
        winner.value = winners.value[0];
    } else {
        // 다음 라운드의 대진을 설정
        matches.value = winners.value
            .map((val, idx, arr) => (idx % 2 === 0 ? [val, arr[idx + 1]] : null))
            .filter(Boolean);
        winners.value = [];
        currentMatchIndex.value = 0; // 다음 라운드의 첫 번째 대진으로 인덱스를 재설정

        // 다음 라운드로 이동
        if (matches.value.length === 4) {
            currentRound.value = '8강';
        } else if (matches.value.length === 2) {
            currentRound.value = '4강';
        } else if (matches.value.length === 1) {
            currentRound.value = '결승';
        }
    }
};

onMounted(() => {
    createMatches();
});
</script>

<style scoped>
.container {
    border: solid #f0f0f0 3px;
    border-radius: 10px;
    width: 60%;
    height: 30rem;
    margin-left: auto;
    margin-right: auto;
}
/* 현재 대진 표시 영역 스타일링 */
.current-match-display {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-bottom: 20px;
}

/* 기업 카드 스타일링 */
.company-card {
    width: 20rem;
    height: 15rem;
    flex-basis: 80%; /* 카드가 부모 컨테이너의 거의 절반을 차지하도록 설정 */
    padding: 20px;
    text-align: center;
    background-color: #f0f0f0; /* 카드 배경색 */
    border-radius: 10px; /* 카드 모서리 둥글게 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 카드에 그림자 효과 추가 */
    transition: transform 0.3s ease; /* 호버 시 변환 효과를 부드럽게 */
}

.company-card:hover {
    transform: translateY(-5px); /* 호버 시 카드가 조금 위로 올라감 */
    cursor: pointer;
}

/* 선택 버튼 스타일링 */
.company-card button {
    margin-top: 10px;
    padding: 10px 20px;
    background-color: #007bff; /* 버튼 배경색 */
    color: white; /* 버튼 글자색 */
    border: none; /* 테두리 제거 */
    border-radius: 5px; /* 버튼 모서리 둥글게 */
    cursor: pointer; /* 마우스 오버 시 커서 변경 */
}

.company-card button:hover {
    background-color: #0056b3; /* 호버 시 버튼 배경색 변경 */
}
</style>
