package com.jt.manage.pojo;

import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="item_param_item")
public class ItemParamItem extends BasePojo {

	@Id
	@GeneratedValue
	private Long id;
	private Long itemId;
	private Map<String,Object> paramData;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Map<String, Object> getParamData() {
		return paramData;
	}
	public void setParamData(Map<String, Object> paramData) {
		this.paramData = paramData;
	}
	@Override
	public String toString() {
		return "ItemParamItem [id=" + id + ", itemId=" + itemId + ", paramData=" + paramData + "]";
	}
	
	
}
