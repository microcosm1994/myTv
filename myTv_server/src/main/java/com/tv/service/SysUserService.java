package com.tv.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tv.dao.SysUserDao;
import com.tv.entity.SysUserDto;
import com.tv.utils.AuthUtils;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class SysUserService {
    @Resource
    private SysUserDao sysUserDao;
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
    public SysUserDto getUser(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        SysUserDto user = sysUserDao.selectOne(queryWrapper);
        return user;
    }
}
