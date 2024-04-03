<script setup>
import { useRoute } from 'vue-router';
import { onMounted } from 'vue';
import router from '@/router';
import { useCounterStore } from '@/stores/counter';
import { logOut } from '@/apis/api/user';

const store = useCounterStore();

const route = useRoute();

onMounted(() => {

    alert("로그인 세션이 만료되었습니다.")


    logOut(
        (res) => {
        console.log(res);
        store.setIsLogin(false);
        localStorage.clear();
        router.push({ name: 'login' }); 
    },
    (err) => {
        console.log(err);
    })

    router.push({ name: 'login' });
});
</script>

<style scoped>

</style>