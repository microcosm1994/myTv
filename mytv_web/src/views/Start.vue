<template>
  <div class="start">
    <Fire />
    <div class="btn">
      <el-button size="small" type="primary"
        ><router-link to="/login">登陆</router-link></el-button
      >
      <el-button size="small" type="primary" @click="toRegister"
        ><router-link to="/register">注册</router-link></el-button
      >
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import store from '@/store';
import Fire from '@/components/fire.vue';

export default defineComponent({
  name: 'Start',
  components: {
    Fire
  },
  setup() {
    const router = useRouter();
    // 跳转到注册页
    const toRegister = async () => {
      router.push('/register');
    };
    // 生命周期
    onMounted(() => {
      if (store.getters.token) {
        router.push('/home');
      }
    });

    return {
      toRegister
    };
  }
});
</script>
<style lang="less" scoped>
.start {
  width: 100%;
  height: 100%;
  .btn {
    padding-top: 20px;
    text-align: center;
    .el-button--primary {
      background: #e95022;
      outline: none;
      border: none;
      &:hover {
        background: #da4c21;
      }
      &:active {
        background: #e95022;
      }
      a {
        color: #fff;
      }
    }
  }
}
</style>
