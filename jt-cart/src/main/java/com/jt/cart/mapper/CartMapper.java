package com.jt.cart.mapper;

import org.apache.ibatis.annotations.Param;

import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;

public interface CartMapper extends SysMapper<Cart>{

	Cart findCartByUIDAndItemId(@Param("userId")Long userId, @Param("itemId")Long itemId);

	void updateCartNum(Cart cart);

	void deleteCartItem(@Param("userId")Long userId, @Param("itemId")Long itemId);

}
