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
        <AddFriends></AddFriends>
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
        <div class="card" v-for="item in friendsList" :key="item['id']">
          <div class="avatar">
            <el-avatar
              v-if="userInfo.id !== item['sourceInfo']['id']"
              size="small"
              shape="square"
              :src="item['sourceInfo']['avatar']"
              >{{ item['sourceInfo']['nickName'] }}</el-avatar
            >
            <el-avatar
              v-if="userInfo.id !== item['targetInfo']['id']"
              size="small"
              shape="square"
              :src="item['targetInfo']['avatar']"
              >{{ item['targetInfo']['nickName'] }}</el-avatar
            >
          </div>
          <div v-if="userInfo.id !== item['sourceInfo']['id']" class="name">
            {{ item['sourceInfo']['nickName'] }}
          </div>
          <div v-if="userInfo.id !== item['targetInfo']['id']" class="name">
            {{ item['targetInfo']['nickName'] }}
          </div>
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
import {
  defineComponent,
  computed,
  ref,
  onMounted,
  reactive,
  toRefs
} from 'vue';
import { useStore } from 'vuex';
import NewFriendsList from './newFriendsList.vue';
import AddFriends from './addFriends.vue';
import { getFriendsList as get } from '@/http/api/friends';

export default defineComponent({
  name: 'Friends',
  components: { NewFriendsList, AddFriends },
  setup() {
    const store = useStore();
    const userInfo: any = computed(() => store.getters.userInfo);
    // 右边组件
    const currentComponentName = 'NewFriendsList';
    const state = reactive({
      friendsList: []
    });
    /**
     * 搜索
     */
    const searchVal = ref('');
    /**
     * 获取好友列表
     */
    const getFriendsList = () => {
      state.friendsList = [];
      get({
        id: userInfo.value.id
      }).then((res: any) => {
        if (res.code === 1) {
          state.friendsList = res.data;
        }
      });
    };

    onMounted(() => {
      getFriendsList();
    });
    return { currentComponentName, userInfo, searchVal, ...toRefs(state) };
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
