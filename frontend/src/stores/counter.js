import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

export const useCounterStore = defineStore('counter', () => {
    const API_URL = import.meta.env.VITE_APP_API_URL;
    // const API_URL = "http://127.0.0.1:8080/api"
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
                // 회원가입 시 자동으로 로그인
                // const password = password;
                // logIn({ email, password });
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
                console.log(localStorage.getItem('accessToken'));
                // console.log(token)
                // Login 시 메인 페이지로 이동
                // axios({
                //     method: 'post',
                //     url: `${API_URL}/v1/CurrentUser/`, //임시임
                //     headers: {
                //         Authorization: `Token ${token.value}`,
                //     },
                // })
                //     .then((res) => {
                //         currentUser.value = res.data;

                //         // console.log(res)
                //     })
                //     .catch((err) => {
                //         console.log(err);
                //     });

                router.push({ name: 'home' });

                // 현재 로그인한 유저 정보 받아오기
            })
            .catch((err) => {
                console.log(err);
                alert('비밀번호가 틀렸거나, 가입되지 않은 사용자입니다.');
            });
    };

    const logOut = function () {
        axios({
            method: 'post',
            url: `${API_URL}/logout/`, // 임시임
        })
            .then((res) => {
                token.value = null;
                currentUser.value = null;
                localStorage.clear();
                router.push({ name: 'main' });
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const isLogin = computed(() => {
        if (token.value === null) {
            return false;
        } else {
            return true;
        }
    });

    return { API_URL, signUp, logIn, logOut, isLogin };
});
