import { ref } from 'vue';

/**
 * 验证码
 */
export default class UseVerifyImg {
  // 验证码图片dom
  private verifyImg: any = ref(null);

  // 获取验证码
  public getVerifyCode: Function = () => {
    const verifyCodeCount = ref(0);
    this.verifyImg.value.src =
      '/api/verify/getCode?c=' + verifyCodeCount.value++;
  };

  // 实例化
  static Instance() {
    const verifyImgInstance = new UseVerifyImg();

    return {
      verifyImg: verifyImgInstance.verifyImg,
      getVerifyCode: verifyImgInstance.getVerifyCode
    };
  }
}
