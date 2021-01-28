import { getUserInfo, logout } from '@/http/api/user';

export default {
  state: () => ({
    token: '',
    userInfo: ''
  }),
  mutations: {
    // 设置token
    SetToken(state, t: string) {
      state.token = t;
    },
    // 用户信息
    SetUserInfo(state, userInfo: any) {
      state.userInfo = userInfo;
    }
  },
  actions: {
    // 获取用户信息
    getUserInfo({ commit }) {
      getUserInfo().then((res: any) => {
        if (res.code === 1) {
          commit('SetUserInfo', res.data);
        }
      });
    },
    // 退出登录
    logout({ commit }) {
      logout().then(() => {
        commit('SetToken', '');
      });
    }
  },
  getters: {
    token: (state) => state.token,
    userInfo: (state) => state.userInfo
  }
};
