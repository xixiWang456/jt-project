package com.jt.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	private  static ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping("/create")
	@ResponseBody
	public SysResult saveOrder(String orderData){
		try {
			Order order = objectMapper.readValue(orderData, Order.class);
			String orderId=orderService.saveOrder(order);
			return SysResult.oK(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "提交订单失败");
	}
	@RequestMapping("/query/{orderId}")
	@ResponseBody
	public Order findOrderById(@PathVariable String orderId){
		Order order =orderService.findOrderById(orderId);
		return order;
	}
	
}
