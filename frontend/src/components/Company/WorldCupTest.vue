<template>
    <div class="container">
        <div>
            <button @click="startTournament" v-if="!isTournamentStarted">대진 시작</button>
            <button @click="resetTournament" v-if="winner">다시하기</button>
            <!-- 대진이 시작되고, 우승자가 아직 결정되지 않았을 때 대진 표시 -->
            <div v-if="!winner">
                <h2 style="text-align: center">{{ currentRound }}</h2>
                <transition name="fade" mode="out-in">
                    <div
                        v-if="currentMatchIndex < matches.length"
                        class="current-match-display"
                        :key="currentMatchIndex">
                        <!-- 기업 카드들 -->
                        <div class="card" @click="selectWinner(currentMatchIndex, 0)">
                            <div class="image">
                                <!-- 이미지 컴포넌트 -->
                                <Visualization />
                            </div>
                            <div class="text">
                                <span>{{ matches[currentMatchIndex][0].name }}</span>
                                <!-- 기업 정보 표시 -->
                                <p
                                    v-for="(value, key) in matches[currentMatchIndex][0].details"
                                    :key="key">
                                    {{ key }} : {{ value }}
                                </p>
                            </div>
                        </div>
                        <div class="card" @click="selectWinner(currentMatchIndex, 1)">
                            <div class="image">
                                <!-- 이미지 컴포넌트 -->
                                <Visualization />
                            </div>
                            <div class="text">
                                <span>{{ matches[currentMatchIndex][1].name }}</span>
                                <!-- 기업 정보 표시 -->
                                <p
                                    v-for="(value, key) in matches[currentMatchIndex][1].details"
                                    :key="key">
                                    {{ key }} : {{ value }}
                                </p>
                            </div>
                        </div>
                    </div>
                </transition>
            </div>
            <!-- 우승자 표시 -->
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
import Visualization from './Visualization.vue';

const companies = ref([]);
const matches = ref([]);
const winners = ref([]);
const winner = ref(null);
const currentMatchIndex = ref(0);
let currentRound = ref('16강');
const isTournamentStarted = ref(false);

// 백엔드에서 기업 리스트를 가져오는 함수
const fetchCompanies = async () => {
    try {
        const response = await axios.get('YOUR_BACKEND_ENDPOINT/companies');
        companies.value = response.data; // 가정: response.data는 기업 객체 배열
        createMatches();
    } catch (error) {
        console.error('Failed to fetch companies:', error);
    }
};

// 대진 생성 함수
const createMatches = () => {
    matches.value = [];
    for (let i = 0; i < companies.value.length; i += 2) {
        matches.value.push([companies.value[i], companies.value[i + 1]]);
    }
};

// 대진 시작, 재설정, 우승자 선택, 다음 라운드 시작 함수는 이전 설명과 동일하게 구현합니다.
// 필요에 따라 일부 로직을 조정하여 데이터 구조와 맞춥니다.

onMounted(() => {
    fetchCompanies();
});
</script>

<style scoped>
@import url('@/assets/choice.css');
/* 스타일링은 생략합니다. 필요에 따라 추가하세요. */
</style>
