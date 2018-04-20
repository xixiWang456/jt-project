package com.jt.sso.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	/**
	 * 根据给定信息，经过分析，查询记录总数，如果返回查询结果不等于0，则表明数据库包含该记录
	 * 
	 */
	@Override
	public Boolean findCheckedUser(String column, String param) {
		int count=userMapper.findCheckedUser(column,param);
		return count!=0?true:false;
	}

	@Override
	public int saveRegisterUser(User user) {
		String password=DigestUtils.md5Hex(user.getPassword()+user.getUsername()+"www.jt.com");
		user.setPassword(password);
		user.setEmail(user.getPhone());
		int count=userMapper.saveUser(user);
		return count==0?0:1;
	}

	@Override
	public String findUserByUP(String username, String password) {
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			return null;
		}
		//校验用户名和密码是否正确
		password=DigestUtils.md5Hex(password+username+"www.jt.com");
		User user=userMapper.findUserByUP(username,password);
		if(user!=null){
			try {
				String ticket="JT_TICKET"+System.currentTimeMillis()+username;
				String md5Ticket=DigestUtils.md5Hex(ticket);//生成密钥
				String json= objectMapper.writeValueAsString(user);
				jedisCluster.set(md5Ticket, json);
				System.out.println("登陆成功"+md5Ticket);
				return md5Ticket;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override  
	public String findUserInfo(String ticket) {
		String json = jedisCluster.get(ticket);
		if(!StringUtils.isEmpty(json))
			return json;
		else 
			return null;
	}


}
