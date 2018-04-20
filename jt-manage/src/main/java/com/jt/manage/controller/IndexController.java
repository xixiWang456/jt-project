package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/page/{moduleName}")
	public String list(@PathVariable("moduleName")String moduleName){
		return moduleName;
	}
}
