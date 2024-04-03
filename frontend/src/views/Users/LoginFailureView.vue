<script setup>
import { useRoute } from 'vue-router';
import { onMounted } from 'vue';
import router from '@/router';
import { useCounterStore } from '@/stores/counter';

const store = useCounterStore();

const route = useRoute();

onMounted(() => {
    window.alert("소셜로그인에 실패하였습니다.");

    // Redirect to the root path
    router.push({ name: 'login' });
});
</script>

<style scoped>

</style>