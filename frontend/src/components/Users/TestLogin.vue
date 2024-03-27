<template>
    <div class="container">
        <div class="welcome">
            <div class="pinkbox" :style="{ transform: pinkboxTransform }">
                <div class="signup" :class="{ nodisplay: !showSignup }">
                    <h1>회원가입</h1>
                    <form autocomplete="off" @submit.prevent="signUp">
                        <input type="text" placeholder="이메일" v-model="userEmail" />
                        <span
                            v-if="!emailValid"
                            class="error-message animate__animated animate__shakeX"
                            >@를 포함하여 올바른 형식으로 작성해주세요.</span
                        >
                        <input type="password" placeholder="비밀번호" v-model="password1" />
                        <span
                            v-if="!passwordValid"
                            class="error-message animate__animated animate__shakeX"
                            >영문 + 숫자를 포함한 4자이상 20자 이내여야 합니다.</span
                        >
                        <input type="password" placeholder="비밀번호 확인" v-model="password2" />
                        <span
                            v-if="!passwordSamevalid"
                            class="error-message animate__animated animate__shakeX"
                            >비밀번호가 일치하지 않습니다.</span
                        >
                        <button class="button submit" @click="signUp">계정 만들기</button>
                    </form>
                </div>
                <div class="signin" :class="{ nodisplay: !showSignin }">
                    <h1>로그인</h1>
                    <form class="more-padding" autocomplete="off">
                        <input type="text" placeholder="아이디" />
                        <input type="password" placeholder="비밀번호" />
                        <div class="checkbox">
                            <input type="checkbox" id="remember" /><label for="remember"
                                >remember me</label
                            >
                        </div>

                        <button class="button submit" @submit="signIn">로그인</button>
                    </form>
                </div>
            </div>
            <div class="leftbox">
                <h2 class="title"><span>JOB</span>IS</h2>
                <p class="desc">pick your perfect <span>JOB</span></p>
                <LoginLogo class="logo" />
                <p class="account">계정이 있으신가요?</p>
                <button class="button" @click="showSignIn">로그인</button>
            </div>
            <div class="rightbox">
                <h2 class="title"><span>JOB</span>IS</h2>
                <p class="desc">pick your perfect <span>JOB</span></p>
                <LoginLogo class="logo" />
                <p class="account">계정이 없으신가요?</p>
                <button class="button" @click="showSignUp">회원가입</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useCounterStore } from '@/stores/counter';
import LoginLogo from '@/components/Users/LoginLogo.vue';
import { watch } from 'vue';

// 회원가입 로직
const store = useCounterStore();
const userEmail = ref('');
const password1 = ref('');
const password2 = ref('');
const emailValid = ref(true);
const passwordValid = ref(true);
const passwordSamevalid = ref(true);

const signUp = () => {
    const payload = {
        userEmail: userEmail.value,
        password1: password1.value,
        password2: password2.value,
    };
    // email 유효성 검사
    const regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    emailValid.value = regexEmail.test(userEmail.value);
    // 비밀번호 유효성 검사
    const regexPassword = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,20}$/;
    passwordValid.value = regexPassword.test(password1.value);
    passwordSamevalid.value = password1.value === password2.value;
    if (
        // emailValid.value == true &&
        // passwordValid.value == true &&
        // passwordSamevalid.value == true
        emailValid.value == true
    ) {
        // store.signUp(payload);
        if (passwordValid.value == true) {
            if (passwordSamevalid.value == true) {
                store.signUp(payload);
            }
        }
    }
};

// Email 유효성 검사
// const watchEmail = watch(userEmail, (newEmail, oldEmail) => {
//     const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
//     emailValid.value = regex.test(newEmail);
// });

// 비밀번호 유효성 검사
// const watchPassword = watch([password1, password2], ([pass1, pass2]) => {
//     const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,20}$/;
//     passwordValid.value = regex.test(pass1);
//     passwordSamevalid.value = pass1 === pass2;
// });

// 로그인 회원가입 전환 로직
const showSignup = ref(false);
const showSignin = ref(true);

const pinkboxTransform = computed(() => {
    return showSignup.value ? 'translateX(80%)' : 'translateX(0%)';
});

const showSignIn = () => {
    showSignup.value = false;
    showSignin.value = true;
};

const showSignUp = () => {
    showSignup.value = true;
    showSignin.value = false;
};
</script>

<style lang="scss" scoped>
$gray: #8e9aaf;
$lavender: #cbc0d3;
$pale: #eac7cc;
$white: #f6f6f6;
$font: #3498db;

$pink: #007bff; // 기존 색상 대신 새로운 색상으로 변경

