import axios from 'axios';
import store from '@/store';
import { useRouter } from 'vue-router';

const router = useRouter();

const service = axios.create({
  baseURL: '/api',
  withCredentials: true,
  timeout: 10000
});

service.interceptors.request.use(
  (config) => {
    if (store.getters.token) {
      config.headers['token'] = store.getters.token;
    }
    if (config.method === 'get') {
      for (const key in config.params) {
        if (!config.params[key] && config.params[key] !== 0) {
          delete config.params[key];
        }
      }
    }
    return config;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);
// response
service.interceptors.response.use(
  (response) => {
    const res = response.data;
    switch (response.status) {
      case 401:
        store.dispatch('user/logout');
        break;
      default:
        break;
    }
    return res;
  },
  (error) => {
    if (error.message.includes('401')) {
      store.dispatch('logout');
      router.push('/');
      return false;
    } else {
      return Promise.reject(error);
    }
  }
);

export default service;
