package com.tv.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tv.dao.FriendsAskDao;
import com.tv.entity.FriendsAskDto;
import com.tv.entity.SysUserDto;
import com.tv.vo.FriendsAskVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FriendsAskService {
    @Resource
    FriendsAskDao friendsAskDao;

    // 添加新的好友请求
    public int addFriendsAsk(FriendsAskDto askData) {
        FriendsAskDto row = this.findFriendsAsk(askData);
        // 如果请求已存在
        if (row != null) {
            // 如果请求已同意
            if (row.getStatus() == 1) {
                // 修改状态为未同意
                row.setStatus(0);
                return friendsAskDao.updateById(row);
            } else {
                return 1;
            }
        } else {
            return friendsAskDao.insert(askData);
        }
    }

    // 查询好友请求
    public FriendsAskDto findFriendsAsk(FriendsAskDto askData) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("source_id", askData.getSourceId());
        wrapper.eq("target_id", askData.getTargetId());
        return friendsAskDao.selectOne(wrapper);
    }

    // 获取好友请求
    public List<FriendsAskVo> getFriendsAsk(FriendsAskDto askData) {
//        QueryWrapper<FriendsAskDto> wrapper = new QueryWrapper();
//        Map map = new HashMap();
//        map.put("source_id", askData.getSourceId());
//        map.put("target_id", askData.getTargetId());
//        wrapper.allEq(map, false);
        return friendsAskDao.selectAskList(askData);
    }

}
