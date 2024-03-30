<template>
    <div>
        <TestTyping />
        <GoWorldcup
            data-aos="fade-up"
            data-aos-offset="10"
            data-aos-delay="50"
            data-aos-duration="1000"
            data-aos-easing="ease-in-out"
            data-aos-mirror="true"
            data-aos-once="false"
            data-aos-anchor-placement="top" />
        <Carousel2
            data-aos="fade-up"
            data-aos-offset="200"
            data-aos-delay="50"
            data-aos-duration="1000"
            data-aos-easing="ease-in-out"
            data-aos-mirror="true"
            data-aos-once="false"
            data-aos-anchor-placement="top-bottom" />
        <!-- <TestCarousel /> -->
        <Top10ComapnyList />
    </div>
</template>

<script setup>
import TestCarousel from '@/components/PageTest/TestCarousel.vue';
import Carousel2 from '@/components/Home/Carousel2.vue';

import Top10ComapnyList from '@/components/Home/Top10ComapnyList.vue';

import TestTyping from '@/components/Home/TestTyping.vue';

import GoWorldcup from '@/components/Home/GoWorldcup.vue';
</script>

<style scoped></style>
