import { createApp } from 'vue';
import { createPinia } from 'pinia';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';

import App from './App.vue';
import router from './router';

import AOS from 'aos';
import 'aos/dist/aos.css';

const app = createApp(App);

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);
app.use(router);
app.use(pinia);

// Vue 인스턴스 생성 전에 AOS 초기화
app.mixin({
    created() {
        AOS.init();
    },
});

app.mount('#app');
