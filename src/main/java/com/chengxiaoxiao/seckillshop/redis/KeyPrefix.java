package com.chengxiaoxiao.seckillshop.redis;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();

}
