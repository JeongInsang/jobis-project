<template>
    <div class="container">
        <div class="top-section">
            <CompanyInformation class="company-info" />
            <div class="visualization card">
                <h3>기업 지표 시각화 그래프</h3>
                <CompanyVisualization />
            </div>
        </div>
    </div>
    <div class="bottom-section">
        <youthCompany />
        <BalanceSheet />
        <FinancialRatio />
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
import CompanyVisualization from '@/components/Company/CompanyVisualization.vue';
import CompanyInformation from '@/components/Company/CompanyInformation.vue';
import BalanceSheet from '@/components/Company/BalanceSheet.vue';
import FinancialRatio from '@/components/Company/FinancialRatio.vue';
import youthCompany from '@/components/Company/youthCompany.vue';
// params로 쏜 CompanyId 받음 => 추후에 axios 쏠 때 이 companyId 값으로 쏘면 됨
import { useRoute } from 'vue-router';

const youthCompanyData = ref(null);
const accessToken = localStorage.getItem('accessToken');
const store = useCounterStore();
const API_URL = store.API_URL;

onMounted(() => {
    getYouthCompanyInformation();
});

const getYouthCompanyInformation = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/youthcompany/1006`,
    })
        .then((res) => {
            // 청년친화강소기업 정보를 가져와서 처리
            console.log(res.data);
            youthCompanyData.value = res.data;
        })
        .catch((err) => {
            console.log(err);
        });
};
const route = useRoute();
// const companyId = ref(route.params);
// console.log(companyId.value);
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
}
.card {
    cursor: pointer;
    margin-top: 10px;
    width: 100%;
    height: 100%;
    background: rgb(255, 255, 255);
    border-radius: 5px;
    border: 1px solid rgba(0, 0, 255, 0.2);
    transition: all 0.2s;
    box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, 0.2);
    margin-bottom: 2rem;
}

/* .card:hover {
    box-shadow: -12px 12px 2px -1px rgba(0, 0, 255, 0.2);
} */
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
    margin: 10px;
    padding: 10px;
    position: relative;
    background: #fff;
    width: 30rem;
    height: 35rem;
    border-radius: 8px;
    cursor: pointer;
    transition: all 12ms;
    overflow: hidden;
    border: 1px solid #dddddd;
    box-shadow: 0px 1px 13px rgba(0, 0, 0, 0.1);
}

/* Visualization 컴포넌트의 크기 조정이 필요할 경우 추가 스타일링을 적용합니다. */

.bottom-section {
    width: 85%;
    margin-left: 7rem;
    display: flex;
    flex-direction: column;
    align-items: flex-start; /* 가운데 정렬 */
    /* License와 News 컴포넌트가 세로로 나열되도록 합니다. */
}
</style>
