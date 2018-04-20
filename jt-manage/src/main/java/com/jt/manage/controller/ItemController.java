package com.jt.manage.controller;



import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller//RestController=controller+responsebody  整个类方法都不会调用视图解析器
@RequestMapping("/item/")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	private static final Logger logger=Logger.getLogger(ItemController.class);
	
			
	
	@RequestMapping("query")
	@ResponseBody
	public EasyUIResult findItemAll(Integer page,Integer rows){
		return itemService.findItemAll(page,rows);
	}
	/**
	 * @ResponseBody转json串时默认按utf-8转换 ，除了string类型 
	 * string 类型按iso 8859-1
	 * 为什么？
	 * @param id
	 * @param response
	 * @throws Exception 
	 */
	/*@RequestMapping("cat/queryItemCatName")
	@ResponseBody
	public void findItemCatName(Integer id,HttpServletResponse response){
		String name=itemService.findItemCatById(id);
		try { 
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	@RequestMapping(value="cat/queryItemCatName",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findItemCatName(Integer id,HttpServletResponse response) throws Exception{
		String name=itemService.findItemCatById(id);
		return name; 
	}
	
	@RequestMapping("testFindCount")
	@ResponseBody
	public int testFindCount(){
		return itemService.testFindCount();
	}
	@RequestMapping("save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			logger.info("商品添加成品");
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "新增操作失败");
		}
	}
	@RequestMapping("delete")
	@ResponseBody
	public SysResult deleteItem(String ids){
		try {
			String[] id = ids.split(",");
			itemService.deleteItem(id);
			logger.info("商品删除成功");
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "删除操作失败");
		}
	}
	@RequestMapping("update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			logger.info("商品更新成功");
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "更新操作失败");
		}
	}
	//下架
	@RequestMapping("instock")
	@ResponseBody
	public SysResult instockItem(Long[]ids){
		try {
			itemService.updateItemStatusById(ids,2);
			logger.info("商品更新成功");
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "更新操作失败");
		}
	}
	//上架 
	@RequestMapping("reshelf")
	@ResponseBody
	public SysResult reshelfItem(Long[]ids){
		try {
			itemService.updateItemStatusById(ids,1);
			logger.info("商品更新成功");
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "更新操作失败");
		}
	}
	@RequestMapping("query/item/desc/{itemId}")
	@ResponseBody
	public SysResult queryItemDesc(@PathVariable("itemId")Long itemId){
		try {
			ItemDesc itemDesc = itemService.queryItemDesc(itemId);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "查询失败");
		}
	}
	
	
}
