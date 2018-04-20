package com.jt.manage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.commom.vo.EasyUITree;
import com.jt.common.service.RedisService;
import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
//	@Autowired
//	private Jedis jesid;

	@Autowired
	private RedisService redisServer;
	
	@Autowired
	private JedisCluster  jedisCluster;
	
	/**
	 * 首先查询全部的一级目录 parentID=0
	 * 二级商品分类目录
	 * 三级商品分类目录
	 * 根据循环遍历的方式频繁操作数据库性能太低
	 * 缓存
	 */
	private ObjectMapper objectMapper=new ObjectMapper();
	@Override
	public ItemCatResult findItemCatAll() {
		
		ItemCat itemCattemp=new ItemCat();
		itemCattemp.setStatus(1);
		List<ItemCat> itemcatList=itemCatMapper.select(itemCattemp);
		
		Map<Long,List<ItemCat>> map=new HashMap<>();
		for (ItemCat itemCat : itemcatList) {
			if(map.containsKey(itemCat.getParentId())){
				map.get(itemCat.getParentId()).add(itemCat);
			}else{
				List<ItemCat>itemList=new ArrayList<>();
				itemList.add(itemCat);
				map.put(itemCat.getParentId(), itemList);
			}
		}
		
		ItemCatResult itemCatResult=new ItemCatResult();
		List<ItemCatData> itemCats1=new ArrayList<>();
		//获取一级对象
		for (ItemCat itemCat1: map.get(0L)) {
			//创建每个itemcatdata
			ItemCatData itemCatData=new ItemCatData();
			itemCatData.setUrl("/products/"+itemCat1.getId()+".html");
			itemCatData.setName("<a href='"+itemCatData.getUrl()+"'>"+itemCat1.getName()+"</a>");
			List<ItemCatData> itemCats2=new ArrayList<>();
			//获取二级目录
			for (ItemCat itemCat2 : map.get(itemCat1.getId())) {
				ItemCatData itemCatData2=new ItemCatData();
				itemCatData2.setUrl("/products/"+itemCat2.getId());
				itemCatData2.setName(itemCat2.getName());
				List<String> itemCats3=new ArrayList<>();
				//获取三级目录
				for (ItemCat itemCat3 : map.get(itemCat2.getId())) {
					String item3="/product/"+itemCat3.getId()+"|"+itemCat3.getName()+"";
					itemCats3.add(item3);
				}
				itemCatData2.setItems(itemCats3);
				itemCats2.add(itemCatData2);
			}
			itemCatData.setItems(itemCats2);
			if(itemCats1.size()>13)break;
			itemCats1.add(itemCatData);
		}
		itemCatResult.setItemCats(itemCats1);
		
		return itemCatResult;
	}
	
	@Override
	public ItemCatResult findItemCatCacheAll(){
		String key="ITEMCATALL";
		String jsonData = jedisCluster.get(key);
		
			try {
				if(StringUtils.isEmpty(jsonData)||"null".equals(jsonData)){
					ItemCatResult itemCatResult = findItemCatAll();
					String json = objectMapper.writeValueAsString(itemCatResult);
					jedisCluster.set(key,json);
					return itemCatResult;
				}else{
						ItemCatResult itemCatResult = objectMapper.readValue(jsonData, ItemCatResult.class);
						return itemCatResult;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
		
	}
	
	/**
	 * 采用jpa思想后，对象中不为null的属性将会作为where条件
	 */
	@Override
	public List<ItemCat> findItemCatByParentId(Long parentId) {
		ItemCat record=new ItemCat();
		record.setParentId(parentId);
//		record.setStatus(1);
		return itemCatMapper.select(record);
	}
	
	
	public List<EasyUITree> findEasyUITree(Long parentId){
		List<EasyUITree> treeList=new ArrayList<>();
		List<ItemCat> items=findItemCatByParentId(parentId);
		for(ItemCat itemCat:items){
			EasyUITree easyUITree=new EasyUITree();
			easyUITree.setId(itemCat.getId());
			easyUITree.setText(itemCat.getName());
			if(itemCat.getIsParent()){
				easyUITree.setState("closed");
			}else{
				easyUITree.setState("open");
			}
			treeList.add(easyUITree);
		}
		return treeList;
		
		
	}


	@Override
	public String findItemCat(Long parentId)  {
		String key=String.valueOf(parentId);
		String cats =redisServer.get(key);
		if(StringUtils.isEmpty(cats)){
			List<EasyUITree> list = findEasyUITree(parentId);
			try {
				redisServer.set(key,objectMapper.writeValueAsString(list));
				
				return redisServer.get(key);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return cats;
	}



}
