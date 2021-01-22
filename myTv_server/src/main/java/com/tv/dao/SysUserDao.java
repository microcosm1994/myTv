package com.tv.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tv.entity.SysUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseMapper<SysUserDto> {
}
