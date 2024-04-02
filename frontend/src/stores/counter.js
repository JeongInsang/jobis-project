import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

export const useCounterStore = defineStore('counter', () => {
    const isLogin = ref(false);
    // const API_URL = 'https://j10b309.p.ssafy.io/api';
    const token = ref(localStorage.getItem('accessToken'));

    const logOut = function () {
        axios({
            method: 'post',
            url: `${import.meta.env.VITE_APP_API_URL}/logout`, // 임시임
        })
            .then((res) => {
                token.value = null;
                // currentUser.value = null;
                localStorage.clear();
                isLogin.value = false;
                localStorage.clear();

                router.push({ name: 'home' });
            })
            .catch((err) => {
                console.log(err);
            });
        router.push({ name: 'home' });
    };

    const setIsLogin = (value) => {
        isLogin.value = value;
      };

    return { logOut, isLogin, token, setIsLogin };
},
{persist:true});
