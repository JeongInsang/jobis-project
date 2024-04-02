// localAxios.js

import axios from 'axios';
import base64 from 'base-64';

// 토큰이 10분 미만인지 검증
function isTokenValid(token) {
    if (!token) return false;
    const payload = JSON.parse(base64.decode(token.split('.')[1])); // base-64 디코딩
    const expirationTime = payload.exp * 1000;
    const currentTime = Date.now();
    return expirationTime - currentTime > 600000;
}

// 로컬 스토리지에서 토큰 가져오기
function getAccessToken() {
    return localStorage.getItem('accessToken');
}

function getRefreshToken() {
    return localStorage.getItem('refreshToken');
}

// 토큰을 헤더에 추가하는 함수
function addTokenToHeaders(headers, token, headerKey) {
    if (token) {
        if (headerKey === 'Authorization') {
            headers[headerKey] = `Bearer ${token}`;
        } else {
            headers[headerKey] = token;
        }
    }
}

// axios 인스턴스 생성 함수
function localAxios() {
    const instance = axios.create({
        baseURL: import.meta.env.VITE_APP_API_URL,
        headers: {
            'Content-Type': 'application/json',
        },
    });

    instance.interceptors.request.use(config => {
        const accessToken = getAccessToken();
        const refreshToken = getRefreshToken();

        if (!accessToken) {
            return config;
        }

        if (isTokenValid(accessToken)) {
            addTokenToHeaders(config.headers, accessToken, 'Authorization');
            // if (refreshToken) {
            //     addTokenToHeaders(config.headers, refreshToken, 'Refresh-Token');
            // }
        } else {
            // 토큰시간 10분 미만일때 refresh토큰 추가
            addTokenToHeaders(config.headers, accessToken, 'Authorization');
            addTokenToHeaders(config.headers, refreshToken, 'Refresh-Token');
        }

        return config;
    }, error => {
        return Promise.reject(error);
    });

    return instance;
}

export { localAxios };
