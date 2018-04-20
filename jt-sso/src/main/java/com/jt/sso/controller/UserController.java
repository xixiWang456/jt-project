package com.jt.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;
@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 单点登录用户校验
	 * http://sso.jt.com/user/check/sadsdf/1
	 * @param param
	 * @param type 
	 * @param callback jsonp 函数名
	 * @return
	 */
	@RequestMapping("check/{param}/{type}")
	@ResponseBody
	public MappingJacksonValue findCheckedUser(@PathVariable String param,@PathVariable Integer type,String callback){
		String column=null;
		switch (type) {
		case 1:
			column="username";
			break;
		case 2:
			column="phone";
			break;
		case 3:
			column="email";
			break;

		}
		Boolean flag=userService.findCheckedUser(column,param);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(SysResult.oK(flag));
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
	@RequestMapping("register")
	@ResponseBody
	public SysResult saveRegisterUser(User user,HttpServletRequest request){
		int count=userService.saveRegisterUser(user);
		return count==0?SysResult.build(500, "该用户已经存在"):SysResult.oK(user.getUsername());
	}
	
	@RequestMapping("login")
	@ResponseBody
	public SysResult findUserByUP(@RequestParam("U") String username,@RequestParam("P")String password){
		try {
			String ticket=userService.findUserByUP(username,password);
			if(!StringUtils.isEmpty(ticket)){
				return SysResult.oK(ticket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "该用户不存在");
	}
	
	//http://sso.jt.com/user/query/60f4bfb6b5387cd90f94a6ad01df66e9?callback=jsonp1523958174766&_=1523958174840 
	@RequestMapping("/query/{ticket}")
	@ResponseBody
	public MappingJacksonValue findUserInfo(@PathVariable String ticket,String callback){
		String userJson=userService.findUserInfo(ticket);
		MappingJacksonValue mappingJacksonValue=null;
		if(userJson!=null){
			mappingJacksonValue =new MappingJacksonValue(SysResult.oK(userJson));
		}else{
			mappingJacksonValue =new MappingJacksonValue(SysResult.build(201, "用户未登陆"));
		}
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
