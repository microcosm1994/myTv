<template>
  <div style="position: relative;width: 100%;height:100%;">
    <div class="rigster">
      <div class="title">
        注册
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
          <el-form-item label="确认密码" prop="checkPass">
            <el-input
              type="password"
              v-model="ruleForm.checkPass"
              autocomplete="off"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="btn">
        <el-button size="small" type="primary" @click="submitForm()"
          >注册</el-button
        >
        <el-button type="info" size="small" @click="resetForm()"
          >重置</el-button
        >
      </div>
      <div class="return">
        <router-link to="/">返回</router-link>
        &nbsp;
        <router-link to="/login">去登陆</router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from 'vue';
import { useStore } from 'vuex';
import { register } from '@/http/api/user';
interface RuleForm {
  userName: string;
  passWord: string;
  checkPass: string;
}
interface Rules {
  userName: any;
  passWord: any;
  checkPass: any;
}
export default defineComponent({
  name: 'Login',
  setup() {
    const formRef: any = ref(null);
    const store = useStore();
    const ruleForm: RuleForm = reactive({
      userName: '',
      passWord: '',
      checkPass: ''
    });
    // 密码验证
    const validatePass = (rule: any, value: string, callback: Function) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (ruleForm.checkPass !== '') {
          formRef.value.validateField('checkPass');
        }
        callback();
      }
    };
    // 密码验证
    const validatePass2 = (rule: any, value: string, callback: Function) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== ruleForm.passWord) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    const rules: Rules = reactive({
      userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
      passWord: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { validator: validatePass, trigger: 'blur' }
      ],
      checkPass: [
        { required: true, message: '请再次输入密码', trigger: 'blur' },
        { validator: validatePass2, trigger: 'blur' }
      ]
    });

    // 提交表单
    const submitForm = () => {
      formRef.value.validate((valid: boolean) => {
        if (valid) {
          register(ruleForm).then((res) => {
            console.log(res);
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    };
    // 重置
    const resetForm = () => {
      formRef.value.resetFields();
      console.log(store.getters.token);
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
