package com.tv.controller;

import com.alibaba.fastjson.JSONObject;
import com.tv.entity.ResultDto;
import com.tv.entity.SysUserDto;
import com.google.code.kaptcha.Constants;
import com.tv.service.FriendsService;
import com.tv.service.impl.SysUserService;
import com.tv.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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

    @Resource
    private FriendsService friendsService;
    @Resource
    private JedisPool jedisPool;

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
    public ResultDto register(@RequestBody Map<String, String> userInfo) {
        // 获取session中的验证码
        Object kaptchaSessionCode = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 比对验证码
        if (userInfo.get("code").equals(kaptchaSessionCode)) {
            SysUserDto user = new SysUserDto();
            user.setUserName(userInfo.get("userName"));
            user.setNickName(userInfo.get("nickName"));
            user.setPassWord(userInfo.get("passWord"));
            int num = sysUserService.register(user);
            if (num > 0) {
                return Result.genSuccessResult();
            } else {
                return Result.genErrorResult();
            }
        } else {
            return Result.genErrorResult("验证码错误");
        }
    }

    @GetMapping(value = "/get")
    public ResultDto getUser() {
        String token = request.getHeader("token");
        Jedis jedis = jedisPool.getResource();
        jedis.select(0);
        String tokenValue = jedis.get(token);
        String userName = JSONObject.parseObject(tokenValue).getString("userName");
        // 获取用户数据
        SysUserDto user = sysUserService.getUser(userName);
        if (user != null) {
            Map data = new HashMap();
            data.put("id", user.getId());
            data.put("userName", user.getUserName());
            data.put("nickName", user.getNickName());
            data.put("avatar", user.getAvatar());
            return Result.genSuccessResult(data);
        } else {
            return Result.genErrorResult("验证码错误");
        }
    }

    @GetMapping(value = "/logout")
    public ResultDto logout() {
        String token = request.getHeader("token");
        if (!token.isEmpty()) {
            Jedis jedis = jedisPool.getResource();
            jedis.select(0);
            jedis.setex(token, 0, "");
        }
        return Result.genSuccessResult();
    }

    @PostMapping(value = "/search")
    public ResultDto searchUser(@RequestBody SysUserDto userData) {
        // 获取用户数据
        String token = request.getHeader("token");
        Map user = sysUserService.getUser(userData.getUserName(), token);
        if (user != null) {
            return Result.genSuccessResult(user);
        } else {
            return Result.genErrorResult("没有此用户");
        }
    }
}
