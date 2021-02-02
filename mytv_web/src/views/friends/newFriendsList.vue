<template>
  <div class="newFriendsList">
    <div class="header">新的朋友</div>
    <div class="list">
      <div class="card" v-for="item in askList" :key="item['id']">
        <div class="avatar">
          <el-avatar
            :size="50"
            shape="square"
            :src="item['targetInfo']['avatar']"
          ></el-avatar>
        </div>
        <div class="info">
          <div class="name">{{ item['targetInfo']['userName'] }}</div>
          <div class="msg">申请添加您为好友</div>
        </div>
        <div class="status" v-if="item['status'] === 1">同意</div>
        <div class="status" v-else>
          <el-button size="mini" type="primary">同意</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed, reactive, onMounted, toRefs } from 'vue';
import { useStore } from 'vuex';
import { getFriendsAsk } from '@/http/api/friends';

export default defineComponent({
  name: 'NewFriendsList',
  setup() {
    const store = useStore();
    const userInfo: any = computed(() => store.getters.userInfo);
    const state = reactive({
      askList: []
    });
    /**
     * 获取请求列表
     */
    const getFriendsAskList = () => {
      state.askList = [];
      getFriendsAsk({ targetId: userInfo.value.id }).then((res: any) => {
        if (res.code === 1) {
          state.askList = res.data;
        }
      });
    };
    onMounted(() => {
      getFriendsAskList();
    });
    return { userInfo, ...toRefs(state), getFriendsAskList };
  }
});
</script>
<style lang="less" scoped>
.newFriendsList {
  width: 100%;
  height: 100%;
  position: relative;
  margin: 0 auto;
  .header {
    width: 100%;
    height: 50px;
    padding-left: 30px;
    line-height: 50px;
    font-size: 18px;
    border-bottom: 1px solid #e2e2e2;
  }
  .list {
    width: 300px;
    margin: 0 auto;
    .card {
      width: 100%;
      height: 70px;
      position: relative;
      padding-left: 50px;
      padding-right: 50px;
      border-bottom: 1px solid #e2e2e2;
      .avatar {
        width: 50px;
        height: 50px;
        position: absolute;
        top: 50%;
        left: 5px;
        transform: translateY(-50%);
      }
      .info {
        width: 100%;
        padding-left: 20px;
        padding-top: 10px;
        .name {
          width: 100%;
          height: 30px;
          line-height: 30px;
        }
        .msg {
          width: 100%;
          height: 20px;
          line-height: 20px;
          font-size: 14px;
          color: #999;
        }
      }
      .status {
        width: 50px;
        height: 50px;
        line-height: 50px;
        position: absolute;
        top: 50%;
        right: 0;
        transform: translateY(-50%);
        color: #999;
        font-size: 14px;
      }
    }
  }
}
</style>
