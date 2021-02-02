package com.tv.vo;

import com.tv.entity.FriendsAskDto;
import com.tv.entity.SysUserDto;
import lombok.Data;

@Data
public class FriendsAskVo extends FriendsAskDto {
    private SysUserDto targetInfo;
}
