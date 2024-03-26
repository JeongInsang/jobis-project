import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';

import AOS from 'aos';
import 'aos/dist/aos.css';

const app = createApp(App);

app.use(createPinia());
app.use(router);

// Vue 인스턴스 생성 전에 AOS 초기화
app.mixin({
    created() {
        AOS.init();
    },
});

app.mount('#app');
