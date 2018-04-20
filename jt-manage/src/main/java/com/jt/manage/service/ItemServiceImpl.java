package com.jt.manage.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Override
	public EasyUIResult findItemAll(Integer page,Integer rows) {    
		
		int total=itemMapper.findItemCount();
		Integer start=(page-1)*rows;
		List<Item> list = itemMapper.findItemByPage(start,rows);
		EasyUIResult result = new EasyUIResult(total, list);
		
		return result;
	}

	@Override
	public String findItemCatById(Integer id) {
		return itemMapper.findItemCatName(id);
	}

	@Override
	public int testFindCount() {
		return itemMapper.findItemCount();
	}

	@Override
	public void saveItem(Item item,String desc) {
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		
		ItemDesc itemDesc = new ItemDesc();
		//通用mapper自动操作后会查询当前的id值并封装进item
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
		
	}

	@Override
	public void deleteItem(String[]key) {
		
		/*for(String id:key){
			Item item=new Item();
			item.setId(Long.parseLong(id));
			item.setStatus(3);
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}*/
		itemMapper.deleteByIDS(key);
		
	}

	@Override
	public void updateItem(Item item,String desc) {
		item.setStatus(1);
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		ItemDesc itemDesc=new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void updateItemStatusById(Long[]ids,Integer status) {
//		String [] idss=ids.split(",");
		/*for(String id:idss){
			Item item=new Item();
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}*/
		itemMapper.updateStatusByIds(ids, status);
	}

	@Override
	public ItemDesc queryItemDesc(Long itemId) {
		ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return itemDesc;
	}

	@Override
	public Item findItemById(Long itemId) {
		Item item=itemMapper.findItemById(itemId);
		return item;
	}
	
	

}
