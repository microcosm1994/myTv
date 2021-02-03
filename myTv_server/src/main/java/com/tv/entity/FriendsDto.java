package com.tv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("friends")
public class FriendsDto {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sId;
    private Integer tId;
    private Integer relation;
    private Integer del;
}
