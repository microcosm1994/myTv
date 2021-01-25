<template>
  <div style="position: relative;width: 100%;height:100%;">
    <div class="rigster">
      <div class="title">
        登录
      </div>
      <div class="form">
        <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="formRef"
          label-width="85px"
          class="demo-ruleForm"
          size="mini"
        >
          <el-form-item label="用户名" prop="userName">
            <el-input v-model.number="ruleForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="passWord">
            <el-input
              type="password"
              v-model="ruleForm.passWord"
              autocomplete="off"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="btn">
        <el-button size="small" type="primary" @click="submitForm()"
          >登录</el-button
        >
        <el-button type="info" size="small" @click="resetForm()"
          >重置</el-button
        >
      </div>
      <div class="return">
        <router-link to="/">返回</router-link>
        &nbsp;
        <router-link to="/rigster">去注册</router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue';
import { useStore } from 'vuex';
import UseForm from '@/utils/form';
interface RuleForm {
  userName: string;
  passWord: string;
}
interface Rules {
  userName: Array<object>;
  passWord: Array<object>;
}
export default defineComponent({
  name: 'Login',
  setup() {
    const store = useStore();
    console.log(store);
    const ruleForm: RuleForm = reactive({
      userName: '',
      passWord: ''
    });
    // 实例化form表单
    const { formRef, validate, resetForm } = UseForm.Instance();
    const rules: Rules = reactive({
      userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
      passWord: [{ required: true, message: '请输入密码', trigger: 'blur' }]
    });

    // 提交表单
    const submitForm = () => {
      if (validate()) {
        console.log('1');
      }
    };

    return {
      ruleForm,
      rules,
      formRef,
      submitForm,
      resetForm
    };
  }
});
</script>
<style lang="less" scoped>
.rigster {
  width: 240px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translateX(-50%) translateY(-60%);
  .title {
    width: 100%;
    text-align: center;
    font-size: 20px;
    line-height: 70px;
  }
  .form {
    width: 240px;
  }
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
  .return {
    width: 100%;
    text-align: center;
    font-size: 12px;
    a {
      color: rgb(105, 138, 230);
      font-style: none;
      cursor: pointer;
    }
  }
}
</style>
