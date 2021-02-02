package com.tv.entity;

import lombok.Data;

@Data
public class ResultDto<T> {
    private Integer code;
    private T data;
    private String msg;
}
