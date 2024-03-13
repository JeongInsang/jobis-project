<template>
    <div class="section">
        <div class="container">
            <div class="title">회원가입</div>
            <form @submit.prevent="submitForm">
                <div class="input">
                    <div class="title2">ID</div>
                    <input
                        class="textfield"
                        type="text"
                        v-model="userId"
                        placeholder="Enter your ID" />
                </div>
                <div class="input">
                    <div class="title2">Password</div>
                    <input
                        class="textfield"
                        type="password"
                        v-model="password"
                        placeholder="Enter your password" />
                </div>
                <div class="input">
                    <div class="title2">Nickname</div>
                    <input
                        class="textfield"
                        type="text"
                        v-model="nickname"
                        placeholder="Enter your nickname" />
                </div>
                <div class="button">
                    <div class="primary">
                        <button type="submit" class="title3">Sign Up</button>
                    </div>
                </div>
            </form>
        </div>
        <svg
            class="vector-200"
            width="1440"
            height="1"
            viewBox="0 0 1440 1"
            fill="none"
            xmlns="http://www.w3.org/2000/svg">
            <path d="M0 1H1440" stroke="black" stroke-opacity="0.1" />
        </svg>
    </div>
</template>

<script setup>
import { ref } from 'vue';
const userId = ref('');
const password = ref('');
const nickname = ref('');

const submitForm = () => {
    // 여기에 폼 제출 처리 로직을 추가하세요.
};
</script>

<style scoped>
@import url('@/assets/signupview.css');
</style>
