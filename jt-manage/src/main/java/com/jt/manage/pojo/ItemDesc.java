package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_item_desc")
public class ItemDesc extends BasePojo{

	@Id
	private Long itemId;//商品ID 一对一的关系
	private String itemDesc;//商品的描述信息
	
	
	
	public Long getItemId() {
		return itemId;
	}



	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}



	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}



	@Override
	public String toString() {
		return "ItemDesc [itemId=" + itemId + ", text=" + itemDesc + "]";
	}
	
	
	
}
