import Vue from "vue";
import VueRouter from "vue-router";
import Main from '../Views/Main'

import HomePage from '../Views/HomePage.vue'
import about_GastricMalignantTumor from '../Views/about_GastricMalignantTumor.vue'
import about_about from '../Views/about_about.vue'
import about_FAQ from '../Views/about_FAQ.vue'
import about_PI from "@/Views/about_PI.vue"
import Login from '../Views/Login.vue'
import ComplicationDetails from '@/Views/ComplicationDetails.vue'

import about_TumorOfLiver from '../Views/about_TumorOfLiver.vue'
import about_ColonTumor from '../Views/about_ColonTumor.vue'
import about_Cholangiocarcinoma from '../Views/about_Cholangiocarcinoma.vue'
import about_CholecysticTumor from '../Views/about_CholecysticTumor.vue'
import about_RectalTumor from '../Views/about_RectalTumor.vue'

import Popup from "@/Views/Popup.vue";

import UserAdmin from "@/Views/UserAdmin.vue";
import Cookie from 'js-cookie'

Vue.use(VueRouter)

const routes = [
    // 主路由
    {
        path: '/',
        name: 'Main',
        component: Main,
        redirect: '/login', // 重定向
        children: [
            // 子路由
            { path: '/HomePage', name: 'HomePage', component: HomePage },
            { path: '/about', name: 'about', component: () => import('@/Views/about.vue') },
            { path: '/about_GastricMalignantTumor', name: 'about_GastricMalignantTumor', component: about_GastricMalignantTumor },

            { path: '/about_TumorOfLiver', name: 'about_TumorOfLiver', component: about_TumorOfLiver },
            { path: '/about_ColonTumor', name: 'about_ColonTumor', component: about_ColonTumor },

            { path: '/about_Cholangiocarcinoma', name: 'about_Cholangiocarcinoma', component: about_Cholangiocarcinoma },
            { path: '/about_CholecysticTumor', name: 'about_CholecysticTumor', component: about_CholecysticTumor },
            { path: '/about_RectalTumor', name: 'about_RectalTumor', component: about_RectalTumor },

            { path: '/about_about', name: 'about_about', component: about_about },
            { path: '/about_FAQ', name: 'about_FAQ', component: about_FAQ },
            { path: '/about_PI', name: 'about_PI', component: about_PI },

            { path: '/Popup', name: 'Popup', component: Popup },
            { path: '/complication-details', name: 'ComplicationDetails', component: ComplicationDetails },
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },

]

const router = new VueRouter({
    routes
})

// 路由导航守卫，拦截未登录用户直接访问内部页面
router.beforeEach((to, from, next) => {
    // 登录页面放行
    if (to.path === '/login') {
        next();
        return;
    }
    
    // 检查是否有token
    const token = localStorage.getItem('token') || sessionStorage.getItem('token') || document.cookie.includes('token=');
    
    if (!token) {
        // 如果没有token，跳转到登录页
        next('/login');
    } else {
        // 有token，放行
        next();
    }
});

export default router