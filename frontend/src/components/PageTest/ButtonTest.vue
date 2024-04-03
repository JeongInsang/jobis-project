<template>
    <div>1</div>
    <button class="Btn"><i class="bi bi-star fs-5 star"></i></button>
    <div v-if="isScarp" @click="scrap">
        <button class="Btn"><i class="bi bi-star fs-5 star"></i></button>
    </div>
    <div v-else @click="scrap">
        <button class="Btn"><i class="bi bi-star-fill fs-5 star"></i></button>
    </div>

    <div class="card">
        <h2>CARD</h2>
    </div>
</template>

<script setup>
import { ref } from 'vue';
const isScarp = ref(false);

const scrap = () => {
    isScarp.value = !isScarp.value;
};
</script>

<style scoped>
@import url('@/assets/scrapbtn.css');

/* 카드 css */
.card {
    width: 190px;
    height: 254px;
    /* background: #07182e; */
    position: relative;
    display: flex;
    place-content: center;
    place-items: center;
    overflow: hidden;
    border-radius: 20px;
}

.card h2 {
    z-index: 1;
    color: white;
    font-size: 2em;
}

.card::before {
    content: '';
    position: absolute;
    width: 100px;
    background-image: linear-gradient(180deg, rgb(0, 183, 255), rgb(255, 48, 255));
    height: 130%;
    animation: rotBGimg 3s linear infinite;
    transition: all 0.2s linear;
}

@keyframes rotBGimg {
    from {
        transform: rotate(0deg);
    }

    to {
        transform: rotate(360deg);
    }
}

.card::after {
    content: '';
    position: absolute;
    /* background: #07182e; */
    inset: 5px;
    border-radius: 15px;
}
/* .card:hover:before {
  background-image: linear-gradient(180deg, rgb(81, 255, 0), purple);
  animation: rotBGimg 3.5s linear infinite;
} */
</style>
