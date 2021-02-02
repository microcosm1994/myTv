package com.tv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("friends_ask")
public class FriendsAskDto {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sourceId;
    private Integer targetId;
    private Integer status;
}
