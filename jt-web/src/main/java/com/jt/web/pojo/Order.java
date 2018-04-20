package com.jt.web.pojo;

import java.util.Date;
import java.util.List;


import com.jt.common.po.BasePojo;
public class Order extends BasePojo{

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public OrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	private String orderId;				// 订单id ：登录用户id+当前时间戳
	private String payment;				// 实付金额 
	private Integer paymentType;		// 支付类型 1 在线支付  2 货到付款
	private String postFee;				// 邮费
	private Integer status;				// 状态：1、未付款2、已付款3、未发货4、已发货5、交易成功6、交易关闭
	private Date paymentTime;			// 付款时间
	private Date consignTime;			// 发货时间
	private Date endTime;				// 交易完成时间
	private Date closeTime;				// 交易关闭事件
	private String shippingName;		// 物流名称
	private String shippingCode;		// 物流单号
	private Long userId;				// 买家id
	private String buyerMessage;		// 买家留言
	private String buyerNick;			// 买家昵称
	private Integer buyerRate;			// 买家是否留言
	private List<OrderItem> orderItems ;// 订单商品信息 ：一对多
	private OrderShipping orderShipping;// 订单物流信息  ：一对一
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public String getPostFee() {
		return postFee;
	}
	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Date getConsignTime() {
		return consignTime;
	}
	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingCode() {
		return shippingCode;
	}
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public Integer getBuyerRate() {
		return buyerRate;
	}
	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}
	
	
}
