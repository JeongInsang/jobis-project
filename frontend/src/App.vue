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

<style scoped>
@font-face {
    font-family: 'Pretendard-Black';
    src: url('@/assets/fonts/Pretendard-Black.woff');
}
@font-face {
    font-family: 'Pretendard-Bold';
    src: url('@/assets/fonts/Pretendard-Bold.woff');
}
@font-face {
    font-family: 'Pretendard-Light';
    src: url('@/assets/fonts/Pretendard-Light.woff');
}
@font-face {
    font-family: 'Pretendard-ExtraBold';
    src: url('@/assets/fonts/Pretendard-ExtraBold.woff');
}
@font-face {
    font-family: 'Pretendard-ExtraLight';
    src: url('@/assets/fonts/Pretendard-ExtraLight.woff');
}
@font-face {
    font-family: 'Pretendard-Medium';
    src: url('@/assets/fonts/Pretendard-Medium.woff');
}
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('@/assets/fonts/Pretendard-Regular.woff');
}
@font-face {
    font-family: 'Pretendard-SemiBold';
    src: url('@/assets/fonts/Pretendard-SemiBold.woff');
}
@font-face {
    font-family: 'Pretendard-Thin';
    src: url('@/assets/fonts/Pretendard-Thin.woff');
}
div,
h1,
h2,
h3,
h4,
h5,
span,
input {
    font-family: 'Pretendard-Medium';
}
</style>
