package com.jt.manage.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 需要将list信息集合返回给页面，页面中通过jstl标签直接获取参数
	 * @return
	 */
	@RequestMapping("/findAll")
	public String fingAll(Model model){
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		
		return "userList";
	}
}
