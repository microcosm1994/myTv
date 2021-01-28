<template>
  <div class="newFriendsList">
    <div class="header">新的朋友</div>
    <div class="list">
      <div class="card">
        <div class="avatar">
          <el-avatar :size="50" shape="square" src="userInfo.avatar"></el-avatar>
        </div>
        <div class="info">
          <div class="name">AAAA飞飞</div>
          <div class="msg">我是AAAA</div>
        </div>
        <div class="status">同意</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed, ref } from 'vue';
import { useStore } from 'vuex';

export default defineComponent({
  name: 'NewFriendsList',
  setup() {
    const store = useStore();
    const userInfo = computed(() => store.getters.userInfo);

    return { userInfo };
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
