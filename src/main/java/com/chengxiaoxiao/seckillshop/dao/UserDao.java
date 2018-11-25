package com.chengxiaoxiao.seckillshop.dao;


import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;

import com.chengxiaoxiao.seckillshop.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao
{

    @Select("select * from user where id = #{id}")
    public MiaoshaUser getById(@Param("id") int id);

    @Insert("insert into user(name) values(#{name})")
    public void insert(User user);


}
