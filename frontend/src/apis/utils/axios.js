import axios from 'axios';

function localAxios() {
    const instance = axios.create({
        baseURL: import.meta.env.VITE_APP_API_URL,
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return instance;
}

export { localAxios };
