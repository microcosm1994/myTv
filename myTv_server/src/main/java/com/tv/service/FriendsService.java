package com.tv.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tv.dao.FriendsDao;
import com.tv.entity.FriendsDto;
import com.tv.entity.SysUserDto;
import com.tv.vo.FriendsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FriendsService {
    @Resource
    FriendsDao friendsDao;

    // 添加好友
    public int addFriends(FriendsDto data) {
        return friendsDao.insert(data);
    }

    // 获取好友列表
    public List<FriendsVo> getFriendsList(SysUserDto data) {
        System.out.print(data);
        return friendsDao.getList(data);
    }

    // 判断用户是不是好友
    public int isFriends(Map data) {
        QueryWrapper<FriendsDto> wrapper = new QueryWrapper<>();
        wrapper.eq("s_id", data.get("userId")).eq("t_id", data.get("friendsId")).or().eq("s_id", data.get("friendsId")).eq("t_id", data.get("userId"));
        return friendsDao.selectCount(wrapper);
    }
}
