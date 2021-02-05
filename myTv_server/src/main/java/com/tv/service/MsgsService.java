package com.tv.service;

import com.tv.dao.MsgsDao;
import com.tv.entity.MsgsDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MsgsService {
    @Resource
    private MsgsDao msgsDao;

    /**
     * 创建新的聊天
     * */
    public int createMsg(MsgsDto data){
        System.out.print(data);
        return msgsDao.insert(data);
    }
}
