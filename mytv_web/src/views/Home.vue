<template>
  <div class="home">
    <el-container>
      <el-aside width="100px"><Aside></Aside></el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from 'vue';
import { setSize } from '@/render';
import { useStore } from 'vuex';
import Aside from '@/components/aside.vue';

export default defineComponent({
  name: 'Home',
  components: { Aside },
  setup() {
    const store = useStore();
    onMounted(() => {
      // 改变窗口大小
      setSize(1000, 600);
      // 获取用户信息
      store.dispatch('getUserInfo');
    });
  }
});
</script>
<style lang="less" scoped>
.home {
  width: 100%;
  height: 100%;
  .el-container {
    width: 100%;
    height: 100%;
    .el-aside {
      height: 100%;
    }
    .el-main {
      height: 100%;
    }
  }
}
</style>
