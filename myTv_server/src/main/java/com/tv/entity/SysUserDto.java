package com.tv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_users")
@Data
public class SysUserDto {
    @TableId(type = IdType.AUTO)
    private int Id;

    private String userName;

    private String nickName;
    private String passWord;
}
