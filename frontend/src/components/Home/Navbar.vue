<template>
    <nav class="top-bar">
        <Logo />
        <!-- <div class="rectangle-4137"><Logo /></div> -->
        <div class="title">
            <RouterLink style="text-decoration: none" :to="{ name: 'home' }">JOBIS</RouterLink>
        </div>
        <div class="navigation">
            <div class="tab">
                <RouterLink style="text-decoration: none" :to="{ name: 'home' }">홈</RouterLink>
            </div>
            <div class="tab">
                <RouterLink style="text-decoration: none" :to="{ name: 'company' }"
                    >기업추천</RouterLink
                >
            </div>
            <div class="tab">
                <RouterLink style="text-decoration: none" :to="{ name: 'search' }"
                    >기업검색</RouterLink
                >
            </div>
            <div class="tab">
                <RouterLink style="text-decoration: none" :to="{ name: 'mypage' }"
                    >Scrap</RouterLink
                >
            </div>
            <!-- <button class="primary" @click="goSignUp">
                <div class="title2">회원가입</div>
            </button> -->
            <div class="tab" v-if="store.isLogin">
                <button class="primary" @click="logout">
                    <div class="title2">로그아웃</div>
                </button>
            </div>
            <div class="tab" v-else>
                <button class="primary" @click="goLogin">
                    <div class="title2">로그인</div>
                </button>
            </div>
        </div>
    </nav>
</template>

<script setup>
import router from '@/router';
import { RouterLink } from 'vue-router';
import { ref, watch, computed } from 'vue';
import { useCounterStore } from '@/stores/counter';

import Logo from './Logo.vue';
import TestLogin from '../Users/TestLogin.vue';

const store = useCounterStore();
// 로그인 유무 확인
console.log(store.isLogin);
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

const logout = () => {
    // 로그아웃 로직
};
</script>

<style scoped>
/* @import url('@/assets/header.css'); */
@import url('@/assets/blueTone/headerblue.css');
</style>
