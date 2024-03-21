<template>
    <div>
        <CompanyInformation />
        <Visualization />
    </div>
    <div></div>
    <div>
        <License />
    </div>
    <div>
        <News />
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import License from '@/components/Company/License.vue';
import News from '@/components/Company/News.vue';
import Visualization from '@/components/Company/Visualization.vue';
import CompanyInformation from '@/components/Company/CompanyInformation.vue';

const store = useCounterStore();
const accessToken = localStorage.getItem('accessToken');
</script>

<style scoped></style>
