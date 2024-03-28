<template>
    <div>
        <h2>특허정보</h2>
        <ul>
            <li v-for="patent in patents" :key="patent.id">
                {{ patent.title }} - {{ patent.date }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const patents = ref([
    { id: 1, title: 'Patent 1', date: '2022-01-01' },
    { id: 2, title: 'Patent 2', date: '2022-02-01' },
    { id: 3, title: 'Patent 3', date: '2022-03-01' },
    // 더 많은 특허 리스트 아이템들...
]);
</script>

<style scoped>
/* 스타일링 */
</style>

<!-- <template>
    <div>특허 리스트 제공</div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore();
const accessToken = localStorage.getItem('accessToken');
const LicenseList = ref([]);

const getLicenseData = function () {
    axios({
        method: 'get',
        url: `${store.API_URL}/patent/${store.companyId}/`,
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            LicenseList.value = res.data;
            console.log(res.data);
        })
        .catch((err) => {
            console.log(err);
        });
};

onmounted(() => {
    getLicenseData();
})
</script>

<style scoped></style> -->
