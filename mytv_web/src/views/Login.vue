<template>
  <div class="login">
    <Fire />
    <div class="btn">
      <el-button size="small" type="primary" @click="setWindowSize">登陆</el-button>
      <el-button size="small" type="primary" @click="toRigster">注册</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { setSize } from '../render'
import Fire from '../components/fire.vue'

export default defineComponent({
  name: 'Login',
  components: {
    Fire
  },
  setup(props, ctx) {
    const router = useRouter()
    const state = reactive({ a: 'aaaa' })
    const state1 = reactive({ a: 'aaaa' })
    console.log(props)
    console.log(ctx)
    console.log(state)
    console.log(state1)
    // 设置窗口大小
    const setWindowSize = async () => {
      setSize(1000, 500)
    }
    // 跳转到注册页
    const toRigster = async () => {
      router.push('/rigster')
    }

    return {
      setWindowSize,
      toRigster
    }
  }
})
</script>
<style lang="less" scoped>
.login {
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
    }
  }
}
</style>
