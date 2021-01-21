package com.tv.utils;

import com.tv.entity.ResultDto;

public class Result {
    private static int SUCCESS_CODE = 1;
    private static int ERROR_CODE = 0;

    private static String SUCCESS_MSG = "成功";
    private static String ERROR_MSG = "失败";

    /**
     * 返回成功
     */
    public static ResultDto genSuccessResult() {
        ResultDto result = new ResultDto();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    /**
     * 返回成功
     */
    public static ResultDto genSuccessResult(String msg) {
        ResultDto result = new ResultDto();
        result.setCode(SUCCESS_CODE);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回成功
     */
    public static <T> ResultDto<T> genSuccessResult(T data) {
        ResultDto result = new ResultDto();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        result.setData(data);
        return result;
    }

    /**
     * 返回失败
     */
    public static ResultDto genErrorResult() {
        ResultDto result = new ResultDto();
        result.setCode(ERROR_CODE);
        result.setMsg(ERROR_MSG);
        return result;
    }

    /**
     * 返回失败
     */
    public static ResultDto genErrorResult(String msg) {
        ResultDto result = new ResultDto();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }


}
