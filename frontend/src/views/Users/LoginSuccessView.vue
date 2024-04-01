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

            // Store the tokens in localStorage
            localStorage.setItem('accessToken', accessToken);
            localStorage.setItem('refreshToken', refreshToken);

            // Redirect to the root path

            window.location.href = '/';
        });
    },
};
</script>

<style scoped>
/* Your styles here */
</style>
