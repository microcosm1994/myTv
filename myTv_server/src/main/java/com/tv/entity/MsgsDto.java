package com.tv.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("msgs")
public class MsgsDto {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private int type;
    private int sid;
    private int tid;
    private Timestamp createTime;
}
