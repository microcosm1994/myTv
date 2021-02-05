<template>
  <div class="userCard">
    <div class="header">
      <div class="info">
        <div class="name">
          {{ userInfo.nickName }}
          <i class="el-icon-s-custom"></i>
        </div>
        <div class="account">{{ userInfo.userName }}</div>
      </div>
      <div class="avator">
        <el-avatar shape="square" :src="userInfo.avatar">{{userInfo.nickName}}</el-avatar>
      </div>
    </div>
    <div class="btn"></div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  name: 'userCard',
  props: {
    userInfo: {
      type: Object,
      required: true
    }
  },
});
</script>
<style lang="less" scoped>
.userCard {
  width: 214px;
  padding: 10px 15px;
  margin: 0 auto;
  .header {
    width: 100%;
    .info {
      width: 120px;
      display: inline-block;
      vertical-align: middle;
      .name {
        width: 100%;
        font-size: 20px;
        line-height: 30px;
        color: #333;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
        i {
          color: rgb(18, 141, 223);
        }
      }
      .account {
        font-size: 14px;
        color: #999;
      }
    }
    .avator {
      display: inline-block;
      vertical-align: middle;
      margin-left: 20px;
    }
  }
}
</style>
