package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.CookieUtils;
import com.jt.web.pojo.User;

import redis.clients.jedis.JedisCluster;

public class TestInterceptor implements HandlerInterceptor{

	@Autowired
	private JedisCluster jedisCluster;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			String key=CookieUtils.getCookieValue(request, "JT_TICKET");
			String userData = jedisCluster.get(key);
			User user = objectMapper.readValue(userData, User.class); 
			request.setAttribute("userId", user.getId());
			System.out.println(request.getRequestURI()+"?userId="+user.getId());
//			String path=request.getRequestURI();
//			System.out.println(path.substring(0, path.lastIndexOf("."))+"?userId="+user.getId());
//			request.getRequestDispatcher(path.substring(0, path.lastIndexOf("."))+"?userId="+user.getId()).forward(request, response);
			return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("拦截post");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("拦截之后");
		
	}

}
