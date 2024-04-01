<template>
    <div>청년친화강소기업 보여주기</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import youthCompany from '@/components/Company/youthCompany.vue';

const companyData = ref(null);
const accessToken = localStorage.getItem('accessToken');
const store = useCounterStore();

const getYouthCompanyInformation = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/youthcompany/1006`,
    })
        .then((res) => {
            // 청년친화강소기업 정보를 가져와서 처리
            console.log(res.data);
        })
        .catch((err) => {
            console.log(err);
        });
};
</script>

<style scoped></style>
