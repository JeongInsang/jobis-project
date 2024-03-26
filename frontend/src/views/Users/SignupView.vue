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
                    <span v-if="!idValid" class="error-message"
                        >아이디는 영문, 숫자를 포함한 4자이상 20자 이내여야 합니다.</span
                    >
                </div>
                <div class="input">
                    <div class="title2">Password</div>
                    <input
                        class="textfield"
                        type="password"
                        v-model="password1"
                        placeholder="비밀번호를 입력하세요" />
                    <span v-if="!passwordValid" class="error-message"
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
                    <span v-if="!passwordSamevalid" class="error-message"
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
                    <span v-if="!nickNamevalid" class="error-message"
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
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useCounterStore } from '@/stores/counter';
import { watch } from 'vue';
const store = useCounterStore();
const userId = ref('');
const password1 = ref('');
const password2 = ref('');
const nickname = ref('');
const idValid = ref(false);
const passwordValid = ref(false);
const passwordSamevalid = ref(false);
const nickNamevalid = ref(false);

const signUp = () => {
    // 회원가입 로직
    const payload = {
        userId: userId.value,
        password1: password1.value,
        password2: password2.value,
        nickname: nickname.value,
    };
    if (
        idValid.value == true &&
        passwordValid.value == true &&
        passwordSamevalid.value == true &&
        nickNamevalid.value == true
    ) {
        store.signUp(payload);
    } else {
        window.alert('정확히 입력해주세요.');
    }
};

// ID 유효성 검사
const watchId = watch(userId, (newId, oldId) => {
    const regex = /^[a-zA-Z0-9]{4,20}$/;
    idValid.value = regex.test(newId);
});

// 비밀번호 유효성 검사
const watchPassword = watch([password1, password2], ([pass1, pass2]) => {
    const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,20}$/;
    passwordValid.value = regex.test(pass1);
    passwordSamevalid.value = pass1 === pass2;
});

// 닉네임 유효성 검사
const watchNickname = watch(nickname, (newNickname) => {
    nickNamevalid.value = newNickname.length >= 2;
});
</script>

<style scoped>
@import url('@/assets/signupview.css');
</style>
