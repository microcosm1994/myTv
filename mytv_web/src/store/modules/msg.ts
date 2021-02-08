export default {
  state: () => ({
    connect: 0,
    userInfo: ''
  }),
  actions: {
    SOCKET_CONNECT({ commit, state, dispatch }, status) {
      console.log(state);
      console.log(status);
      state.connect = status
    },
    SOCKET_CHAT({ commit, state, dispatch }) {
      console.log(state);
    }
  },
  getters: {}
};