@import url('https://fonts.googleapis.com/css?family=Open+Sans:300,400|Lora');

$sans-serif: 'Open Sans', sans-serif;
$serif: 'Lora', serif;

body {
    background: $lavender;
}

/* div box styling */
.container {
    margin: auto;
    width: 650px;
    height: 550px;
    position: relative;
}

.welcome {
    background: $white;
    width: 650px;
    height: 415px;
    position: absolute;
    top: 25%;
    border-radius: 5px;
    box-shadow: rgba(0, 123, 255, 1);
}

.pinkbox {
    position: absolute;
    top: -10%;
    left: 5%;
    background: $pink; // 여기서 핑크색을 새로운 색상으로 변경
    width: 320px;
    height: 500px;
    border-radius: 5px;
    box-shadow: 2px 0 10px rgba(0, 123, 255, 1);
    transition: all 0.5s ease-in-out;
    z-index: 2;
}

.nodisplay {
    display: none;
    transition: all 0.5s ease;
}

.leftbox,
.rightbox {
    position: absolute;
    width: 50%;
    transition: 1s all ease;
}

.leftbox {
    left: -2%;
}
.rightbox {
    right: -2%;
}

/* font & button styling */
h1 {
    font-family: $sans-serif;
    text-align: center;
    margin-top: 95px;
    text-transform: uppercase;
    color: $white;
    font-size: 2em;
    letter-spacing: 8px;
}

.title {
    font-family: $serif;
    color: $gray;
    font-size: 1.8em;
    line-height: 1.1em;
    letter-spacing: 3px;
    text-align: center;
    font-weight: 300;
    margin-top: 20%;
}
.desc {
    margin-top: -8px;
}
.account {
    margin-top: 45%;
    font-size: 10px;
}
p {
    font-family: $sans-serif;
    font-size: 0.7em;
    letter-spacing: 2px;
    color: $gray;
    text-align: center;
}

span {
    color: $font;
}

.flower {
    position: absolute;
    width: 120px;
    height: 120px;
    top: 46%;
    left: 29%;
    opacity: 0.7;
}

.smaller {
    width: 90px;
    height: 100px;
    top: 48%;
    left: 38%;
    opacity: 0.9;
}

button {
    padding: 12px;
    font-family: $sans-serif;
    text-transform: uppercase;
    letter-spacing: 3px;
    font-size: 11px;
    border-radius: 10px;
    margin: auto;
    outline: none;
    display: block;
    &:hover {
        background: $pink;
        color: $white;
        transition: background-color 1s ease-out;
    }
}

.button {
    margin-top: 3%;
    background: $white;
    color: $pink; // 버튼 텍스트 색상을 새로운 핑크색으로 변경
    border: solid 1px $pink; // 버튼 테두리 색상도 새로운 핑크색으로 변경
}

/* form styling */

form {
    display: flex;
    align-items: center;
    flex-direction: column;
    padding-top: 7px;
}
.more-padding {
    padding-top: 35px;
    input {
        padding: 12px;
    }
    .submit {
        margin-top: 45px;
    }
}
.submit {
    margin-top: 25px;
    padding: 12px;
    border-color: $pink;
    &:hover {
        background: $lavender;
        border-color: darken($lavender, 5%);
    }
}

input {
    background: $pink;
    width: 65%;
    color: $white;
    border: none;
    border-bottom: 1px solid rgba($white, 0.5);
    padding: 9px;
    margin: 7px;
    &::placeholder {
        color: rgba($white, 1);
        letter-spacing: 2px;
        font-size: 1.3em;
        font-weight: 100;
    }
    &:focus {
        color: $white;
        outline: none;
        border-bottom: 1.2px solid rgba($pink, 0.7);
        font-size: 1em;
        transition: 0.8s all ease;
        &::placeholder {
            opacity: 0;
        }
    }
}

label {
    font-family: $sans-serif;
    color: $white;
    font-size: 0.8em;
    letter-spacing: 1px;
}

.checkbox {
    display: inline;
    white-space: nowrap;
    position: relative;
    left: -62px;
    top: 5px;
}

input[type='checkbox'] {
    width: 7px;
    background: $pink;
}

.checkbox input[type='checkbox']:checked + label {
    color: $white;
    transition: 0.5s all ease;
}
//
.logo {
    position: absolute;
    width: 80px;
    height: 80px;
    top: 46%;
    left: 40%;
    opacity: 0.7;
}

/* 에러 메시지 */
.error-message {
    color: rgb(255, 242, 0);
    font-size: 12px;
    margin-top: 4px;
    font-weight: 700;
}
</style>
