package com.chengxiaoxiao.seckillshop.service;

import com.chengxiaoxiao.seckillshop.dao.UserDao;
import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;

import com.chengxiaoxiao.seckillshop.domain.User;
import com.chengxiaoxiao.seckillshop.redis.MiaoshaKey;
import com.chengxiaoxiao.seckillshop.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(int id) {
        //添加对象缓存
        MiaoshaUser miaoshaUser = redisService.get(MiaoshaKey.getById, "" + id, MiaoshaUser.class);
        if (miaoshaUser != null) {
            return miaoshaUser;
        }
        miaoshaUser = userDao.getById(id);
        if (miaoshaUser != null) {
            redisService.set(MiaoshaKey.getById, "" + id, miaoshaUser);
        }

        return miaoshaUser;
    }


    public void insert(User user) {
        userDao.insert(user);
    }

}
