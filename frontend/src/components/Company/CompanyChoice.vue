<template>
    <div>
        <h1 v-if="!winner">Ideal Type World Cup</h1>
        <button v-if="!winner" @click="startTournament">Start Tournament</button>
        <div v-if="!winner && selectedCards.length === 2" class="selected-cards">
            <div v-for="card in selectedCards" :key="card.id" class="card">
                <img :src="card.image" :alt="card.name" />
            </div>
            <button @click="moveNextRound">Next Round</button>
        </div>
        <h1 v-if="winner">{{ winner.name }} Wins!</h1>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const cards = ref([]);
const selectedCards = ref([]);
const winner = ref(null);

// 더미 데이터 생성
const generateDummyData = () => {
    const dummyCards = [];
    for (let i = 1; i <= 32; i++) {
        dummyCards.push({
            id: i,
            name: `Person ${i}`,
            image: `https://via.placeholder.com/150?text=Person${i}`,
        });
    }
    return dummyCards;
};

const startTournament = () => {
    const shuffledCards = generateDummyData().sort(() => Math.random() - 0.5);
    selectedCards.value = shuffledCards.slice(0, 2);
    cards.value = shuffledCards.slice(2, 18);
};

const moveNextRound = () => {
    const shuffledCards = cards.value.sort(() => Math.random() - 0.5);
    selectedCards.value = shuffledCards.slice(0, 2);
    cards.value = shuffledCards.slice(2);
    if (cards.value.length === 0) {
        winner.value = selectedCards.value[0];
    }
};
</script>

<style scoped>
.selected-cards {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.card {
    width: 150px;
    margin: 10px;
}

.card img {
    width: 100%;
}

h1 {
    margin-bottom: 20px;
}
</style>
