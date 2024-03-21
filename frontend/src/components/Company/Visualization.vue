<template>
    <div class="chart-canvas">
        <h2>Visualization</h2>
        <canvas ref="chartCanvas"></canvas>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';
import 'chartjs-adapter-date-fns';

const chartCanvas = ref(null);
const visualizationData = ref({
    stability: 80,
    growth: 90,
    activity: 85,
    profitability: 75,
    availability: 95,
});

onMounted(() => {
    const ctx = chartCanvas.value.getContext('2d');
    new Chart(ctx, {
        type: 'radar',
        data: {
            labels: Object.keys(visualizationData.value),
            datasets: [
                {
                    label: 'Value',
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
                    max: 100,
                    min: 0,
                    stepSize: 20,
                },
            },
            plugins: {
                legend: {
                    position: 'top',
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
