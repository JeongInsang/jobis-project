<template>
    <!-- 기업선호 월드컵 컴포넌트 -->
    <div class="container1" style="margin-top: 30px;">
        <div style="font-family: 'Pretendard'; justify-content: center; align-self: center">
            <!-- 제목 -->
            <div class="title-container"><h2 style="text-align: center;">기업선호 월드컵</h2></div>
            <div v-if="!winner">
                <!-- 현재 라운드 표시 -->
                <transition :name="isNextRound ? 'fade-next-round' : 'fade'" mode="out-in">
                    <h2 style="text-align: center" :key="currentRound">
                        {{ currentRound }}
                    </h2>
                </transition>
                <!-- 게임 시작 버튼 -->
                <button
                    @click="startTournamentAndgetInfo"
                    v-if="!isTournamentStarted"
                    class="learn-more"
                    style="margin: 0 auto; display: block; background-color: #007bff; color: #fff;"> <!-- 배경색과 글자색 지정 -->
                    게임 시작!
                </button>
                <!-- 현재 경기 표시 -->
                <transition :name="isNextRound ? 'fade-next-round' : 'fade'" mode="out-in">
                    <div
                        v-if="currentMatchIndex < matches.length"
                        class="current-match-display"
                        :key="currentMatchIndex">
                        <div v-for="(match, index) in matches[currentMatchIndex]" :key="index">
                            <!-- 경기 카드 -->
                            <div class="card" @click="selectWinner(currentMatchIndex, index)">
                                <!-- 이미지 -->
                                <img
                                    :src="`src/assets/img/worldcup/${match.worldcupId}.jpg`"
                                    alt="나와라 제발"
                                    class="image" />

                                    <!-- 기업 정보 -->
                                    <div class="text">
                                        <span style="font-size: 20px;">{{ match.name }}</span>
                                        <div>
                                            <p :style="styles.activity">활동성 : {{ match.activity }}</p>
                                            <p :style="styles.efficiency">효율성 : {{ match.efficiency }}</p>
                                            <p :style="styles.growth">성장성 : {{ match.growth }}</p>
                                            <p :style="styles.profitability">수익성 : {{ match.profitability }}</p>
                                            <p :style="styles.stability">안정성 : {{ match.stability }}</p>
                                            <br />
                                        </div>
                                        <span>전체 분석 내용</span>
                                        <p style="width: 380px">{{ match.description }}</p>
                                    </div>

                            </div>
                            <span>전체 분석 내용</span>
                            <p style="width: 380px">{{ match.description }}</p>
                        </div>
                        </div>
                    </div>
                </transition>
            </div>
            <div v-else style="width: 55rem; text-align: center;"> <!-- 우승 기업을 가운데 정렬 -->
                <!-- 수정된 부분: 추가 정보 확인 버튼 클릭 시, winner의 name 정보만을 표시 -->
                <h3>가상기업 <span style="color: red;">{{ winner.name }}</span> (이)가 우승했습니다 !</h3>
                <div v-if="winnerInfoLoaded" style="margin-top: 2rem">
                    <h4 style="margin-bottom: 2rem">성향과 비슷한 추천 기업 목록</h4>
                    <div class="box" style="background-color: #007bff; color: #fff;"></div>
                    <div class="card-container">
                        <div v-for="(info, index) in win" :key="index" class="unique-card">
                            <!-- 기업 카드 -->
                            <div class="background-overlay"></div>
                            <div @click="goCompanyDeatil(info.companyId)" class="card-content">
                                <!-- 기업 정보 -->
                                <div class="card-title">{{ info.name }}</div>
                                <div class="card-description">{{ info.type }}</div>
                                <!-- <div>활동성 : {{ info.factor.activity.toFixed(6) }}</div>
                                <div>안정성 : {{ info.factor.stability.toFixed(6) }}</div>
                                <div>수익성 : {{ info.factor.profitability.toFixed(6) }}</div>
                                <div>성장성 : {{ info.factor.growth.toFixed(6) }}</div>
                                <div>효율성 : {{ info.factor.efficiency.toFixed(6) }}</div> -->
                                <!-- <div class="card-description">{{ info.address }}</div> -->
                            </div>
                        </div>
                    </div>
                    <!-- 다시하기 버튼 -->
                    <button @click="resetTournament" v-if="winner" class="learn-more" style="background-color: #007bff; color: #ffffff;"> <!-- 다시하기 버튼 색상 변경 -->
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
import { useRouter } from 'vue-router';

const router = useRouter();
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

// 게임 시작 및 기업 정보 가져오기
const startTournamentAndgetInfo = function () {
    companiesInfo();
    startTournament();
};

