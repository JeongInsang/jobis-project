<template>
    <nav class="top-bar">
        <!-- <Logo /> -->
        <!-- <div class="rectangle-4137"><Logo /></div> -->
        <div class="title">
            <RouterLink style="text-decoration: none" :to="{ name: 'home' }">JOBIS</RouterLink>
        </div>
        <div class="navigation">
            <div class="tab">
                <RouterLink style="text-decoration: none" :to="{ name: 'home' }">홈</RouterLink>
            </div>
            <div class="tab" v-if="store.isLogin">
                <RouterLink style="text-decoration: none" :to="{ name: 'company' }"
                    >기업추천</RouterLink
                >
            </div>
            <div class="tab">
                <RouterLink style="text-decoration: none" :to="{ name: 'search' }"
                    >기업검색</RouterLink
                >
            </div>
            <div class="tab" v-if="store.isLogin">
                <RouterLink style="text-decoration: none" :to="{ name: 'mypage' }"
                    >Scrap</RouterLink
                >
            </div>
            <!-- <button class="primary" @click="goSignUp">
                <div class="title2">회원가입</div>
            </button> -->
            <div class="tab" v-if="store.isLogin">
                <button class="primary" @click="formLogout">
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
import { logOut } from '@/apis/api/user';

const store = useCounterStore();
// 로그인 유무 확인
console.log(store.isLogin);

const goLogin = () => {
    router.push({
        name: 'login',
    });
};

const formLogout = () => {
    // 로그아웃 로직
    console.log("logout")
    logOut(
        (res) => {
        console.log(res);
        store.setIsLogin(false);
        localStorage.clear();

        alert("로그아웃 완료")

        router.push({ name: 'home' });
        
    },
    (err) => {
        console.log(err);
    })
    // store.logOut();
};
</script>

<style scoped>
/* @import url('@/assets/header.css'); */
@import url('@/assets/blueTone/headerblue.css');
</style>
