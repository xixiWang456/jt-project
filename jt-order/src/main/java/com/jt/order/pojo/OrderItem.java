package com.jt.order.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_order_item")
public class OrderItem extends BasePojo{

	   @Id
	   private String itemId;			// 商品id
	   @Id
	   private String orderId;			// 订单id
	   private Integer num;				// 订单是商品数量
	   private String title;			// 商品标题
	   private Long price;				// 商品单价
	   private String totalFee;			// 商品总金额
	   private String picPath;			// 商品图片
	   
	   
	   
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	   
	   
}
