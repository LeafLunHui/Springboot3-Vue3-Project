import { createRouter,createWebHistory } from "vue-router";

import LoginVue from '../views/Login.vue'
import LayoutVue from '../views/Layout.vue'
import ArticleCategory from '../views/article/ArticleCategory.vue'
import ArticleManage from '../views/article/ArticleManage.vue'
import UserAvatar from '../views/user/UserAvatar.vue'
import UserInfo from '../views/user/UserInfo.vue'
import UserResetPassword from '../views/user/UserResetPassword.vue'

const routes = [
    {
        path:'/login',
        component:LoginVue
    },
    {
        path: '/' ,
        component:LayoutVue,
        children: [
            {
                path: '/article/category',
                component: ArticleCategory
            },
            {
                path: '/article/manage',
                component: ArticleManage
            },
            {
                path: '/user/avatar',
                component: UserAvatar
            },
            {
                path: '/user/info',
                component: UserInfo
            },
            {
                path: '/user/resetpassword',
                component: UserResetPassword
            },
        ]
    }
]

const router = createRouter({
    history:createWebHistory(),
    routes:routes
})

export default router
