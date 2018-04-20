package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		Cart cart=new Cart();
		cart.setUserId(userId);
		List<Cart> cartList = cartMapper.select(cart);
		return cartList;
	}

	@Override
	public void saveCart(Cart cart) {
		Cart cartDB=cartMapper.findCartByUIDAndItemId(cart.getUserId(),cart.getItemId());
		if(cartDB==null){
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
			System.out.println("新增购物车成功");
		}else{
			cartDB.setNum(cartDB.getNum()+cart.getNum());
			cartDB.setUpdated(new Date());
			cartMapper.updateByPrimaryKeySelective(cartDB);
			System.out.println("更新购物车成功");
		}  
	}

	@Override
	public void updateCartNum(Long userId, Long itemId, Integer num) {
		Cart cart=new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cart.setNum(num);
		cart.setUpdated(new Date());
		cartMapper.updateCartNum(cart);
//		cartMapper.updateByPrimaryKeySelective(cart);
	}

	@Override
	public void deleteCartItemByUserId(Long userId, Long itemId) {
		cartMapper.deleteCartItem(userId,itemId);
		
	}

}
