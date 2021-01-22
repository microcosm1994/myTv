<template>
  <div class="start">
    <Fire />
    <div class="btn">
      <el-button size="small" type="primary" @click="setWindowSize"
        ><router-link to="/login">登陆</router-link></el-button
      >
      <el-button size="small" type="primary" @click="toRigster"
        ><router-link to="/rigster">注册</router-link></el-button
      >
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { setSize } from '../render';
import Fire from '../components/fire.vue';

export default defineComponent({
  name: 'Start',
  components: {
    Fire
  },
  setup() {
    const router = useRouter();
    // 设置窗口大小
    const setWindowSize = async () => {
      setSize(1000, 500);
    };
    // 跳转到注册页
    const toRigster = async () => {
      router.push('/rigster');
    };

    return {
      setWindowSize,
      toRigster
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
      a{
        color: #fff;
      }
    }
  }
}
</style>
