<template>
  <div class="login">
    <!-- <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="密码" prop="pass">
        <el-input
          type="password"
          v-model="ruleForm.pass"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input
          type="password"
          v-model="ruleForm.checkPass"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model.number="ruleForm.age"></el-input>
      </el-form-item>
    </el-form>
    <div class="btn">
      <el-button size="small" type="primary" @click="submitForm('ruleForm')"
        >注册账号</el-button
      >
      <el-button @click="resetForm('ruleForm')">重置</el-button> -->
    <!-- </div> -->
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Login',
  setup(props, content){
      console.log(props)
      console.log(content)
  },
  data() {
    // var validatePass = (rule: any, value: String, callback: Function) => {
    //   if (value === '') {
    //     callback(new Error('请输入密码'))
    //   } else {
    //     if (this.ruleForm.checkPass !== '') {
    //       this.$refs.ruleForm.validateField('checkPass')
    //     }
    //     callback()
    //   }
    // }
    // var validatePass2 = (rule: any, value: String, callback: Function) => {
    //   if (value === '') {
    //     callback(new Error('请再次输入密码'))
    //   } else if (value !== this.ruleForm.pass) {
    //     callback(new Error('两次输入密码不一致!'))
    //   } else {
    //     callback()
    //   }
    // }
    // return {
    //   ruleForm: {
    //     pass: '',
    //     checkPass: '',
    //     age: ''
    //   },
    //   rules: {
    //     pass: [{ validator: validatePass, trigger: 'blur' }],
    //     checkPass: [{ validator: validatePass2, trigger: 'blur' }]
    //   }
    // }
  },
  methods: {
    // // 提交表单
    // submitForm(formName: String) {
    //   this.$refs[formName].validate((valid: boolean) => {
    //     if (valid) {
    //       alert('submit!')
    //     } else {
    //       console.log('error submit!!')
    //       return false
    //     }
    //   })
    // },
    // // 重置
    // resetForm(formName: String) {
    //   this.$refs[formName].resetFields()
    // }
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
