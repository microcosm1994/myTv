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
            <el-input v-model.number="ruleForm.userName" clearable></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="passWord">
            <el-input
              clearable
              type="password"
              v-model="ruleForm.passWord"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input
              clearable
              type="password"
              v-model="ruleForm.checkPass"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="code">
            <el-input
              clearable
              style="width: 50%;"
              v-model="ruleForm.code"
              autocomplete="off"
              maxlength="4"
            ></el-input>
            <div class="verifyCode" @click="getVerifyCode">
              <img ref="verifyImg" src="/api/verify/getCode" alt="" />
            </div>
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
import { defineComponent, getCurrentInstance, reactive } from 'vue';
// import { useStore } from 'vuex';
import cryptoMd5 from 'crypto-js/md5';
import UseForm from '@/utils/UseForm';
import UseVerifyImg from '@/utils/UseVerifyImg';
import { register } from '@/http/api/user';
interface RuleForm {
  userName: string;
  passWord: string;
  checkPass: string;
  code: string;
}
interface Rules {
  userName: Array<object>;
  passWord: Array<object>;
  checkPass: Array<object>;
  code: Array<object>;
}
export default defineComponent({
  name: 'Login',
  setup() {
    // 获取全局实例
    const { ctx }: any = getCurrentInstance();

    // const store = useStore();
    const ruleForm: RuleForm = reactive({
      userName: '',
      passWord: '',
      checkPass: '',
      code: ''
    });
    // 实例化form表单
    const { formRef, validate, resetForm } = UseForm.Instance();
    // 密码验证
    const validatePass = (rule, value: string, callback: Function) => {
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
    const validatePass2 = (rule, value: string, callback: Function) => {
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
      ],
      code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
    });
    // 提交表单
    const submitForm = () => {
      if (validate()) {
        register({
          userName: ruleForm.userName,
          passWord: cryptoMd5(encodeURI(ruleForm.passWord)).toString(),
          code: ruleForm.code
        }).then((res: any) => {
          if (res.code === 1) {
            console.log(res);
          }
          ctx.$notify({
            title: '注册结果',
            message: res.msg,
            position: 'bootom-right',
            duration: 0,
            type: res.code === 1 ? 'success' : 'error'
          });
        });
      }
    };

    /**
     * 验证码
     */
    const { getVerifyCode, verifyImg } = UseVerifyImg.Instance();

    return {
      ruleForm,
      rules,
      formRef,
      submitForm,
      resetForm,
      getVerifyCode,
      verifyImg
    };
  }
});
</script>
<style lang="less" scoped>
.rigster {
  width: 300px;
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
    width: 280px;
  }
  .verifyCode {
    width: 47%;
    height: 30px;
    display: inline-block;
    vertical-align: middle;
    cursor: pointer;
    margin-left: 2%;
    img {
      width: 100%;
      height: 100%;
    }
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
