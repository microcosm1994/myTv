import { createRouter, RouteRecordRaw, createWebHashHistory } from 'vue-router';
import Start from '../views/Start.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Start',
    component: Start
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import(/* 注册页: "register" */ '../views/Register.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* 登录页: "login" */ '../views/Login.vue')
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import(/* home页: "home" */ '../views/Home.vue'),
    children: [
      {
        path: '',
        name: 'About',
        component: () => import(/* about页: "about" */ '../views/About.vue'),
      }
    ]
  }
];

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
});

export default router;
