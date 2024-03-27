<template>
    <div class="financial-section">
        <h3>대차대조표</h3>
        <table class="financial-statements">
            <thead>
                <tr>
                    <th>계정명</th>
                    <th>유동자산</th>
                    <th>비유동자산</th>
                    <th>자산총계</th>
                    <th>유동부채</th>
                    <th>비유동부채</th>
                    <th>부채총계</th>
                    <th>자본금</th>
                    <th>자본총계</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in companyData.financialStatements" :key="index">
                    <td>{{ item.accountName }}</td>
                    <td>{{ item.currentAssets }}</td>
                    <td>{{ item.nonCurrentAssets }}</td>
                    <td>{{ item.totalAssets }}</td>
                    <td>{{ item.currentLiabilities }}</td>
                    <td>{{ item.nonCurrentLiabilities }}</td>
                    <td>{{ item.totalLiabilities }}</td>
                    <td>{{ item.capital }}</td>
                    <td>{{ item.totalEquity }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import BalanceSheet from '@/components/Company/BalanceSheet.vue';
import FrinancialRatio from '@/components/Company/FinancialRatio.vue';

const companyData = ref(null);
const accessToken = localStorage.getItem('accessToken');

onMounted(() => {
    getCompanyDetail();
});

const getCompanyDetail = () => {
    axios({
        method: 'get',
        url: 'YOUR_API_ENDPOINT',
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
        .then((res) => {
            companyData.value = res.data;
        })
        .catch((err) => {
            console.error(err);
        });
};
</script>

<style scoped>
@import url('@/assets/information.css');
</style>
