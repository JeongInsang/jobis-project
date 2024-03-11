<script>
import { useRoute } from 'vue-router';
import { onMounted } from 'vue';
import router from '@/router';

export default {
    name: 'LoginSuccess',
    components: [],
    setup() {
        const route = useRoute();

        onMounted(() => {
            const accessToken = route.params.atk;
            const refreshToken = route.params.rtk;

            // 로컬스토리지에 토큰 저장
            localStorage.setItem('accessToken', accessToken);
            localStorage.setItem('refreshToken', refreshToken);

            // Redirect to the root path

            window.location.href = '/';
        });
    },
};
</script>

<style scoped>

</style>
