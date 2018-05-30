package com.sme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * order(FruitOrder)模型对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FruitOrder extends BaseDTO {
	//======================字段列表========================
	/** id */
	private String	id;

	/** 创建时间 */
	private Date	createTime;

	/** 更新时间 */
	private Date	updateTime;

	/** 卖家id */
	private Integer	sellerId;

	/** 订单状态：-1 已删除 0 已取消 1 下单 2 付款确认中 3 已付款 4 已发货 5 付款失败 */
	private Integer	orderStatus;

	/** 订单价格 */
	private String	orderPrice;

	/** 实际支付 */
	private String	realpay;

	/** 买家id */
	private Integer	userId;

	/** 总数 */
	private Integer	quantity;

	/** 快照 */
	private String	snapshot;

	/** 收件人地址 */
	private String	receiverAddress;

	/** 收件人手机号码 */
	private String	receiverMobile;

	/** 收件人名称 */
	private String	receiverName;

	/** 删除标志位1正常 */
	private Integer	hidden;

	/** 买家订单备注 */
	private String	notes;

	/** 付款时间 */
	private Date	payTime;

	/** 发货时间 */
	private Date	sendTime;

	/** 冗余 */
	private String	description;

	private String orderCreateTime;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getRealpay() {
		return realpay;
	}

	public void setRealpay(String realpay) {
		this.realpay = realpay;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSnapshot() {
		return this.snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public String getReceiverAddress() {
		return this.receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverMobile() {
		return this.receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getHidden() {
		return this.hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tid(id):").append(id);
		buffer.append("\tcreateTime(创建时间):").append(createTime);
		buffer.append("\tupdateTime(更新时间):").append(updateTime);
		buffer.append("\tsellerId(卖家id):").append(sellerId);
		buffer.append("\n");
		buffer.append("\torderStatus(订单状态：-1 已删除 0 已取消 1 下单 2 付款确认中 3 已付款 4 已发货 5 付款失败):").append(orderStatus);
		buffer.append("\torderPrice(订单价格):").append(orderPrice);
		buffer.append("\trealpay(实际支付):").append(realpay);
		buffer.append("\tuserId(买家id):").append(userId);
		buffer.append("\n");
		buffer.append("\tquantity(总数):").append(quantity);
		buffer.append("\tsnapshot(快照):").append(snapshot);
		buffer.append("\treceiverAddress(收件人地址):").append(receiverAddress);
		buffer.append("\treceiverMobile(收件人手机号码):").append(receiverMobile);
		buffer.append("\n");
		buffer.append("\treceiverName(收件人名称):").append(receiverName);
		buffer.append("\thidden(删除标志位1删除):").append(hidden);
		buffer.append("\tnotes(买家订单备注):").append(notes);
		buffer.append("\tpayTime(付款时间):").append(payTime);
		buffer.append("\n");
		buffer.append("\tsendTime(发货时间):").append(sendTime);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
