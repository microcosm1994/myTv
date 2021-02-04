package com.tv.service;

import com.tv.entity.SysUserDto;

import java.util.Map;

public interface SysUserServiceImpl {
    Map getUser(String userName, String token);
    SysUserDto getUser(String userName);
}
