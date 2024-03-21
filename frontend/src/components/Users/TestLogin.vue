<template>
    <div class="container">
        <div class="welcome">
            <div class="pinkbox" :style="{ transform: pinkboxTransform }">
                <div class="signup" :class="{ nodisplay: !showSignup }">
                    <h1>register</h1>
                    <form autocomplete="off">
                        <input type="text" placeholder="username" />
                        <input type="email" placeholder="email" />
                        <input type="password" placeholder="password" />
                        <input type="password" placeholder="confirm password" />
                        <button class="button submit">create account</button>
                    </form>
                </div>
                <div class="signin" :class="{ nodisplay: !showSignin }">
                    <h1>sign in</h1>
                    <form class="more-padding" autocomplete="off">
                        <input type="text" placeholder="username" />
                        <input type="password" placeholder="password" />
                        <div class="checkbox">
                            <input type="checkbox" id="remember" /><label for="remember"
                                >remember me</label
                            >
                        </div>

                        <button class="button submit">login</button>
                    </form>
                </div>
            </div>
            <div class="leftbox">
                <h2 class="title"><span>JOB</span>IS</h2>
                <p class="desc">pick your perfect <span>JOB</span></p>
                <LoginLogo class="logo" />
                <p class="account">have an account?</p>
                <button class="button" @click="showSignIn">login</button>
            </div>
            <div class="rightbox">
                <h2 class="title"><span>JOB</span>IS</h2>
                <p class="desc">pick your perfect <span>JOB</span></p>
                <LoginLogo class="logo" />
                <p class="account">don't have an account?</p>
                <button class="button" @click="showSignUp">sign up</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import LoginLogo from '@/components/Users/LoginLogo.vue';

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
</style>
