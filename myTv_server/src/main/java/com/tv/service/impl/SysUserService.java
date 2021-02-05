package com.tv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tv.dao.SysUserDao;
import com.tv.entity.SysUserDto;
import com.tv.service.FriendsService;
import com.tv.service.SysUserServiceImpl;
import com.tv.utils.AuthUtils;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserService implements SysUserServiceImpl {
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private FriendsService friendsService;
    @Resource
    private JedisPool jedisPool;

    /**
     * 登录
     */
    public String login(String userName, String passWord) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        SysUserDto user = this.getUser(userName);
        // 验证密码
        boolean flag = encoder.matches(passWord, user.getPassWord());
        if (flag) {
            String token = AuthUtils.genToken(userName);
            String jsonTokenValue = AuthUtils.setToken(token, user);
            Jedis jedis = jedisPool.getResource();
            jedis.select(0);
            jedis.setex(token, 3600 * 24 * 30, jsonTokenValue);
            return token;
        } else {
            return "";
        }
    }

    /**
     * 插入新用户
     */
    public int register(SysUserDto userInfo) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode(userInfo.getPassWord());
        userInfo.setPassWord(pwd);
        int num = 0;
        try {
            num = sysUserDao.insert(userInfo);
        } catch (Exception err) {
            num = 0;
        } finally {
            return num;
        }
    }

    /**
     * 获取用户信息
     */
    @Override
    public Map getUser(String userName, String token) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        SysUserDto userInfo = sysUserDao.selectOne(queryWrapper);
        // 获取token value
        Map data = new HashMap();
        Map tokenValue = AuthUtils.getTokenValue(token);
        data.put("userId", tokenValue.get("id"));
        data.put("friendsId", userInfo.getId());
        // 查询是否好友关系
        int num = friendsService.isFriends(data);
        Map user = new HashMap();
        user.put("id", userInfo.getId());
        user.put("userName", userInfo.getUserName());
        user.put("nickName", userInfo.getNickName());
        user.put("avatar", userInfo.getAvatar());
        user.put("isFriends", num);
        return user;
    }
    /**
     * 获取用户信息
     */
    @Override
    public SysUserDto getUser(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        SysUserDto userInfo = sysUserDao.selectOne(queryWrapper);
        return userInfo;
    }
}
