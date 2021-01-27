import { ref } from 'vue';

/**
 * @name UseForm
 * @type {Class}
 * @description elementPlus form表单逻辑复用
 */
export default class UseForm {
  /**
   * form表单dom
   */
  private formRef: any = ref(null);

  /**
   * @name validate
   * @return {boolean}
   * @description 验证form表单
   */
  public validate: Function = () => {
    let validResult = true;
    this.formRef.value.validate((valid: boolean) => {
      validResult = valid;
    });
    return validResult;
  };

  /**
   * @name resetForm
   * @return {any}
   * @description 清空form表单
   */
  public resetForm: Function = () => {
    this.formRef.value.resetFields();
  };

  // 实例化
  static Instance() {
    const form: any = new UseForm();
    return {
      formRef: form.formRef,
      validate: form.validate,
      resetForm: form.resetForm
    };
  }
}