// 기업 상세 정보 페이지로 이동
const goCompanyDeatil = (comapnyId) => [
    router.push({
        name: 'companydetail',
        params: { companyId: comapnyId },
    }),
];

// 기업 정보 가져오기
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

// 우승 기업 정보 가져오기
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

// 경기 생성
const createMatches = () => {
    const newMatches = [];
    for (let i = 0; i < companies.value.length; i += 2) {
        newMatches.push([companies.value[i], companies.value[i + 1]]);
        // console.log(companies.value);
    }
    matches.value = newMatches;
};


// 게임 시작
const isTournamentStarted = ref(false);
const startTournament = () => {
    isTournamentStarted.value = true;
    if (!matches.value.length || currentRound.value === '16강') {
        createMatches();
    }
};

// 게임 재시작
const resetTournament = () => {
    isTournamentStarted.value = false;
    matches.value = [];
    winners.value = [];
    winner.value = '';
    currentMatchIndex.value = 0;
    currentRound.value = '16강';
};


// 다음 경기 시작
const nextMatch = ref(null);
const selectWinner = (matchIndex, winnerIndex) => {
  const selectedMatch = matches.value[matchIndex][winnerIndex];
  winners.value.push(selectedMatch);

  selectionConfirmed.value = false;
  setTimeout(() => {
    selectionConfirmed.value = true;
    currentMatchIndex.value++;
    if (currentMatchIndex.value >= matches.value.length) {
      startNextRound();
    }
  }, 50);
};

// 다음 라운드 시작
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

// 우승자 변경 감지
watch(winner, (newValue) => {
    if (newValue !== '') {
        getWinnerInfo();
    }
});

const getStyle = (match, factor) => {
  const values = [match.activity, match.efficiency, match.growth, match.profitability, match.stability];
  const sortedValues = [...values].sort((a, b) => b - a);
  const highestValue = sortedValues[0];
  const secondHighestValue = sortedValues[1];

  if (match[factor] === highestValue) {
    return { color: 'red' };
  } else if (match[factor] === secondHighestValue) {
    return { color: 'blue' };
  }

  return {}; // 기본 스타일
};

const matchStyles = computed(() => {
  return matches.value.map(match => {
    return {
      match: match,
      styles: {
        activity: getStylesForMatch(match[0]).activity,
        efficiency: getStylesForMatch(match[0]).efficiency,
        growth: getStylesForMatch(match[0]).growth,
        profitability: getStylesForMatch(match[0]).profitability,
        stability: getStylesForMatch(match[0]).stability,
      }
    };
  });
});

// 컴포넌트에서 사용할 수 있도록 `styles`를 반응형 참조로 만듭니다.
const styles = ref({});
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
.image {
  transition: transform 0.5s ease;
}

.card:hover .image {
  transform: scale(0.95); /* 이미지 크기를 95%로 줄입니다 */
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
    text-align: center;
}

.unique-card:hover {
    transform: scale(1.1);
    box-shadow: 0 8px 16px rgba(57, 57, 57, 0.3);
}

.card-content {
    padding: 10px;
    text-align: center; /* 정보 텍스트들을 가운데 정렬 */
    color: #888;
    z-index: 2;
    transition: color 0.3s;
}

.unique-card:hover .card-content {
    color: #ff3300;
}

.background-overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(192, 220, 249, 0.7);
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
    text-align: center;
}

.card-description {
    padding-top: 0;
    padding-bottom: 5px;
    display: block;
    padding-left: 5px;
    font-size: 14px;
    text-align: center; /* 정보 텍스트들을 가운데 정렬 */
}

/* 추가 */
.title-container {
    text-align: center;
    margin-top: 30px;
    position: relative; /* 부모 요소로부터의 상대 위치 설정 */
    /* border: 2px solid #007bff; /* 테두리 컴포넌트의 색상을 #007bff로 설정합니다. */
    /* background-color: #007bff; */
    /* color: #fff; */
    border-radius: 5px; /* 테두리의 둥글기를 조절합니다. */
    padding: 20px; /* 컨테이너 내부의 여백을 설정합니다. */
}

.title-bar {
    background-color: #007bff; /* 파란색 바 색상 */
    height: 3px; /* 바의 높이 */
    width: calc(100% - 32px); /* 부모 요소인 title-container의 너비에서 좌우 패딩을 뺀 값 */
    margin: 0 auto; /* 가운데 정렬 */
    position: absolute; /* 절대 위치 설정 */
    bottom: -5px; /* 바의 하단을 조정하여 제목 아래에 위치하도록 설정 */
    left: 16px; /* 바를 제목 컨테이너의 좌우 패딩에 맞추어 왼쪽으로 이동 */
}

</style>
