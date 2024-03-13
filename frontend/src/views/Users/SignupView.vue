<template>
    <div class="section">
        <div class="container">
            <div class="title">회원가입</div>
            <form @submit.prevent="signUp">
                <div class="input">
                    <div class="title2">ID</div>
                    <input
                        class="textfield"
                        type="text"
                        v-model="userId"
                        placeholder="ID를 입력하세요" />
                    <span v-if="idValid" class="error-message"
                        >아이디는 영문, 숫자를 포함한 4자이상 20자 이내여야 합니다.</span
                    >
                    {{ idValid }}
                </div>
                <div class="input">
                    <div class="title2">Password</div>
                    <input
                        class="textfield"
                        type="password"
                        v-model="password1"
                        placeholder="비밀번호를 입력하세요" />
                    <span v-if="passwordValid" class="error-message"
                        >비밀번호는 영문 + 숫자를 포함한 4자이상 20자 이내여야 합니다.</span
                    >
                </div>
                <div class="input">
                    <div class="title2">Password 확인</div>
                    <input
                        class="textfield"
                        type="password"
                        v-model="password2"
                        placeholder="비밀번호를 한번 더 입력하세요" />
                    <span v-if="passwordSamevalid" class="error-message"
                        >비밀번호가 일치하지 않습니다.</span
                    >
                </div>
                <div class="input">
                    <div class="title2">닉네임</div>
                    <input
                        class="textfield"
                        type="text"
                        v-model="nickname"
                        placeholder="닉네임을 입력하세요" />
                    <span v-if="nickNamevalid" class="error-message"
                        >최소 두 글자 이상 입력해주세요.</span
                    >
                </div>
                <div class="button">
                    <button class="primary" type="submit" @submit="signUp">
                        <div class="title3">Sign Up</div>
                    </button>
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
import { useCounterStore } from '@/stores/counter';
import { watch } from 'vue';
const store = useCounterStore();
const userId = ref(null);
const password1 = ref(null);
const password2 = ref(null);
const nickname = ref(null);

const signUp = () => {
    // 회원가입 로직
    const payload = {
        userId: userId.value,
        password1: password1.value,
        password2: password2.value,
        nickname: nickname.value,
    };
    store.signUp(payload);
};

const idValid = ref(true);
const passwordValid = ref(true);
const passwordSamevalid = ref(true);
const nickNamevalid = ref(true);

// ID 유효성 검사
const watchId = watch(userId, (newId, oldId) => {
    const regex = /^[a-zA-Z0-9]{4,20}$/;
    idValid.value = regex.test(newId);
});
</script>

<style scoped>
@import url('@/assets/signupview.css');
</style>
