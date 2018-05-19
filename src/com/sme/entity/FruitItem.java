package com.sme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * item(FruitItem)模型对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FruitItem extends BaseDTO {
	//======================字段列表========================
	/** id */
	private Integer	id;

	/** 创建时间 */
	private Date	createTime;

	/** 更新时间 */
	private Date	updateTime;

	/** 操作人 */
	private Integer	adminId;

	/** 状态1上架2下架 */
	private Integer	itemStatus;

	/** 类型1商品 */
	private Integer	itemType;

	/** 标题 */
	private String	title;

	/** 描述 */
	private String	description;

	/** 删除0正常1删除 */
	private Integer	isDelete;

	/** 图片 */
	private String	itemPic;

	/** 内容 */
	private String	itemData;

	/** 价格 */
	private String	itemPrice;

	/** 规格类型1份2个 */
	private Integer	itemSaleType;

	/** 规格内容 */
	private String	itemSaleInfo;

	/** 库存 */
	private Integer	itemStock;

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

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getItemStatus() {
		return this.itemStatus;
	}

	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Integer getItemType() {
		return this.itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getItemPic() {
		return itemPic;
	}

	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}

	public String getItemData() {
		return this.itemData;
	}

	public void setItemData(String itemData) {
		this.itemData = itemData;
	}

	public String getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemSaleType() {
		return this.itemSaleType;
	}

	public void setItemSaleType(Integer itemSaleType) {
		this.itemSaleType = itemSaleType;
	}

	public String getItemSaleInfo() {
		return this.itemSaleInfo;
	}

	public void setItemSaleInfo(String itemSaleInfo) {
		this.itemSaleInfo = itemSaleInfo;
	}

	public Integer getItemStock() {
		return this.itemStock;
	}

	public void setItemStock(Integer itemStock) {
		this.itemStock = itemStock;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\tid(id):").append(id);
		buffer.append("\tcreateTime(创建时间):").append(createTime);
		buffer.append("\tupdateTime(更新时间):").append(updateTime);
		buffer.append("\tadminId(操作人):").append(adminId);
		buffer.append("\n");
		buffer.append("\titemStatus(状态1上架2下架):").append(itemStatus);
		buffer.append("\titemType(类型1商品):").append(itemType);
		buffer.append("\ttitle(标题):").append(title);
		buffer.append("\tdescription(描述):").append(description);
		buffer.append("\n");
		buffer.append("\tisDelete(删除0正常1删除):").append(isDelete);
		buffer.append("\titemPic(图片):").append(itemPic);
		buffer.append("\titemData(内容):").append(itemData);
		buffer.append("\titemPrice(价格):").append(itemPrice);
		buffer.append("\n");
		buffer.append("\titemSaleType(规格类型1份2个):").append(itemSaleType);
		buffer.append("\titemSaleInfo(规格内容):").append(itemSaleInfo);
		buffer.append("\titemStock(库存):").append(itemStock);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
