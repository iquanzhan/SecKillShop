package com.chengxiaoxiao.seckillshop.service;

import com.chengxiaoxiao.seckillshop.dao.MiaoshaUserDao;
import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getUserById(long id) {
        return miaoshaUserDao.getUserById(id);
    }

}
