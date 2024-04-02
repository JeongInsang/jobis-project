<template>
    <div>
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
    성장성: 1.6481,
    활동성: 1.3759,
    수익성: 3.5884,
    안정성: 2.784,
    효율성: 3.226,
});

onMounted(() => {
    const ctx = chartCanvas.value.getContext('2d');
    const dataValues = Object.values(visualizationData.value);
    // console.log(visualizationData.value),
    const normalizedData = dataValues.map((value) => Math.min(value, 9)); // 최대값을 9로 제한
    new Chart(ctx, {
        type: 'radar',
        data: {
            labels: Object.keys(visualizationData.value),
            datasets: [
                {
                    label: '점수',
                    data: normalizedData,
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
                    min: -6,
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
