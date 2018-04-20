package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Order;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private HttpClientService httpClientService;

	private static ObjectMapper objectMapper=new ObjectMapper();
	
	@Override
	public String saveOrder(Order order) {
		String uri="http://order.jt.com/order/create";
		try {
			String orderData = objectMapper.writeValueAsString(order);
			Map<String,String>params=new HashMap<>();
			params.put("orderData", orderData);
			String json = httpClientService.doPost(uri, params);
			SysResult sysResult=objectMapper.readValue(json, SysResult.class);
			if(sysResult.getStatus()==200){
				return (String) sysResult.getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public Order findOrderById(String id) {
		String uri="http://order.jt.com/order/query/"+id;
		try {
			String json = httpClientService.doGet(uri);
			Order order = objectMapper.readValue(json, Order.class);
			if(order!=null)
				return order;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
