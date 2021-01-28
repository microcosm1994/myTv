<template>
  <div class="aside">
    <div class="header">
      <el-popover placement="right" :width="240" trigger="click">
        <template #reference>
          <el-avatar shape="square" :src="userInfo.avatar">{{
            userInfo.nickName
          }}</el-avatar>
        </template>
        <UserCard :user-info="userInfo"></UserCard>
      </el-popover>
    </div>
    <div class="menu">
      <ul>
        <li>
          <router-link to="/home"
            ><i
              :class="[
                'el-icon-chat-dot-round',
                { active: '/home/index' === currentRouteName }
              ]"
            ></i
          ></router-link>
        </li>
        <li>
          <router-link to="/home/friends"
            ><i
              :class="[
                'el-icon-user',
                { active: '/home/friends' === currentRouteName }
              ]"
            ></i
          ></router-link>
        </li>
        <li><i class="el-icon-video-camera"></i></li>
      </ul>
    </div>
    <div class="bootom-menu">
      <ul>
        <li><i class="el-icon-menu"></i></li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import UserCard from '@/components/userCard.vue';

export default defineComponent({
  name: 'Aside',
  components: { UserCard },
  setup() {
    // 获取用户信息
    const store = useStore();
    const userInfo = computed(() => store.getters.userInfo);

    // 获取路由信息
    const router = useRouter();
    const currentRouteName = computed(() => router.currentRoute.value.path);

    return { userInfo, currentRouteName };
  }
});
</script>
<style lang="less" scoped>
.aside {
  width: 65px;
  height: 100%;
  background: #25292c;
  padding-top: 10px;
  padding-bottom: 150px;
  position: relative;
  .header {
    width: 100%;
    padding-left: 10px;
  }
  .menu {
    width: 100%;
    padding-top: 20px;
    ul {
      width: 100%;
      li {
        width: 100%;
        height: 65px;
        padding: 17px 17px;
        .active {
          color: #07c160;
        }
        i {
          cursor: pointer;
          font-size: 25px;
          color: #e2e2e2;
        }
      }
    }
  }
  .bootom-menu {
    position: absolute;
    bottom: 10px;
    left: 0;
    ul {
      width: 100%;
      li {
        width: 100%;
        height: 40px;
        padding: 5px 17px;
        .active {
          color: #fff;
        }
        i {
          cursor: pointer;
          font-size: 25px;
          color: #e2e2e2;
        }
      }
    }
  }
}
</style>
