package com.jt.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.order.mapper.OrderItemMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.mapper.OrderShippingMapper;
import com.jt.order.pojo.Order;
import com.jt.order.pojo.OrderItem;
import com.jt.order.pojo.OrderShipping;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private OrderShippingMapper OrderShippingMapper;
	
	@Override
	public String saveOrder(Order order) {
		// 订单信息入库
		String orderId=order.getUserId()+""+System.currentTimeMillis();
		order.setOrderId(orderId);
		order.setStatus(1);
		order.setCreated(new Date());
		order.setUpdated(order.getCreated());
		orderMapper.insert(order);
		System.out.println("订单入库成功");
		//订单物流信息入库
		OrderShipping orderShipping=order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(order.getCreated());
		orderShipping.setUpdated(order.getCreated());
		OrderShippingMapper.insert(orderShipping);
		System.out.println("订单物流信息入库成功");
		//订单商品入库
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId);
			orderItem.setCreated(order.getCreated());
			orderItem.setUpdated(order.getCreated());
			orderItemMapper.insert(orderItem);
		}
		System.out.println("订单商品入库成功");
		return order.getOrderId() ;
	}

	@Override
	public Order findOrderById(String orderId) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		OrderShipping orderShipping = OrderShippingMapper.selectByPrimaryKey(orderId);
		OrderItem orderItem=new OrderItem();
		orderItem.setOrderId(orderId);
		List<OrderItem>orderItems =orderItemMapper.select(orderItem);
		order.setOrderShipping(orderShipping);
		order.setOrderItems(orderItems);
		return order;
	}

	
	
}
