<template>
    <div>
        <h1>Data Page {{ page + 1 }}</h1>
        <ul>
            <li v-for="(item, index) in data" :key="index">
                {{ item.name }} - {{ item.address }} - {{ item.type }}
            </li>
        </ul>
        <button @click="previousPage" :disabled="page === 0">Previous Page</button>
        <button @click="nextPage">Next Page</button>
    </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
// 기업 리스트 받을 변수
const data = ref([]);
const store = useCounterStore();
const page = ref(store.currentPage);
const size = ref(null);
const keyword = ref('');
const type = ref('');
const location = ref('');
const API_URL = store.API_URL;

const fetchData = async () => {
    console.log(page.value);
    console.log(store.currentPage);
    try {
        const response = await axios.get(
            `${API_URL}/api/company/search?page=${page.value}&size=${size.value}&keyword=${keyword.value}&location=${location.value}&type=${type.value}`
        );
        data.value = response.data.data.content;
        console.log(data.value, '받아온 데이터리스트');
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

const previousPage = () => {
    if (page.value > 0) {
        page.value--;
        store.currentPage--;
    }
};

const nextPage = () => {
    page.value++;
    store.currentPage++;
};

onMounted(fetchData);

watch(
    () => page.value,
    () => {
        fetchData();
    }
);
</script>
