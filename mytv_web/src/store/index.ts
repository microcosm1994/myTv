import { createStore } from 'vuex';
import getters from './getters';
import user from './modules/user';
export default createStore({
  getters,
  mutations: {},
  actions: {},
  modules: { user }
});
