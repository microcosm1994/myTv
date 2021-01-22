package com.tv.controller;

import com.tv.entity.ResultDto;
import com.tv.entity.SysUserDto;
import com.google.code.kaptcha.Constants;
import com.tv.service.SysUserService;
import com.tv.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@ResponseBody
public class SysUserController {
    @Resource
    HttpServletRequest request;
    @Resource
    private HttpSession session;
    @Resource
    private SysUserService sysUserService;

    @PostMapping(value = "/login")
    public ResultDto login(@RequestBody Map<String, String> loginInfo) {
        // 获取session中的验证码
        Object kaptchaSessionCode = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 比对验证码
        if (loginInfo.get("code").equals(kaptchaSessionCode)) {
            // 登录
            String token = sysUserService.login(loginInfo.get("userName"), loginInfo.get("passWord"));
            if (token.isEmpty()) {
                return Result.genErrorResult("用户名或密码错误");
            } else {
                Map data = new HashMap();
                data.put("token", token);
                return Result.genSuccessResult(data);
            }
        } else {
            // 验证码错误
            return Result.genErrorResult("验证码错误");
        }
    }

    @PostMapping(value = "/register")
    public ResultDto register(@RequestBody SysUserDto userInfo){
        System.out.print(userInfo);
        int num = sysUserService.register(userInfo);
        if (num > 0) {
            return Result.genSuccessResult();
        } else{
            return Result.genErrorResult();
        }
    }
}
