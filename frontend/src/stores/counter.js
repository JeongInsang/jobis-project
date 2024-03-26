import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

export const useCounterStore = defineStore('counter', () => {
    const signUp = function (payload) {
        const { username, password1, password2, nickname } = payload;
        axios({
            method: 'post',
            url: `${API_URL}/signup/`, // 임시임
            data: {
                userId,
                password1,
                password2,
                nickname,
            },
        })
            .then((res) => {
                // 회원가입 시 자동으로 로그인
                const password = password1;
                logIn({ username, password });
            })
            .catch((err) => console.log(err));
    };

    const logIn = function (payload) {
        const userId = payload.userId;
        const password = payload.password;

        axios({
            method: 'post',
            url: `${API_URL}/login/`, // 임시임
            data: {
                userId,
                password,
            },
        })
            .then((res) => {
                token.value = res.data.key;
                // Login 시 메인 페이지로 이동
                axios({
                    method: 'post',
                    url: `http://127.0.0.1:8000/api/v1/CurrentUser/`, //임시임
                    headers: {
                        Authorization: `Token ${token.value}`,
                    },
                })
                    .then((res) => {
                        currentUser.value = res.data;

                        // console.log(res)
                    })
                    .catch((err) => {
                        console.log(err);
                    });

                router.push({ name: 'main' });

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

    return { signUp, logIn, logOut, isLogin };
});
