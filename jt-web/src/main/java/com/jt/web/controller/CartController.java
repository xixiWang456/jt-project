package com.jt.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	/**
	 * http://www.jt.com/cart/show.html
	 * 
	 */
	@RequestMapping("/show")
	public String findCartList(Model model){
		Long userId=UserThreadLocal.get().getId();
		List<Cart>cartList=cartService.findCartListByUserId(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	@RequestMapping("/add/{itemId}")
	public String saveCart(@PathVariable Long itemId,Cart cart){
		Long userId=UserThreadLocal.get().getId();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";
	}
	
	//update/num/"+_thisInput.attr("itemId")
	@RequestMapping("/update/num/{itemId}/{num}") 
	@ResponseBody
	public SysResult updataCart(@PathVariable Long itemId,@PathVariable Integer num,HttpServletRequest request){
		try {
			Long userId=UserThreadLocal.get().getId();
			cartService.updateCartNum(userId,itemId,num);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "购物车修改失败");
	}
	
	//http://www.jt.com/cart/delete/562379.html
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId,HttpServletRequest request){
		Long userId=UserThreadLocal.get().getId();
		cartService.deleteCartItem(userId,itemId);
		return "redirect:/cart/show.html";
	}

}
