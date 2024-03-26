<template>
    <div>
        <Navbar />
        <RouterView />
        <Footer />
    </div>
</template>

<script setup>
import { RouterView } from 'vue-router';
import Navbar from './components/Home/Navbar.vue';
import Footer from './components/Home/Footer.vue';
</script>

<style scoped></style>
