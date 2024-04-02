<template>
    <div class="card1" v-if="financialStatements && quarter !== null">
        <div class="company-info">
            <div class="financial-section">
                <div>
                    <h3>재무제표</h3>
                    <table class="indicators">
                        <thead>
                            <tr>
                                <th>항목</th>
                                <th>{{ quarter[0].firstQuarterDate }}</th>
                                <th>{{ quarter[0].secondQuarterDate }}</th>
                                <th>{{ quarter[0].thirdQuarterDate }}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>유동부채</td>
                                <td>{{ formatNumber(financialStatements[0].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[0].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[0].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>비유동부채</td>
                                <td>{{ formatNumber(financialStatements[1].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[1].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[1].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>부채총계</td>
                                <td>{{ formatNumber(financialStatements[2].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[2].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[2].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>자본금</td>
                                <td>{{ formatNumber(financialStatements[3].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[3].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[3].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>자본금잉여금</td>
                                <td>{{ formatNumber(financialStatements[4].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[4].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[4].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>이익잉여금</td>
                                <td>{{ formatNumber(financialStatements[5].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[5].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[5].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>자본조정</td>
                                <td>{{ formatNumber(financialStatements[6].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[6].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[6].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>자본총계</td>
                                <td>{{ formatNumber(financialStatements[7].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[7].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[7].thirdQuarter) }}</td>
                            </tr>
                        </tbody>
                    </table>
                    <div>
                        <div class="card1" v-if="financialStatements && quarter !== null">
                            <!-- Your existing HTML code -->
                        </div>
                        <canvas id="salesChart"></canvas>
                    </div>
                    <h3>손익계산서</h3>
                    <table class="indicators">
                        <thead>
                            <tr>
                                <th>항목</th>
                                <th>{{ quarter[0].firstQuarterDate }}</th>
                                <th>{{ quarter[0].secondQuarterDate }}</th>
                                <th>{{ quarter[0].thirdQuarterDate }}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>매출액</td>
                                <td>{{ formatNumber(financialStatements[8].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[8].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[8].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>매출총이익</td>
                                <td>{{ formatNumber(financialStatements[9].firstQuarter) }}</td>
                                <td>
                                    {{ formatNumber(financialStatements[9].secondQuarter) }}
                                </td>
                                <td>{{ formatNumber(financialStatements[9].thirdQuarter) }}</td>
                            </tr>
                            <tr>
                                <td>영업이익</td>
                                <td>
                                    {{ formatNumber(financialStatements[10].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[10].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[10].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>영업외수익</td>
                                <td>
                                    {{ formatNumber(financialStatements[11].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[11].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[11].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>영업외비용</td>
                                <td>
                                    {{ formatNumber(financialStatements[12].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[12].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[12].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>당기순이익</td>
                                <td>
                                    {{ formatNumber(financialStatements[13].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[13].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[13].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>현금흐름표</td>
                                <td>
                                    {{ formatNumber(financialStatements[14].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[14].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[14].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>영업활동으로 인한 현금흐름</td>
                                <td>
                                    {{ formatNumber(financialStatements[15].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[15].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[15].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>투자활동으로 인한 현금흐름</td>
                                <td>
                                    {{ formatNumber(financialStatements[16].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[16].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[16].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>재무활동으로 인한 현금흐름</td>
                                <td>
                                    {{ formatNumber(financialStatements[17].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[17].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[17].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>현금의 증가감소</td>
                                <td>
                                    {{ formatNumber(financialStatements[18].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[18].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[18].thirdQuarter) }}
                                </td>
                            </tr>
                            <tr>
                                <td>기말현금</td>
                                <td>
                                    {{ formatNumber(financialStatements[19].firstQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[19].secondQuarter) }}
                                </td>
                                <td>
                                    {{ formatNumber(financialStatements[19].thirdQuarter) }}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useCounterStore } from '@/stores/counter';
import Chart from 'chart.js/auto';

const store = useCounterStore();
const API_URL = store.API_URL;
const financialStatements = ref(null);
const quarter = ref(null);

onMounted(() => {
    getCompanySheet();
    getCompanyQuarter();
});

const getCompanySheet = () => {
    axios
        .get(`${API_URL}/api/company/statement/1006`)
        .then((res) => {
            financialStatements.value = res.data.data;
            console.log('Financial Statements:', financialStatements.value);
            renderChart();
        })
        .catch((err) => {
            console.error('Error fetching financial statements:', err);
        });
};

const getCompanyQuarter = () => {
    axios
        .get(`${API_URL}/api/company/quarter/1006`)
        .then((res) => {
            quarter.value = res.data.data;
            console.log('Quarter:', quarter.value);
        })
        .catch((err) => {
            console.error('Error fetching quarter:', err);
        });
};

const formatNumber = (value) => {
    return value ? parseFloat(value).toLocaleString() : '-';
};

const renderChart = () => {
    if (!financialStatements.value || !quarter.value) {
        console.error('Financial statements or quarter data is not available');
        return;
    }

    const salesData = financialStatements.value.map((item) => parseFloat(item.firstQuarter));
    const labels = quarter.value.map((item) => item.firstQuarterDate);

    const ctx = document.getElementById('salesChart');
    if (!ctx) {
        console.error('Canvas element not found');
        return;
    }

    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: '매출액',
                    data: salesData,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1,
                },
            ],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                },
            },
        },
    });
};
</script>
<style scoped>
@import url('@/assets/sheet.css');
</style>
