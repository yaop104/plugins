package com.sme.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单素材管理(TosOrderSource)模型对象
 */
public class TosOrderSource implements Serializable {
	//======================字段列表========================
	/** id */
	private Integer	tosOdsourceUnid;

	/** 排序 */
	private Integer	tosOdsourceOrder;

	/** 状态 */
	private String	tosOdsourceState;

	/** 记录日期 */
	private Date	tosOdsourceCdate;

	/** 更新日期 */
	private Date	tosOdsourceUdate;

	/** 记录人 */
	private Integer	tosOdsourceCuser;

	/** 创建人 */
	private Integer	tosOdsourceUuser;

	/** 订单素材图片url */
	private String	tosOdsourceUrl;

	/** 订单素材标题 */
	private String	tosOdsourceTitle;

	/** 订单素材文案 */
	private String	tosOdsourceDocument;

	/** 订单id */
	private Integer	tosOdsourceOrderid;

	/** 投放位id */
	private Integer	tosOdsourcePositionid;

	public Integer getTosOdsourceUnid() {
		return this.tosOdsourceUnid;
	}

	public void setTosOdsourceUnid(Integer tosOdsourceUnid) {
		this.tosOdsourceUnid = tosOdsourceUnid;
	}

	public Integer getTosOdsourceOrder() {
		return this.tosOdsourceOrder;
	}

	public void setTosOdsourceOrder(Integer tosOdsourceOrder) {
		this.tosOdsourceOrder = tosOdsourceOrder;
	}

	public String getTosOdsourceState() {
		return this.tosOdsourceState;
	}

	public void setTosOdsourceState(String tosOdsourceState) {
		this.tosOdsourceState = tosOdsourceState;
	}

	public Date getTosOdsourceCdate() {
		return this.tosOdsourceCdate;
	}

	public void setTosOdsourceCdate(Date tosOdsourceCdate) {
		this.tosOdsourceCdate = tosOdsourceCdate;
	}

	public Date getTosOdsourceUdate() {
		return this.tosOdsourceUdate;
	}

	public void setTosOdsourceUdate(Date tosOdsourceUdate) {
		this.tosOdsourceUdate = tosOdsourceUdate;
	}

	public Integer getTosOdsourceCuser() {
		return this.tosOdsourceCuser;
	}

	public void setTosOdsourceCuser(Integer tosOdsourceCuser) {
		this.tosOdsourceCuser = tosOdsourceCuser;
	}

	public Integer getTosOdsourceUuser() {
		return this.tosOdsourceUuser;
	}

	public void setTosOdsourceUuser(Integer tosOdsourceUuser) {
		this.tosOdsourceUuser = tosOdsourceUuser;
	}

	public String getTosOdsourceUrl() {
		return this.tosOdsourceUrl;
	}

	public void setTosOdsourceUrl(String tosOdsourceUrl) {
		this.tosOdsourceUrl = tosOdsourceUrl;
	}

	public String getTosOdsourceTitle() {
		return this.tosOdsourceTitle;
	}

	public void setTosOdsourceTitle(String tosOdsourceTitle) {
		this.tosOdsourceTitle = tosOdsourceTitle;
	}

	public String getTosOdsourceDocument() {
		return this.tosOdsourceDocument;
	}

	public void setTosOdsourceDocument(String tosOdsourceDocument) {
		this.tosOdsourceDocument = tosOdsourceDocument;
	}

	public Integer getTosOdsourceOrderid() {
		return this.tosOdsourceOrderid;
	}

	public void setTosOdsourceOrderid(Integer tosOdsourceOrderid) {
		this.tosOdsourceOrderid = tosOdsourceOrderid;
	}

	public Integer getTosOdsourcePositionid() {
		return this.tosOdsourcePositionid;
	}

	public void setTosOdsourcePositionid(Integer tosOdsourcePositionid) {
		this.tosOdsourcePositionid = tosOdsourcePositionid;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttosOdsourceUnid(id):").append(tosOdsourceUnid);
		buffer.append("\ttosOdsourceOrder(排序):").append(tosOdsourceOrder);
		buffer.append("\ttosOdsourceState(状态):").append(tosOdsourceState);
		buffer.append("\ttosOdsourceCdate(记录日期):").append(tosOdsourceCdate);
		buffer.append("\n");
		buffer.append("\ttosOdsourceUdate(更新日期):").append(tosOdsourceUdate);
		buffer.append("\ttosOdsourceCuser(记录人):").append(tosOdsourceCuser);
		buffer.append("\ttosOdsourceUuser(创建人):").append(tosOdsourceUuser);
		buffer.append("\ttosOdsourceUrl(订单素材图片url):").append(tosOdsourceUrl);
		buffer.append("\n");
		buffer.append("\ttosOdsourceTitle(订单素材标题):").append(tosOdsourceTitle);
		buffer.append("\ttosOdsourceDocument(订单素材文案):").append(tosOdsourceDocument);
		buffer.append("\ttosOdsourceOrderid(订单id):").append(tosOdsourceOrderid);
		buffer.append("\ttosOdsourcePositionid(投放位id):").append(tosOdsourcePositionid);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
