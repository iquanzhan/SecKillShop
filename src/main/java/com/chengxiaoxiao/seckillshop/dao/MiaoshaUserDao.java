package com.chengxiaoxiao.seckillshop.dao;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MiaoshaUserDao {

    @Select("select * from miaosha_user where id=#{id}")
    public MiaoshaUser getUserById(@Param("id") long id);
}
