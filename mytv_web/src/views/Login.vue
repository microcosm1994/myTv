<template>
  <div class="login">
    <Fire />
    <div class="btn">
      <el-button size="small" type="primary" @click="setSize">游客登陆</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { setSize } from '../render'
import Fire from '../components/fire.vue'

export default defineComponent({
  name: 'Login',
  components: {
    Fire
  },
  methods: {
    setSize() {
      setSize(1000, 500)
    }
  }
})
</script>
<style lang="less" scoped>
.login{
    width: 100%;
    height: 100%;
    .btn{
        padding-top: 20px;
        text-align: center;
        .el-button--primary{
            background: #e95022;
            outline: none;
            border: none;
            &:hover{
                background: #da4c21;
            }
            &:active{
                background: #e95022;
            }
        }
    }
}
</style>

