package com.tv.vo;

import com.tv.entity.FriendsDto;
import com.tv.entity.SysUserDto;
import lombok.Data;

@Data
public class FriendsVo extends FriendsDto {
    private SysUserDto sourceInfo;
    private SysUserDto targetInfo;
}
