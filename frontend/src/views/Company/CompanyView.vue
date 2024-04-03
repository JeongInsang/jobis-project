<template>
    <CompanyChoice />
    <!-- <img src="../../assets/img/유연근무제_TRUE.PNG" alt="" style="width: 15rem" /> -->
    <!-- <WorldCupTest /> -->
    <!-- <VsCompany /> -->
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import CompanyChoice from '@/components/Company/CompanyChoice.vue';
import WorldCupTest from '@/components/Company/WorldCupTest.vue';
import VsCompany from '@/components/Company/VsCompany.vue';

const store = useCounterStore();
</script>

<style scoped></style>
