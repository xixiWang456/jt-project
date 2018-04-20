package com.jt.test.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRedis {

	@Test
	public void test01(){
		/*Jedis jedis=new Jedis("192.168.233.134",6379);
		jedis.set("1711", "京淘1711");
		System.out.println(jedis.get("1711"));*/
	}
	
	@Test
	public void test02(){
		//采用分片实现数据的缓存
		/*JedisPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMaxIdle(100);
		poolConfig.setTestOnBorrow(true);
		List<JedisShardInfo>shards=new ArrayList<>();
		shards.add(new JedisShardInfo("192.168.233.134", 6379));
		shards.add(new JedisShardInfo("192.168.233.134", 6380));
		shards.add(new JedisShardInfo("192.168.233.134", 6381));
		ShardedJedisPool pool=new ShardedJedisPool(poolConfig, shards);
		//获取jedis对象
		ShardedJedis shardedJedis = pool.getResource();
		shardedJedis.set("name", "tomcat");
		System.out.println(shardedJedis);
		//还池操作
		pool.returnResource(shardedJedis);*/
		
	}
	
	@Test
	public void test03(){
		/*Set<String>sentinels=new HashSet<>();
		sentinels.add(new HostAndPort("192.168.233.134",26379).toString());//192.168.233.134:26379
		sentinels.add(new HostAndPort("192.168.233.134",26380).toString());
		sentinels.add(new HostAndPort("192.168.233.134",26381).toString());
		JedisSentinelPool pool=new JedisSentinelPool("mymaster", sentinels);
		Jedis resource = pool.getResource();
		resource.set("name", "lalalalalala");
		System.out.println(resource.get("name"));
		pool.returnResource(resource);*/
		
	}
	@Test
	public void test04(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext-factory.xml");
		
		Object user = ac.getBean("user");
		System.out.println(user);
		
	}
}
