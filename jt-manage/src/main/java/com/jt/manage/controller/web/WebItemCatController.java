package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.service.ItemCatService;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/web/")
public class WebItemCatController {

	@Autowired
	private ItemCatService  ItemCatService;
	
	/**
	 * 商品分类3级目录
	 * @param callback
	 * @return
	 * 前台以jsonp的形式进行调用，选哟将数据按照约定进行封装
	 * 
	 */
	@RequestMapping("itemcat/all")
	@ResponseBody
	public MappingJacksonValue findItemcatAll(String callback){
		
		ItemCatResult itemCatResult=ItemCatService.findItemCatCacheAll();
//		ItemCatResult itemCatResult=ItemCatService.findItemCatAll();
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(itemCatResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
}
