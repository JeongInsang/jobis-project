<template>
  <div>
    <!-- 16강 부터 결승까지의 라운드를 표시합니다. -->
    <div v-for="(round, index) in rounds" :key="index">
      <h2 v-if="!isFinalRound(round)"> {{ round.title }} </h2>
      <div class="matchups">
        <!-- 각 라운드에서의 매치업을 표시합니다. -->
        <div v-for="(matchup, i) in round.matches" :key="i" class="matchup">
          <div class="company-card" v-for="company in matchup" :key="company">
            <!-- 각 기업 카드를 표시합니다. -->
            <img :src="getCompanyImage(company)" alt="company logo" />
            <h3>{{ company }}</h3>
            <button @click="selectWinner(company, matchup.filter(c => c !== company)[0])">선택하기</button>
          </div>
        </div>
      </div>
    </div>

    <!-- 최종 우승자를 표시합니다. -->
    <h2 v-if="finalWinner">최종 우승자: {{ finalWinner }}</h2>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 기업 목록
const companies = [
  '기업1', '기업2', '기업3', '기업4', '기업5', '기업6', '기업7', '기업8',
  '기업9', '기업10', '기업11', '기업12', '기업13', '기업14', '기업15', '기업16',
  '기업17', '기업18', '기업19', '기업20', '기업21', '기업22', '기업23', '기업24',
  '기업25', '기업26', '기업27', '기업28', '기업29', '기업30', '기업31', '기업32',
];

// 선택된 기업들을 저장합니다.
const selectedCompanies = ref(companies.slice(0, 16));

// 라운드 정보를 저장합니다.
const rounds = ref([
  { title: '16강', matches: chunkArray(selectedCompanies.value, 2) },
  { title: '8강', matches: [] },
  { title: '4강', matches: [] },
  { title: '결승', matches: [] }
]);

// 최종 우승자를 저장합니다.
const finalWinner = ref('');

// 기업 카드의 이미지 경로를 가져옵니다.
const getCompanyImage = (company) => `@/img/${company.toLowerCase().replace(/\s+/g, '')}.png`;

// 라운드가 최종 라운드인지 확인합니다.
const isFinalRound = (round) => round.title === '결승';

// 라운드 진행 버튼을 클릭했을 때의 동작입니다.
const proceedToNextRound = () => {
  const currentRoundIndex = rounds.value.findIndex(round => round.matches.length > 0);
  const nextRoundIndex = currentRoundIndex + 1;
  if (nextRoundIndex < rounds.value.length) {
    rounds.value[nextRoundIndex].matches = chunkArray(rounds.value[currentRoundIndex].matches.map(match => selectWinner(match)), 2);
  } else {
    finalWinner.value = rounds.value[currentRoundIndex].matches[0][0];
  }
};

// 선택된 기업을 반환하고 이긴 기업은 결과에 남기고 진 기업은 제거합니다.
const selectWinner = (match) => {
  return match.map((company, index) => {
    if (index === 0) {
      return company;
    } else {
      return null;
    }
  }).filter(company => company !== null);
};

// 배열을 여러 개의 작은 배열로 나누는 함수입니다.
const chunkArray = (arr, size) => {
  return Array.from({ length: Math.ceil(arr.length / size) }, (_, i) => {
    const start = i * size;
    return arr.slice(start, start + size);
  });
};
</script>

<style scoped>
.matchup {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
}

.company-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.company-card img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}

.company-card button {
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
}

.company-card button:hover {
  background-color: #0056b3;
}
</style>
