package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private HttpClientService httpClientService;

	private ObjectMapper objectMapper=new ObjectMapper();
			
	/**
	 * 1 定义uri
	 * 2 封装数据
	 * 3 发送请求
	 * 4 处理返回值结果
	 */
	@Override
	public String saveRegisterUser(User user) {
		String uri="http://sso.jt.com/user/register";
		Map<String,String>params=new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
		params.put("email", user.getEmail());
		try {
			String jsonData = httpClientService.doPost(uri,params);
			SysResult sysResult=objectMapper.readValue(jsonData, SysResult.class);
			if(sysResult.getStatus()==200){
				return (String) sysResult.getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	/*
	 * 用户登陆模块
	 * @see com.jt.web.service.UserService#findUserByUp(com.jt.web.pojo.User)
	 * 1 定义uri
	 */
	@Override
	public String findUserByUp(User user) {
		String uri="http://sso.jt.com/user/login";
		Map<String,String>params=new HashMap<>();
		params.put("U", user.getUsername());
		params.put("P", user.getPassword());
		try {
			String jsonData = httpClientService.doPost(uri,params);
			//将json数据转换为sysResult
			SysResult sysResult=objectMapper.readValue(jsonData, SysResult.class);
			if(sysResult.getStatus()==200){
				return (String) sysResult.getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
