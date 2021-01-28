<template>
  <div class="friends">
    <div class="left">
      <!-- 搜索 -->
      <div class="search">
        <div class="input">
          <el-input size="mini" placeholder="搜索" v-model="searchVal">
            <template #prefix>
              <i class="el-input__icon el-icon-search"></i>
            </template>
          </el-input>
        </div>
        <el-popover placement="bottom" :width="240" trigger="click">
          <template #reference>
            <div class="btn">+</div>
          </template>
          <AddFriends></AddFriends>
        </el-popover>
      </div>
      <!-- 新的朋友 -->
      <div class="new active">
        <div class="icon">
          <i class="el-icon-s-custom"></i>
        </div>
        <div class="title">新的朋友</div>
      </div>
      <!-- 好友列表 -->
      <div class="list">
        <div class="title">A</div>
        <div class="card">
          <div class="avatar">
            <el-avatar
              size="small"
              shape="square"
              src="userInfo.avatar"
            ></el-avatar>
          </div>
          <div class="name">AAA开发1</div>
        </div>
      </div>
    </div>
    <!-- 右边详细内容 -->
    <div class="right">
      <component :is="currentComponentName"></component>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed, ref } from 'vue';
import { useStore } from 'vuex';
import NewFriendsList from './newFriendsList.vue';
import AddFriends from './addFriends.vue'

export default defineComponent({
  name: 'Friends',
  components: { NewFriendsList, AddFriends },
  setup() {
    const store = useStore();
    const userInfo = computed(() => store.getters.userInfo);
    // 右边组件
    const currentComponentName = 'NewFriendsList';
    /**
     * 搜索
     */
    const searchVal = ref('');

    return { currentComponentName, userInfo, searchVal };
  }
});
</script>
<style lang="less" scoped>
.friends {
  width: 100%;
  height: 100%;
  background: #eeeae8;
  position: relative;
  padding-left: 200px;
  .left {
    width: 200px;
    position: absolute;
    left: 0;
    top: 0;
    .search {
      width: 100%;
      padding: 15px 10px;
      .input {
        display: inline-block;
        vertical-align: middle;
        width: 80%;
        .el-input {
          .el-input__inner {
            background: #dcd9d8;
          }
        }
      }
      .btn {
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
    }
    .active {
      background: #c6c5c5;
    }
    .new {
      width: 100%;
      height: 50px;
      padding-top: 10px;
      padding-left: 10px;
      .icon {
        display: inline-block;
        vertical-align: middle;
        width: 30px;
        height: 30px;
        background: #fa9d3b;
        border-radius: 2px;
        line-height: 30px;
        text-align: center;
        i {
          color: #fff;
        }
      }
      .title {
        display: inline-block;
        vertical-align: middle;
        margin-left: 10px;
        font-size: 14px;
      }
    }
    .list {
      width: 100%;
      padding-top: 10px;
      .title {
        width: 100%;
        height: 20px;
        line-height: 20px;
        font-size: 12px;
        padding-left: 15px;
        color: #b4b3b3;
      }
      .card {
        width: 100%;
        height: 50px;
        position: relative;
        padding-left: 40px;
        padding-top: 10px;
        cursor: pointer;
        .avatar {
          display: inline-block;
          vertical-align: middle;
          width: 30px;
          height: 30px;
          position: absolute;
          left: 10px;
        }
        .name {
          display: inline-block;
          vertical-align: middle;
          font-size: 14px;
          margin-left: 10px;
        }
      }
    }
  }
  .right {
    width: 100%;
    height: 100%;
    background: #fff;
  }
}
</style>
