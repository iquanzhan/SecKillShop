package com.chengxiaoxiao.seckillshop.redis;

import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 根据key获取对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;

            String str = jedis.get(realKey);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 存储键值对
     * @param key
     * @param data
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T data) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = beanToString(data);

            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();

            if (seconds <= 0) {
                jedis.set(realKey, value);
            } else {
                jedis.setex(realKey, seconds, value);
            }

            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * bean to string，实体转换为JSON字符串
     * @param data
     * @param <T>
     * @return
     */
    private <T> String beanToString(T data) {

        if (data == null) {
            return null;
        }
        Class<?> clazz = data.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + data;
        } else if (clazz == String.class) {
            return (String) data;
        } else if (clazz == Long.class) {
            return "" + data;
        } else {
            return JSON.toJSONString(data);
        }

    }

    /**
     * string 字符串转换为指定的对象
     * @param str   JSON字符串
     * @param clazz 指定对象限定名
     * @param <T>
     * @return
     */
    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }

    }

    /**
     * 释放Jedis
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
