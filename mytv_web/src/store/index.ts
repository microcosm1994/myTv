import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './modules/user';
export default createStore({
  mutations: {},
  actions: {},
  modules: { user },
  plugins: [createPersistedState()]
});
