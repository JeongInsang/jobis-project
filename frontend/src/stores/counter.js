import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

export const useCounterStore = defineStore('counter', () => {
    const isLogin = ref(false);
    // const API_URL = 'https://j10b309.p.ssafy.io/api';
    const token = localStorage.getItem('accessToken');

    const logOut = function () {
        // axios({
        //     method: 'post',
        //     url: `${API_URL}/logout/`, // 임시임
        // })
        //     .then((res) => {
        //         token.value = null;
        //         currentUser.value = null;
        //         localStorage.clear();
        //         router.push({ name: 'main' });
        //     })
        //     .catch((err) => {
        //         console.log(err);
        //     });
        localStorage.clear();
        isLogin.value = false;
        router.push({ name: 'home' });
    };

    return { logOut, isLogin, token };
});
