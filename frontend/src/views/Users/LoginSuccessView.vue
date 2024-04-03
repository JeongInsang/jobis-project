<script setup>
import { useRoute } from 'vue-router';
import { onMounted } from 'vue';
import router from '@/router';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore();

const route = useRoute();

onMounted(() => {
    const accessToken = route.params.atk;
    const refreshToken = route.params.rtk;

    // 로컬스토리지에 토큰 저장
    localStorage.setItem('accessToken', accessToken.replace('Bearer ', ''));
    localStorage.setItem('refreshToken', refreshToken);
    store.setIsLogin(true);

    // Redirect to the root path
    router.push({ name: 'home' });
});
</script>

<style scoped>

</style>