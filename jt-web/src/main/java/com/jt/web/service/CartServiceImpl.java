package com.jt.web.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private HttpClientService httpClientService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		String uri="http://cart.jt.com/cart/query/"+userId;
		try {
			String json = httpClientService.doGet(uri);
			SysResult sysResult=objectMapper.readValue(json, SysResult.class);
			if(sysResult.getStatus()==200){
				List<Cart> cartList=(List<Cart>) sysResult.getData();
				return cartList;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//购物车新增
	/**
	 * 1 定位uri
	 * 2 
	 */
	@Override
	public void saveCart(Cart cart) {
		String uri="http://cart.jt.com/cart/save";
		Map<String,String>params=new HashMap<String, String>();
		params.put("userId", cart.getUserId()+"");
		params.put("itemId", cart.getItemId()+"");
		params.put("itemTitle", cart.getItemTitle());
		params.put("itemImage", cart.getItemImage());
		params.put("itemPrice", cart.getItemPrice()+"");
		params.put("num", ""+cart.getNum());
		try {
			httpClientService.doPost(uri, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCartNum(Long userId, Long itemId, Integer num) {
		String uri="http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
		try {
			String json = httpClientService.doGet(uri);
			SysResult sysResult=objectMapper.readValue(json, SysResult.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCartItem(Long userId, Long itemId) {
		String uri="http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		try {
			httpClientService.doGet(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
