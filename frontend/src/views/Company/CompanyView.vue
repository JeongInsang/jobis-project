<template>
    <CompanyChoice />
    <!-- <WorldCupTest /> -->
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import CompanyChoice from '@/components/Company/CompanyChoice.vue';
import WorldCupTest from '@/components/Company/WorldCupTest.vue';

const store = useCounterStore();
</script>

<style scoped></style>
