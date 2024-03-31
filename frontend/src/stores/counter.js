import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

export const useCounterStore = defineStore('counter', () => {
<<<<<<< HEAD
    // const API_URL = import.meta.env.VITE_APP_API_URL;
    // const API_URL = "http://127.0.0.1:8080/api"
    const API_URL = 'https://j10b309.p.ssafy.io';
=======
    const isLogin = ref(false);
    const API_URL = 'https://j10b309.p.ssafy.io/api';
>>>>>>> cfbd61e8c1ce5b4013b2ecd9c36b0d2c0f90375f
    const token = localStorage.getItem('accessToken');
    const signUp = function (payload) {
        const { email, password } = payload;
        axios({
            method: 'post',
            url: `${API_URL}/user/register`,
            data: {
                email,
                password,
            },
        })
            .then((res) => {
                console.log('회원가입 성공, ', res);
            })
            .catch((err) => console.log(err));
    };

    const logIn = function (payload) {
        const email = payload.email;
        const password = payload.password;

        axios({
            method: 'post',
            url: `${API_URL}/user/login`,
            data: {
                email,
                password,
            },
        })
            .then((res) => {
                const accessToken = res.headers.get('authorization');
                const refreshToken = res.headers.get('refresh-token');
                console.log(res.headers);
                console.log(refreshToken);

                localStorage.setItem('accessToken', accessToken);
                localStorage.setItem('refreshToken', refreshToken);
                isLogin = true;

                router.push({ name: 'home' });

                // 현재 로그인한 유저 정보 받아오기
            })
            .catch((err) => {
                console.log(err);
                alert('비밀번호가 틀렸거나, 가입되지 않은 사용자입니다.');
            });
    };

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

    return { API_URL, signUp, logIn, logOut, isLogin, token };
});
