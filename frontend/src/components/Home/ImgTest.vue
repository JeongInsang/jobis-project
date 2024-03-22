<template>
    <div class="frame" style="width: 500px; height: 500px">
        <img :src="getImageUrl(fname)" />
    </div>
</template>

<script setup>
import { ref } from 'vue';

const gifList = ['gif1', 'gif2'];

const fname = ref('gif1');

function getImageUrl(name) {
    return new URL(`/src/assets/images/${name}.gif`, import.meta.url).href;
}

setInterval(() => {
    console.log(fname.value);
    if (fname.value === 'gif1') {
        fname.value = 'gif2';
    } else if (fname.value === 'gif2') {
        fname.value = 'gif1';
    }
}, 5000);
</script>

<style scoped></style>
