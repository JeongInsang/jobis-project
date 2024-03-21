<template>
    <div>소비자 기업월드컵 결과 및 결과에 따른 기업 추천 페이지</div>
    <CompanyChoice />
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import CompanyChoice from '@/components/Company/CompanyChoice.vue';

const store = useCounterStore();
</script>

<style scoped></style>
