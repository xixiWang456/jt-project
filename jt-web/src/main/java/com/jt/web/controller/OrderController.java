package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;
import com.jt.web.service.CartService;
import com.jt.web.service.OrderService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/create")
	public String CreateOrder(Model model){
		Long userId=UserThreadLocal.get().getId();
		List<Cart> cartList = cartService.findCartListByUserId(userId);
		model.addAttribute("carts", cartList);
		return "order-cart";
	}
	
	//submit
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order){
		try {
			Long userId = UserThreadLocal.get().getId();
			order.setUserId(userId);
			String orderId=orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)){
				return SysResult.oK(orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "订单提交失败");
	}
	
	
	//success
	@RequestMapping("/success")
	public String success(String id,Model model){
		Order order=orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "success";
	}
	@RequestMapping("/myOrder")
	public String myOrder(){
		return "my-orders";
	}
}
