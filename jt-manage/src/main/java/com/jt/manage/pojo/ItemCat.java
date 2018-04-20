package com.jt.manage.pojo;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;
/**
 * 商品分类表
 * @author tarena
 *
 */
@Table(name="tb_item_cat")
public class ItemCat extends BasePojo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;    	   //自增主键             
	private Long parentId;    //父级分类id,父ID=0时，代表一级类目
	private String name;       //分类名称 
	private Integer status;    //状态码   ,默认值为1，可选值：1正常，2删除',
	private Integer sortOrder; //排序号 
	private Boolean isParent; //是否为父级 
	
	/**
	 * 如果是父级菜单，则默认关闭，反之则打开
	 * @return
	 */
/*	public String getState(){
		return isParent?"closed":"open";
	}
	
	public String getText(){
		return name;
	}
	*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	@Override
	public String toString() {
		return "ItemCat [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status + ", sortOrder="
				+ sortOrder + ", isParent=" + isParent + "]";
	}
	
	
	
}
