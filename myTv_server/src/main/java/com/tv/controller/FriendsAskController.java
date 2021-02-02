package com.tv.controller;

import com.tv.entity.FriendsAskDto;
import com.tv.entity.ResultDto;
import com.tv.service.FriendsAskService;
import com.tv.utils.Result;
import com.tv.vo.FriendsAskVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/friendsAsk")
public class FriendsAskController {
    @Resource
    FriendsAskService friendsAskService;

    // 添加新的好友请求
    @PostMapping(value = "/add")
    public ResultDto addFriendsAsk(@RequestBody FriendsAskDto askData) {
        int sourceId = askData.getSourceId();
        int targetId = askData.getTargetId();
        if (sourceId > 0 && targetId > 0 && sourceId != targetId) {
            int num = friendsAskService.addFriendsAsk(askData);
            if (num > 0) {
                return Result.genSuccessResult();
            }
        }
        return Result.genErrorResult();
    }

    // 获取好友请求
    @GetMapping(value = "/get")
    public ResultDto getFriendsAsk( FriendsAskDto askData){
        List<FriendsAskVo> row = friendsAskService.getFriendsAsk(askData);
        if (row.size() > 0) {
            return Result.genSuccessResult(row);
        }
        return Result.genErrorResult();
    }
}
