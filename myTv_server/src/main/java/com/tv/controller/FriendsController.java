package com.tv.controller;

import com.tv.entity.FriendsDto;
import com.tv.entity.ResultDto;
import com.tv.entity.SysUserDto;
import com.tv.service.FriendsService;
import com.tv.utils.Result;
import com.tv.vo.FriendsVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/friends")
public class FriendsController {
    @Resource
    FriendsService friendsService;

    @GetMapping("/getList")
    public ResultDto getFriendslist(SysUserDto data) {
        List<FriendsVo> list = friendsService.getFriendsList(data);
        if (list.size() > 0) {
            return Result.genSuccessResult(list);
        }
        return Result.genErrorResult();
    }
}
