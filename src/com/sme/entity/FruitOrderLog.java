package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.util.Date;

/**
 * order_log(FruitOrderLog)模型对象
 */
public class FruitOrderLog extends BaseObject {
	//======================字段列表========================
	/** id */
	private Integer	id;

	/** 创建时间 */
	private Date	createTime;

	/** 更新时间 */
	private Date	updateTime;

	/** 卖家id */
	private String	sellerId;

	/** 订单状态： 9000成功2 付款失败 */
	private String	payStatus;

	/** 订单价格 */
	private String	totalAmount;

	/** 买家id */
	private String	userId;

	/** 订单号 */
	private String	outTradeNo;

	/** 收件人地址 */
	private String	tradeNo;

	/** 结果码 */
	private String	code;

	/** 结果的描述 */
	private String	msg;

	/** 付款时间 */
	private String	userPayTime;

	/** 原始日志 */
	private String	orderData;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOutTradeNo() {
		return this.outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserPayTime() {
		return this.userPayTime;
	}

	public void setUserPayTime(String userPayTime) {
		this.userPayTime = userPayTime;
	}

	public String getOrderData() {
		return this.orderData;
	}

	public void setOrderData(String orderData) {
		this.orderData = orderData;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tid(id):").append(id);
		buffer.append("\tcreateTime(创建时间):").append(createTime);
		buffer.append("\tupdateTime(更新时间):").append(updateTime);
		buffer.append("\tsellerId(卖家id):").append(sellerId);
		buffer.append("\n");
		buffer.append("\tpayStatus(订单状态： 9000成功2 付款失败):").append(payStatus);
		buffer.append("\ttotalAmount(订单价格):").append(totalAmount);
		buffer.append("\tuserId(买家id):").append(userId);
		buffer.append("\toutTradeNo(订单号):").append(outTradeNo);
		buffer.append("\n");
		buffer.append("\ttradeNo(收件人地址):").append(tradeNo);
		buffer.append("\tcode(结果码):").append(code);
		buffer.append("\tmsg(结果的描述):").append(msg);
		buffer.append("\tuserPayTime(付款时间):").append(userPayTime);
		buffer.append("\n");
		buffer.append("\torderData(原始日志):").append(orderData);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
