<template>
    <div>
        <LoginHome v-if="isLogin" />
        <LogoutHome v-else />
    </div>
</template>

<script setup>
import LogoutHome from '@/components/Home/LogoutHome.vue';
import LoginHome from '@/components/Home/LoginHome.vue';
import { ref } from 'vue';
const isLogin = ref(false);
</script>

<style scoped></style>
