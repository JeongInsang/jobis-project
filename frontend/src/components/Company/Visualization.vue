<template>
    <div class="chart-canvas">
        <h2>기업지표 시각화정보</h2>
        <canvas ref="chartCanvas"></canvas>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';
import 'chartjs-adapter-date-fns';
import 'chartjs-plugin-datalabels';

const chartCanvas = ref(null);
const visualizationData = ref({
    안정성: 9,
    성장성: -9,
    활동성: 4,
    수익성: -2,
    가용성: 5,
});

onMounted(() => {
    const ctx = chartCanvas.value.getContext('2d');
    console.log(visualizationData.value),
        new Chart(ctx, {
            type: 'radar',
            data: {
                labels: Object.keys(visualizationData.value),
                datasets: [
                    {
                        label: '점수',
                        data: Object.values(visualizationData.value),
                        backgroundColor: 'rgba(0, 123, 255, 0.7)',
                        borderColor: 'rgba(0, 123, 255, 1)',
                        borderWidth: 1,
                    },
                ],
            },
            options: {
                scales: {
                    r: {
                        max: 9,
                        min: -9,
                        stepSize: 3,
                    },
                },
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    datalabels: {
                        display: true, // 데이터 라벨 표시 여부
                        color: '#000',
                        font: {
                            weight: 'bold',
                        },
                    },
                },
            },
        });
});
</script>

<style scoped>
.chart-canvas {
    width: 30rem; /* 원하는 너비로 설정 */
    height: 20rem; /* 원하는 높이로 설정 */
}
</style>
