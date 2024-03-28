<template>
    <div>
        <ul>
            <li v-for="item in items" :key="item.id">{{ item.name }}</li>
        </ul>
        <button @click="prevPage" :disabled="currentPage === 1">Previous</button>
        <span>{{ currentPage }}</span>
        <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
    </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue';
import axios from 'axios';

const items = ref([]);
const currentPage = ref(1);
const pageSize = 10; // 페이지당 아이템 수
let totalPages = 1;

const fetchData = async () => {
    try {
        const response = await axios.get(
            `/api/data?page=${currentPage.value}&pageSize=${pageSize}`
        );
        items.value = response.data.items;
        totalPages = Math.ceil(response.data.totalCount / pageSize);
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

const nextPage = () => {
    if (currentPage.value < totalPages) {
        currentPage.value++;
    }
};

const prevPage = () => {
    if (currentPage.value > 1) {
        currentPage.value--;
    }
};

watch(currentPage, fetchData);

onMounted(fetchData);
</script>

<style scoped></style>
