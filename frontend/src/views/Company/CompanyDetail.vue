<template>
    <div class="container">
        <div class="top-section">
            <CompanyInformation class="company-info" />
            <div class="card5">
                <h3>기업 지표 시각화 그래프</h3>
                <CompanyVisualization />
            </div>
        </div>
    </div>
    <div class="bottom-section">
        <youthCompany v-if="companyData.youthCompany" />
        <BalanceSheet />
        <FinancialRatio />
        <!-- patents가 null일 때 License 컴포넌트를 보이지 않도록 v-if를 사용하여 조건부 렌더링합니다. -->
        <License v-if="patents && patents !== null" />
        <!-- <News /> -->
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
import { useRoute } from 'vue-router';
import { getCompanyDetail } from '@/apis/api/company';
import { getPatent } from '@/apis/api/patent';

const route = useRoute();
const youthCompanyData = ref(null);
const accessToken = localStorage.getItem('accessToken');
const store = useCounterStore();
const API_URL = store.API_URL;
const companyId = ref(route.params.companyId);
const patents = ref(null);

onMounted(() => {
    getYouthCompanyInformation();
    getCompanyInformation();
    getLicenseData();
    // 페이지 스크롤을 최상단으로 이동
    window.scrollTo(0, 0);
});
const companyData = ref([]);

const getCompanyInformation = () => {
    getCompanyDetail(companyId.value,
        (res) => {
            companyData.value = res.data.data;
            scraped.value = res.data.data.scraped;
            if (companyData.value.youthCompany) {
                getYouthCompanyInformation();
            }
            console.log(companyData.value, 'companyData 입니다.');       
        },
        (err) => {
            console.log(err);
        }
    );
};

const getYouthCompanyInformation = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/youthcompany/1006`,
    })
        .then((res) => {
            youthCompanyData.value = res.data;
        })
        .catch((err) => {
            console.log(err);
        });
};

const getLicenseData = function () {
    getPatent(companyId.value,
        (res) => {
            patents.value = res.data.data;
            visiblePatents.value = patents.value.slice(0, batchSize);
        },
        (err) => {
            console.log(err)
        }
    );
};
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
}
.card5 {
    width: 100%;
    margin: 10px;
    padding: 10px;
    position: relative;
    background: #fff;
    width: 29rem;
    height: 35rem;
    border-radius: 8px;
    cursor: pointer;
    transition: all 12ms;
    overflow: hidden;
    border: 1px solid #dddddd;
    box-shadow: 0px 1px 13px rgba(0, 0, 0, 0.1);
    cursor: pointer;
    margin-top: 10px;
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
    width: 45%;
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
    /* align-items: flex-start; 가운데 정렬 */
    /* License와 News 컴포넌트가 세로로 나열되도록 합니다. */
}
</style>
