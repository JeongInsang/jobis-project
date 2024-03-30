<template>
    <div class="indicators-section">
        <table class="indicators">
            <thead v-if="quarter !== null">
                <tr>
                    <th>구분</th>
                    <th>계정명</th>
                    <th>{{ quarter[0].firstQuarterDate }}</th>
                    <th>{{ quarter[0].secoundQuarterDate }}</th>
                    <th>{{ quarter[0].thirdQuarterDate }}</th>
                </tr>
            </thead>
            <tbody v-if="companyRate !== null">
                <!-- 성장성 지표 -->
                <tr>
                    <td rowspan="6">성장성 지표</td>
                    <td>매출액증가율</td>
                    <td>{{ companyRate[0].firstQuarter }}</td>
                    <td>{{ companyRate[0].secondQuarter }}</td>
                    <td>{{ companyRate[0].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>영업이익증가율</td>
                    <td>{{ companyRate[1].firstQuarter }}</td>
                    <td>{{ companyRate[1].secondQuarter }}</td>
                    <td>{{ companyRate[1].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>순이익증가율</td>
                    <td>{{ companyRate[2].firstQuarter }}</td>
                    <td>{{ companyRate[2].secondQuarter }}</td>
                    <td>{{ companyRate[2].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>자기자본증가율</td>
                    <td>{{ companyRate[3].firstQuarter }}</td>
                    <td>{{ companyRate[3].secondQuarter }}</td>
                    <td>{{ companyRate[3].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>재고자산증가율</td>
                    <td>{{ companyRate[4].firstQuarter }}</td>
                    <td>{{ companyRate[4].secondQuarter }}</td>
                    <td>{{ companyRate[4].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>총자산증가율</td>
                    <td>{{ companyRate[5].firstQuarter }}</td>
                    <td>{{ companyRate[5].secondQuarter }}</td>
                    <td>{{ companyRate[5].thirdQuarter }}</td>
                </tr>
                <!-- 수익성 지표 -->
                <tr>
                    <td rowspan="4">수익성 지표</td>
                    <td>순이익률</td>
                    <td>{{ companyRate[6].firstQuarter }}</td>
                    <td>{{ companyRate[6].secondQuarter }}</td>
                    <td>{{ companyRate[6].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>영업이익률</td>
                    <td>{{ companyRate[7].firstQuarter }}</td>
                    <td>{{ companyRate[7].secondQuarter }}</td>
                    <td>{{ companyRate[7].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>총자산순이익률(ROA)</td>
                    <td>{{ companyRate[8].firstQuarter }}</td>
                    <td>{{ companyRate[8].secondQuarter }}</td>
                    <td>{{ companyRate[8].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>자본금이익률(ROE)</td>
                    <td>{{ companyRate[9].firstQuarter }}</td>
                    <td>{{ companyRate[9].secondQuarter }}</td>
                    <td>{{ companyRate[9].thirdQuarter }}</td>
                </tr>
                <!-- 안정성 지표 -->
                <tr>
                    <td rowspan="4">안정성 지표</td>
                    <td>유동성비율</td>
                    <td>{{ companyRate[10].firstQuarter }}</td>
                    <td>{{ companyRate[10].secondQuarter }}</td>
                    <td>{{ companyRate[10].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>당좌비율</td>
                    <td>{{ companyRate[11].firstQuarter }}</td>
                    <td>{{ companyRate[11].secondQuarter }}</td>
                    <td>{{ companyRate[11].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>부채비율</td>
                    <td>{{ companyRate[12].firstQuarter }}</td>
                    <td>{{ companyRate[12].secondQuarter }}</td>
                    <td>{{ companyRate[12].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>자기자본비율</td>
                    <td>{{ companyRate[13].firstQuarter }}</td>
                    <td>{{ companyRate[13].secondQuarter }}</td>
                    <td>{{ companyRate[13].thirdQuarter }}</td>
                </tr>
                <!-- 활동성 지표 -->
                <tr>
                    <td rowspan="5">활동성 지표</td>
                    <td>유형자산회전율</td>
                    <td>{{ companyRate[14].firstQuarter }}</td>
                    <td>{{ companyRate[14].secondQuarter }}</td>
                    <td>{{ companyRate[14].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>자기자본회전율</td>
                    <td>{{ companyRate[15].firstQuarter }}</td>
                    <td>{{ companyRate[15].secondQuarter }}</td>
                    <td>{{ companyRate[15].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>재고자산회전율</td>
                    <td>{{ companyRate[16].firstQuarter }}</td>
                    <td>{{ companyRate[16].secondQuarter }}</td>
                    <td>{{ companyRate[16].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>매출채권회전율</td>
                    <td>{{ companyRate[17].firstQuarter }}</td>
                    <td>{{ companyRate[17].secondQuarter }}</td>
                    <td>{{ companyRate[17].thirdQuarter }}</td>
                </tr>
                <tr>
                    <td>총자본회전율</td>
                    <td>{{ companyRate[18].firstQuarter }}</td>
                    <td>{{ companyRate[18].secondQuarter }}</td>
                    <td>{{ companyRate[18].thirdQuarter }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore();
const companyRate = ref(null);
const accessToken = localStorage.getItem('accessToken');
const API_URL = store.API_URL;
const quarter = ref(null);
onMounted(() => {
    getCompanyRatio();
    getCompanyQuarter();
});

const getCompanyRatio = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/rate/10`,
        // headers: {
        //     Authorization: `Bearer ${accessToken}`,
        // },
    })
        .then((res) => {
            companyRate.value = res.data.data;
            console.log(companyRate.value);
        })
        .catch((err) => {
            console.error(err);
        });
};

const getCompanyQuarter = () => {
    axios({
        method: 'get',
        url: `${API_URL}/api/company/quarter/10`,
        // headers: {
        //     Authorization: `Bearer ${accessToken}`,
        // },
    })
        .then((res) => {
            quarter.value = res.data.data;
            console.log(quarter.value);
        })
        .catch((err) => {
            console.error(err);
        });
};
</script>

<style scoped>
@import url('@/assets/information.css');
</style>
