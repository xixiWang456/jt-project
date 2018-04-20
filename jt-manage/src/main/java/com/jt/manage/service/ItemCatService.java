package com.jt.manage.service;

import java.util.List;

import com.jt.commom.vo.EasyUITree;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.pojo.ItemCat;

public interface ItemCatService {

	List<ItemCat> findItemCatByParentId(Long parentId);
	List<EasyUITree> findEasyUITree(Long parentId);
	String findItemCat(Long parentId);
	ItemCatResult findItemCatAll();
	ItemCatResult findItemCatCacheAll();
}
