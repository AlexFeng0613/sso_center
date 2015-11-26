package com.hsjc.central.base;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by henry on 1/12/15.
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

	private final Class<T> javaType;

	public FastJsonRedisSerializer(Class<T> javaType) {
		this.javaType = javaType;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		return JSON.toJSONBytes(t);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		return JSON.parseObject(bytes, javaType);
	}
}
