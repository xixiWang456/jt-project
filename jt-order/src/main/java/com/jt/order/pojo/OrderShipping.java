package com.jt.order.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_order_shipping")
public class OrderShipping extends BasePojo{

	@Id
	private String orderId;				// 订单编号
	private String receiverName;		// 收件人姓名
	private String receiverPhone;		// 收件人固定电话
	private String receiverMobile;		// 收件人移动电话
	private String receiverState;		// 
	private String receiverCity;		// 收件城市
	private String receiverDistrict;	// 收件区域
	private String receiverAddress;		// 收件详细地址
	private String receiverZip;			// 邮政编码
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverState() {
		return receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverDistrict() {
		return receiverDistrict;
	}
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverZip() {
		return receiverZip;
	}
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}
	
	
	
	
	
}
