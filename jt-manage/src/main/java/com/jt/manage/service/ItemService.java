package com.jt.manage.service;


import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

public interface ItemService {

	EasyUIResult findItemAll(Integer page,Integer rows);
	String findItemCatById(Integer id);
	int testFindCount();
	void saveItem(Item item, String desc);
	void deleteItem(String[]key);
	void updateItem(Item item, String desc);
	void updateItemStatusById(Long[] ids,Integer status);
	ItemDesc queryItemDesc(Long itemId);
	Item findItemById(Long itemId);
}
