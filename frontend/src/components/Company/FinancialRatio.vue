<template>
    <div class="indicators-section">
        <table class="indicators">
            <thead>
                <tr>
                    <th>구분</th>
                    <th>계정명</th>
                    <th>2019-12-31</th>
                    <th>2020-12-31</th>
                    <th>2021-12-31</th>
                    <th>지표(%)</th>
                </tr>
            </thead>
            <tbody>
                <!-- 성장성 지표 -->
                <tr>
                    <td rowspan="6">성장성 지표</td>
                    <td>매출액증가율</td>
                    <td>3.3</td>
                    <td>19.4</td>
                    <td>26.6</td>
                    <td></td>
                </tr>
                <tr>
                    <td>영업이익증가율</td>
                    <td>0</td>
                    <td>0</td>
                    <td>9.1</td>
                    <td></td>
                </tr>
                <tr>
                    <td>순이익증가율</td>
                    <td>9.3</td>
                    <td>22.2</td>
                    <td>13.4</td>
                    <td></td>
                </tr>
                <tr>
                    <td>자기자본증가율</td>
                    <td>5.4</td>
                    <td>6.2</td>
                    <td>6.7</td>
                    <td></td>
                </tr>
                <tr>
                    <td>재고자산증가율</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td></td>
                </tr>
                <tr>
                    <td>총자산증가율</td>
                    <td>-25.4</td>
                    <td>11.9</td>
                    <td>29.4</td>
                    <td></td>
                </tr>
                <!-- 수익성 지표 -->
                <tr>
                    <td rowspan="4">수익성 지표</td>
                    <td>순이익률</td>
                    <td>0.5</td>
                    <td>0.5</td>
                    <td>0.5</td>
                    <td></td>
                </tr>
                <tr>
                    <td>영업이익률</td>
                    <td>-0.6</td>
                    <td>0.7</td>
                    <td>0.6</td>
                    <td></td>
                </tr>
                <tr>
                    <td>총자산순이익률(ROA)</td>
                    <td>2.1</td>
                    <td>2.8</td>
                    <td>2.6</td>
                    <td></td>
                </tr>
                <tr>
                    <td>자본금이익률(ROE)</td>
                    <td>5.2</td>
                    <td>6.1</td>
                    <td>6.5</td>
                    <td></td>
                </tr>
                <!-- 안정성 지표 -->
                <tr>
                    <td rowspan="4">안정성 지표</td>
                    <td>유동성비율</td>
                    <td>83.4</td>
                    <td>81.1</td>
                    <td>73.5</td>
                    <td></td>
                </tr>
                <tr>
                    <td>당좌비율</td>
                    <td>83.4</td>
                    <td>81.1</td>
                    <td>73.5</td>
                    <td></td>
                </tr>
                <tr>
                    <td>부채비율</td>
                    <td>113.6</td>
                    <td>124.9</td>
                    <td>172.9</td>
                    <td></td>
                </tr>
                <tr>
                    <td>자기자본비율</td>
                    <td>46.8</td>
                    <td>44.5</td>
                    <td>36.6</td>
                    <td></td>
                </tr>
                <!-- 활동성 지표 -->
                <tr>
                    <td rowspan="4">활동성 지표</td>
                    <td>유형자산회전율</td>
                    <td>93.9</td>
                    <td>85</td>
                    <td>47.4</td>
                    <td></td>
                </tr>
                <tr>
                    <td>자기자본회전율</td>
                    <td>10</td>
                    <td>11.2</td>
                    <td>13.4</td>
                    <td></td>
                </tr>
                <tr>
                    <td>재고자산회전율</td>
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <td></td>
                </tr>
                <tr>
                    <td>매출채권회전율</td>
                    <td>382.7</td>
                    <td>299.7</td>
                    <td>135.7</td>
                    <td></td>
                </tr>
                <tr>
                    <td>총자본회전율</td>
                    <td>3.9</td>
                    <td>5.1</td>
                    <td>5.4</td>
                    <td></td>
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
