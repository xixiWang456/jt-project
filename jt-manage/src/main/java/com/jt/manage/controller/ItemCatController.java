package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.commom.vo.EasyUITree;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * (value="id",defaultValue="0",required=true)表示传递参数id，传递默认值0,是否传递参数
	 * @param parentId
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public String findItemCatByParentId(@RequestParam(value="id",defaultValue="0")Long parentId){
//		if(id==null)id=0l;
//		return itemCatService.findItemCatByParentId(parentId);
		return itemCatService.findItemCat(parentId);
		
	}
	
/*	@RequestMapping("list")
	@ResponseBody
	public List<EasyUITree> findItemCatByParentId(@RequestParam(value="id",defaultValue="0")Long parentId){
//		if(id==null)id=0l;
//		return itemCatService.findItemCatByParentId(parentId);
		return itemCatService.findEasyUITree(parentId);
		
	}
*/
}
