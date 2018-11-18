package com.chengxiaoxiao.seckillshop.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
@Service
public class RedisPoolFactory {

    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jsc = new JedisPoolConfig();
        jsc.setMaxIdle(redisConfig.getPoolMaxIdle());
        jsc.setMaxTotal(redisConfig.getPoolMaxTotal());
        jsc.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);

        JedisPool jp = new JedisPool(jsc, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout(), redisConfig.getPassword());
        return jp;
    }
}
