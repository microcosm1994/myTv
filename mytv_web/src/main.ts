import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import installElementPlus from './plugins/element';
import VueSocketIo from 'vue-socket.io';
import '@/assets/css/public.css';

const app = createApp(App);

/**
 * 创建socket
 */
const socket = new VueSocketIo({
  connection: 'http://127.0.0.1:9096',
  vuex: {
    store,
    actionPrefix: 'SOCKET_',
    mutationPrefix: 'SOCKET_'
  }
});
app.config.globalProperties.$socket = socket;

// 挂载element-plus
installElementPlus(app);
app
  .use(store)
  .use(router)
  .mount('#app');
