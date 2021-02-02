package com.tv.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tv.entity.FriendsAskDto;
import com.tv.vo.FriendsAskVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendsAskDao extends BaseMapper<FriendsAskDto> {
    List<FriendsAskVo> selectAskList(@Param("askData") FriendsAskDto askData);
}
