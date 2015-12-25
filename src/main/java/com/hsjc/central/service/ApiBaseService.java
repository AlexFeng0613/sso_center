package com.hsjc.central.service;

import com.hsjc.central.base.FastJsonRedisSerializer;
import com.hsjc.central.constant.Constant;
import com.hsjc.central.util.DesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : zga
 * @date : 2015-12-03
 *
 * 基本Service类
 */
@Service
public class ApiBaseService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author : zga
     * @date : 2015-12-03
     * 获取缓存对象
     * @param id
     * @param clazz
     * @return
     */
    public Object fetchObject(String id,Class clazz) {
        Object obj = null;

        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(String.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(clazz));
        if (redisTemplate.hasKey(id)) {
            obj = redisTemplate.opsForValue().get(id);
        }

        return obj;
    }

    /**
     * @author : zga
     * @date : 2015-12-03
     * 插入Redis缓存
     * @param key
     * @param obj
     * @param clazz
     */
    protected void insertIntoRedis(String key, Object obj, Class clazz) {
        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(String.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(clazz));
        redisTemplate.opsForValue().set(key, obj, 0);
        redisTemplate.expire(key, Constant.REDIS_FETCH_TIME_OUT,TimeUnit.SECONDS);
    }

    /**
     * @author : zga
     * @date : 2015-12-03
     * 获取DesUtils类
     * @return
     * @throws Exception
     */
    public DesUtil getDesUtil(){
        DesUtil desUtil = null;
        try {
            desUtil = new DesUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desUtil;
    }
}
