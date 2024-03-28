<template>
    <div
        v-if="companyData && companyData.financialStatements && companyData.incomeStatement"
        class="financial-section">
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
        <!-- 손익계산서를 테이블 형식으로 수정 -->
        <h3>손익계산서</h3>
        <table class="financial-statements">
            <thead>
                <tr>
                    <th>항목</th>
                    <th>금액</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>매출액</td>
                    <td>{{ companyData.incomeStatement.revenue }}</td>
                </tr>
                <tr>
                    <td>매출총이익</td>
                    <td>{{ companyData.incomeStatement.grossProfit }}</td>
                </tr>
                <tr>
                    <td>영업이익</td>
                    <td>{{ companyData.incomeStatement.operatingIncome }}</td>
                </tr>
                <tr>
                    <td>영업외수익</td>
                    <td>{{ companyData.incomeStatement.nonOperatingIncome }}</td>
                </tr>
                <tr>
                    <td>영업외비용</td>
                    <td>{{ companyData.incomeStatement.nonOperatingExpenses }}</td>
                </tr>
                <tr>
                    <td>당기순이익</td>
                    <td>{{ companyData.incomeStatement.netIncome }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const companyData = ref(null);

onMounted(() => {
    getCompanyDetail();
});

const getCompanyDetail = () => {
    // 더미 데이터 설정
    companyData.value = {
        financialStatements: [
            {
                accountName: '현금 및 현금성 자산',
                currentAssets: '10,000',
                nonCurrentAssets: '0',
                totalAssets: '10,000',
                currentLiabilities: '2,000',
                nonCurrentLiabilities: '3,000',
                totalLiabilities: '5,000',
                capital: '1,000',
                totalEquity: '5,000',
            },
            {
                accountName: '유형자산',
                currentAssets: '0',
                nonCurrentAssets: '20,000',
                totalAssets: '20,000',
                currentLiabilities: '4,000',
                nonCurrentLiabilities: '6,000',
                totalLiabilities: '10,000',
                capital: '2,000',
                totalEquity: '10,000',
            },
        ],
        incomeStatement: {
            revenue: '30,000',
            grossProfit: '20,000',
            operatingIncome: '10,000',
            nonOperatingIncome: '2,000',
            nonOperatingExpenses: '1,000',
            netIncome: '11,000',
        },
    };
};
</script>

<style scoped>
@import url('@/assets/information.css');
</style>
