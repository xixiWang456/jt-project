package com.jt.cart.service;

import java.util.List;

import com.jt.cart.pojo.Cart;

public interface CartService {

	List<Cart> findCartListByUserId(Long userId);

	void saveCart(Cart cart);

	void updateCartNum(Long userId, Long itemId, Integer num);

	void deleteCartItemByUserId(Long userId, Long itemId);

}
