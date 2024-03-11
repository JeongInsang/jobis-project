import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '@/views/Users/LoginView.vue';
import CompanyView from '@/views/Company/CompanyView.vue';
import CompanyDetail from '@/views/Company/CompanyDetail.vue';
import MypageView from '@/views/Users/MypageView.vue';
import SignupView from '@/views/Users/SignupView.vue';
import LoginSuccessView from '@/views/Users/LoginSuccessView.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView,
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView,
        },
        {
            path: '/company',
            name: 'company',
            component: CompanyView,
        },
        {
            path: '/companydetail',
            name: 'companydetail',
            component: CompanyDetail,
        },
        {
            path: '/mypage',
            name: 'mypage',
            component: MypageView,
        },
        {
            path: '/signup',
            name: 'signup',
            component: SignupView,
        },
        {
            path: '/login-success/:atk/:rtk',
            name: 'loginsuccess',
            component: LoginSuccessView,
        },
    ],
});

export default router;
