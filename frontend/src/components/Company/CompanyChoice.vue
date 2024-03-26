<template>
    <div class="container">
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
                            <p>이 기업은 30년간 안정적으로 운영된 기업으로 복지가 좋고 그렇다나</p>
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
</template>

<script setup>
import { ref, onMounted } from 'vue';
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
// 선택 확인 딜레이 상태
const selectionConfirmed = ref(false);

// 대진 생성 함수
const createMatches = () => {
    for (let i = 0; i < companies.length; i += 2) {
        matches.value.push([companies[i], companies[i + 1]]);
    }
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
    width: 80%;
    height: 50rem;
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
.card {
    position: relative;
    background: #fff;
    width: 30rem;
    height: 35rem;
    border-radius: 8px;
    cursor: pointer;
    transition: all 120ms;
    overflow: hidden;
    box-shadow: 0px 1px 13px rgba(0, 0, 0, 0.1);
}

.card:active {
    transform: scale(0.95);
}

.card::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    box-shadow: inset 0px 0px 25px 5px rgba(255, 255, 255, 0.5);
    z-index: 2;
}

.card::after {
    content: '선택하기';
    position: absolute;
    bottom: -50%;
    left: 0;
    padding-left: 15px;
    background: #181818;
    width: 100%;
    height: 60px;
    color: #fff;
    line-height: 50px;
    text-transform: uppercase;
    z-index: 2;
    transition: all 0.2s ease-in;
}

.card:hover::after {
    bottom: 0;
}

.card:active::after {
    content: 'Adding !';
    height: 65px;
}

.image {
    position: absolute;
    top: 0; /* 변경된 부분: 상단에 위치하도록 수정 */
    left: 50%;
    width: 100%;
    height: auto;
    filter: drop-shadow(3px 3px 5px #18181815);
    transform: translate(-50%, 0); /* 애니메이션에 맞춰 수정 */
    animation: shoes 1s ease infinite alternate;
    transition: all 0.5s ease-in;
}

@keyframes shoes {
    from {
        top: -0.5%; /* 변경된 부분: 애니메이션 동작을 위해 상단에 위치하도록 수정 */
    }

    to {
        top: 0.5%; /* 변경된 부분: 애니메이션 동작을 위해 상단에 위치하도록 수정 */
    }
}

.card:hover .image {
    width: 150px; /* 이미지의 너비를 줄입니다. */
    height: 100px; /* 이미지의 높이는 자동으로 조정됩니다. */
    transition: width 0.5s ease-in-out height 0.5s ease-in-out; /* 너비 변화에 대한 transition 추가 */
}

.text {
    position: absolute;
    top: 15%;
    left: -80%;
    color: #181818;
    transition: all 0.3s ease-in;
}

.text span {
    font-size: 25px;
    font-weight: 800;
    margin-bottom: 10px;
}

.text p {
    font-size: 18px;
    margin-top: 10px;
}

.card:hover .text {
    top: 15%;
    left: 5%;
}
/* 애니메이션 스타일 */
.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
    opacity: 0;
}
</style>
