package com.tv.controller;

import com.tv.entity.MsgsDto;
import com.tv.entity.ResultDto;
import com.tv.service.MsgsService;
import com.tv.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/msg")
public class MsgsController {
    @Resource
    private MsgsService msgsService;

    /**
     * 新建聊天
     */
    @PostMapping(value = "/create")
    public ResultDto createMsg(@RequestBody MsgsDto data) {
        System.out.print(data);
        int num = msgsService.createMsg(data);
        if (num > 0) {
            return Result.genSuccessResult();
        }
        return Result.genErrorResult();
    }
}
