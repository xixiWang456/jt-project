package com.jt.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping(value="/{module}",method=RequestMethod.GET)
	private String index(@PathVariable String module){
		return module;
	}
	@RequestMapping(value="/doRegister",method=RequestMethod.POST)
	@ResponseBody
	public SysResult doRegister(User user){
		String username=userService.saveRegisterUser(user);
		if(username!=null)
			return SysResult.oK(username);
		else 
			return SysResult.build(404, "用户注册失败");
	}
	
	//用户登录操作 
	//http://www.jt.com/service/user/doLogin?r=0.667074150363973
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletRequest request,HttpServletResponse response){
		try {
			String ticket=userService.findUserByUp(user);
			if(!StringUtils.isEmpty(ticket)){
				CookieUtils.setCookie(request, response, "JT_TICKET", ticket);
				return SysResult.oK();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "登陆失败");
	}
	
	//用户退出操作
	/**
	 * http://www.jt.com/user/logout.html
	 * 1 获取cookie中的ticket
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		String ticket = CookieUtils.getCookieValue(request, "JT_TICKET");
		jedisCluster.del(ticket);
		CookieUtils.deleteCookie(request, response, "JT_TICKET");
		return "redirect:/index.html";
	}
	
}
