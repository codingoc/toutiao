package com.river.toutiao.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public final class RedisUtil {
    
	@Autowired
	private static JedisPool jedisPool;

	private static Logger logger = Logger.getLogger(RedisUtil.class);
	
	/**
     * 获取资源
     * @return
     */
    public static Jedis getResource() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            logger.debug(jedis);
        } catch (Exception e) {
            logger.error("getResource:{}",e);
            if (jedis!=null)
            jedis.close();
            throw  e;
        }
        return jedis;
    }
}