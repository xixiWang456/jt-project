package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;


public interface ItemMapper extends SysMapper<Item>{

	List<Item>findItemAll();
	
	Integer findItemCount();
	
	List<Item>findItemByPage(@Param("offset")Integer start,@Param("pageSize") Integer rows );
	
	String findItemCatName(@Param("id")Integer id);

	int deleteItemByIds(Long[] ids);
	
	int updateStatusByIds(@Param("ids")Long[]ids,@Param("status")Integer status);

	Item findItemById(@Param("itemId")Long itemId);
}
 