package com.tv.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tv.entity.SysUserDto;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class AuthUtils {
    @Resource
    private JedisPool jedisPool;
    /**
     * 生成token
     *
     * @param userName 账号
     * @return 返回加密后的账号用作token
     */
    public static String genToken(String userName) {
        Encryption encrypt = new Encryption();
        return encrypt.encrypt(userName);
    }

    /**
     * 设置token
     *
     * @param token    token
     * @param userInfo 用户信息
     */
    public static String setToken(String token, SysUserDto userInfo) {
        Map tokenVal = new HashMap();
        tokenVal.put("id", userInfo.getId());
        tokenVal.put("userName", userInfo.getUserName());
        String jsonTokenValue = JSON.toJSONString(tokenVal);
        return jsonTokenValue;
    }

    /**
     * 根据token获取用户信息
     *
     * @param token    token
     */
    public static Map getTokenValue(String token) {
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.select(0);
        String tokenValue = jedis.get(token);
        String id = JSONObject.parseObject(tokenValue).getString("id");
        String userName = JSONObject.parseObject(tokenValue).getString("userName");
        Map data = new HashMap();
        data.put("id", id);
        data.put("userName", userName);
        return data;
    }
}
