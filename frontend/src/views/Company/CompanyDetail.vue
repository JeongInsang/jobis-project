<template>
    <div class="container">
        <div class="top-section">
            <CompanyInformation class="company-info" />
            <Visualization class="visualization" />
        </div>
    </div>
    <div class="bottom-section">
        <License />
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

<style scoped>
.container {
    display: flex;
    flex-direction: column;
}

.top-section {
    display: flex;
    width: 100%;
}

.company-info {
    width: 60%;
    /* 세로 크기가 가변적이므로, 필요에 따라 min-height 등을 설정할 수 있습니다. */
}

.visualization {
    width: 40%;
    /* Visualization 컴포넌트의 크기 조정이 필요할 경우 추가 스타일링을 적용합니다. */
}

.bottom-section {
    margin-left: 7rem;
    display: flex;
    flex-direction: column;
    align-items: flex-start; /* 가운데 정렬 */
    /* License와 News 컴포넌트가 세로로 나열되도록 합니다. */
}
</style>
