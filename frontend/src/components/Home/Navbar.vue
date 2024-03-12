<template>
    <div class="top-bar">
        <div class="rectangle-4137"></div>
        <div class="title">JOBIS</div>
        <div class="navigation">
            <div class="tab">
                <RouterLink style="text-decoration: none" :to="{ name: 'home' }">홈</RouterLink>
            </div>
            <div class="tab">
                <RouterLink style="text-decoration: none" :to="{ name: 'companydetail' }"
                    >기업추천</RouterLink
                >
            </div>
            <button class="primary" @click="goSignUp">
                <div class="title2">회원가입</div>
            </button>
            <button class="primary" @click="goLogin">
                <div class="title2">로그인</div>
            </button>
        </div>
    </div>
</template>

<script setup>
import router from '@/router';
import { RouterLink } from 'vue-router';
const goSignUp = () => {
    router.push({
        name: 'signup',
    });
};

const goLogin = () => {
    router.push({
        name: 'login',
    });
};
</script>

<style scoped>
@import url('@/assets/header.css');
</style>
