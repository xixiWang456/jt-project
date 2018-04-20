package com.jt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.util.RedisCluster;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {

	/**
	 * 工具类会被很多
	 * @Autowired(required=false) required =false
	 * 表示在工具中调用该工具类的方法才进行该属性的自动注入
	 * 由于某些项目需要使用缓存，该对象没有实例化，则启动报错
	 */
//	@Autowired(required=false)
//	private StringRedisTemplate stringRedisTemplate;
	
//	@Autowired(required=false)
//	private ShardedJedisPool shardedJedisPool;
	
	@Autowired
	private JedisCluster jedisCluster;
	
/*	@Autowired(required=false)
	private JedisSentinelPool jedisSentinelPool;
	
	public void set(String key,String value){
		Jedis jedis = jedisSentinelPool.getResource();
		jedis.set(key, value);
		jedisSentinelPool.returnResourceObject(jedis);
	}
	
	public String get(String key){
		Jedis jedis = jedisSentinelPool.getResource();
		String value=jedis.get(key);
		jedisSentinelPool.returnResourceObject(jedis);
		return value;
	}
	*/
//	public void set(String key,String value){
//		Jedis resource = shardedJedisPool.getResource();
//		resource.set(key, value);
//		shardedJedisPool.returnResource(resource);
//	}
//	
//	public String get(String key){
//		Jedis resource = shardedJedisPool.getResource();
//		String value=resource.get(key);
//		shardedJedisPool.returnResource(resource);
//		return value;
//	}
	
	
	/*
	 public void set(String key,String value){
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		opsForValue.set(key, value);
	}
	public String get(String key){
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		return opsForValue.get(key);
	}*/
	
	@Autowired(required=false)
	private RedisCluster redisCluster;
	
	public String get(String key){
		try {
			return redisCluster.get(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void set(String key ,String value){
		try {
			redisCluster.set(key, value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@Autowired(required=false)
	private StringRedisTemplate redisTemplate;
	
	public void set(String key,String value){
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value);
		
	}
	
	public String get(String key){
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		String json = opsForValue.get(key);
		return json;
	}
	
	public void set(String key,String value,Long expireTime){
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value, expireTime);
	}*/
	
}
