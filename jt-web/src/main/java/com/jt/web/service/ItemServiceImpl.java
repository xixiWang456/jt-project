package com.jt.web.service;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private HttpClientService httpClientService;
	
	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Override
	public Item findItemById(Long itemId)  {
		String uri="http://manage.jt.com/web/item/findItemById/"+itemId;
		String charset="utf-8";
		try {
			String json = httpClientService.doGet(uri);
			if(StringUtils.isEmpty(json)||json.equals("null")){
				//表示返回的数据有误
				return null;
			}else{
				//如果程序执行到整行
				Item item = objectMapper.readValue(json, Item.class);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
