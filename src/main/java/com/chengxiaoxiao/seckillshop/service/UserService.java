package com.chengxiaoxiao.seckillshop.service;

import com.chengxiaoxiao.seckillshop.dao.UserDao;
import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;

import com.chengxiaoxiao.seckillshop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
@Service
public class UserService
{
    @Autowired
    UserDao userDao;

    public MiaoshaUser getById(int id)
    {
        return userDao.getById(id);
    }


    public void insert(User user)
    {
        userDao.insert(user);
    }

}
