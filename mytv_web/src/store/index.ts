import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './modules/user';
import msg from './modules/msg';
export default createStore({
  mutations: {},
  actions: {},
  modules: { user, msg },
  plugins: [createPersistedState()]
});
