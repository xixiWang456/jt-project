package com.jt.manage.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.pojo.User;

@Controller
@RequestMapping("/web/")
public class JSONPController {

	@RequestMapping("testJSONP")
	public void testJSON(String callback,HttpServletResponse response) throws Exception{
		User user =new User();
		user.setId(100);	
		String json;
		json = callback+"("+new ObjectMapper().writeValueAsString(user)+")";
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}
	
}
