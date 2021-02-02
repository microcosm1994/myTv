<template>
  <el-popover
    placement="bottom"
    :width="240"
    trigger="click"
    @hide="clearUserInfo"
  >
    <template #reference>
      <div class="addFriends">+</div>
    </template>
    <div class="search">
      <el-input
        size="mini"
        placeholder="输入账号"
        v-model="searchVal"
        @keyup.enter="searchUser"
      >
        <template #prefix>
          <i class="el-input__icon el-icon-search"></i>
        </template>
        <template #append>
          <el-button @click="searchUser" icon="el-icon-search"></el-button>
        </template>
      </el-input>
    </div>
    <div class="result">
      <div v-if="userCardShow">
        <UserCard :user-info="userInfo"></UserCard>
        <div class="addBtn">
          <el-button size="mini" type="primary" @click="addFriends"
            >添加好友</el-button
          >
        </div>
      </div>
      <div v-if="errorMsg">{{ errorMsg }}</div>
    </div>
  </el-popover>
</template>
<script lang="ts">
import {
  computed,
  defineComponent,
  getCurrentInstance,
  reactive,
  ref,
  toRefs,
  Ref,
  watch
} from 'vue';
import { useStore } from 'vuex';
import UserCard from '@/components/userCard.vue';
import { searchUserInfo } from '@/http/api/user';
import { addFriendsAsk } from '@/http/api/friends';

export default defineComponent({
  name: 'AddFriends',
  components: { UserCard },
  setup() {
    const store = useStore();
    // 获取user信息
    const user = computed(() => store.getters.userInfo);
    /**
     * 搜索用户
     */
    const userCardShow = ref(false);
    const errorMsg = ref('');
    const state = reactive({
      userInfo: {}
    });
    // 搜索用户
    const searchVal: Ref<string> = ref('');
    const searchUser = () => {
      state.userInfo = {};
      searchUserInfo({ userName: searchVal.value.trim() }).then((res: any) => {
        if (res.code === 1) {
          state.userInfo = res.data;
          userCardShow.value = true;
        } else {
          userCardShow.value = false;
          errorMsg.value = res.msg;
        }
      });
    };
    // 清除用户信息
    const clearUserInfo = () => {
      state.userInfo = {};
      searchVal.value = '';
    };
    watch(searchVal, () => {
      state.userInfo = {};
      userCardShow.value = false;
    });

    /**
     * 添加用户
     */
    const { ctx }: any = getCurrentInstance();
    const addFriends = () => {
      addFriendsAsk({
        sourceId: user.value.id,
        targetId: state.userInfo['id']
      }).then((res: any) => {
        if (res.code === 1) {
          ctx.$notify({
            title: '添加好友',
            message: '好友申请已发送',
            type: 'success'
          });
        } else {
          ctx.$notify({
            title: '添加好友',
            message: '好友申请发送失败',
            type: 'error'
          });
        }
      });
    };

    return {
      userCardShow,
      errorMsg,
      searchVal,
      searchUser,
      clearUserInfo,
      ...toRefs(state),
      addFriends
    };
  }
});
</script>
<style lang="less" scoped>
.addFriends {
  display: inline-block;
  vertical-align: middle;
  width: 15%;
  height: 25px;
  background: #dcd9d8;
  color: #999;
  line-height: 25px;
  text-align: center;
  border-radius: 4px;
  overflow: hidden;
  cursor: pointer;
  margin-left: 5px;
  font-size: 16px;
  &:hover {
    background: #d1d1d1;
  }
  &:active {
    background: #dcd9d8;
  }
}
.search {
  width: 100%;
  padding-bottom: 10px;
  border-bottom: 1px solid #e2e2e2;
}
.result {
  width: 100%;
  padding-top: 10px;
  .addBtn {
    width: 100%;
    text-align: center;
  }
}
</style>
