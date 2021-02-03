package com.tv.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tv.entity.FriendsDto;
import com.tv.entity.SysUserDto;
import com.tv.vo.FriendsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FriendsDao extends BaseMapper<FriendsDto> {
    List<FriendsVo> getList(@Param("data") SysUserDto data);
}
