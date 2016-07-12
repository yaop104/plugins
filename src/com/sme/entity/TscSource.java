package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.io.Serializable;

import java.util.Date;

/**
 * 素材表(TscSource)模型对象
 */
public class TscSource extends BaseObject implements Serializable {
	//======================字段列表========================
	/** unid */
	private Integer	tscUnid;

	/** 状态 */
	private String	tscState;

	/** 序号 */
	private Integer	tscOrder;

	/** 创建日期 */
	private Date	tscCdate;

	/** 最后修改日期 */
	private Date	tscUdate;

	/** 创建者 */
	private String	tscCuser;

	/** 最后修改者 */
	private String	tscUuser;

	/** 素材名 */
	private String	tscSourcename;

	/** 素材标题要求 */
	private String	tscTitle;

	/** 素材文案要求 */
	private String	tscDocunments;

	/** 素材图片要求 */
	private String	tscPircture;

	/** 投放位id */
	private Integer	tscPositionid;

	public Integer getTscUnid() {
		return this.tscUnid;
	}

	public void setTscUnid(Integer tscUnid) {
		this.tscUnid = tscUnid;
	}

	public String getTscState() {
		return this.tscState;
	}

	public void setTscState(String tscState) {
		this.tscState = tscState;
	}

	public Integer getTscOrder() {
		return this.tscOrder;
	}

	public void setTscOrder(Integer tscOrder) {
		this.tscOrder = tscOrder;
	}

	public Date getTscCdate() {
		return this.tscCdate;
	}

	public void setTscCdate(Date tscCdate) {
		this.tscCdate = tscCdate;
	}

	public Date getTscUdate() {
		return this.tscUdate;
	}

	public void setTscUdate(Date tscUdate) {
		this.tscUdate = tscUdate;
	}

	public String getTscCuser() {
		return this.tscCuser;
	}

	public void setTscCuser(String tscCuser) {
		this.tscCuser = tscCuser;
	}

	public String getTscUuser() {
		return this.tscUuser;
	}

	public void setTscUuser(String tscUuser) {
		this.tscUuser = tscUuser;
	}

	public String getTscSourcename() {
		return this.tscSourcename;
	}

	public void setTscSourcename(String tscSourcename) {
		this.tscSourcename = tscSourcename;
	}

	public String getTscTitle() {
		return this.tscTitle;
	}

	public void setTscTitle(String tscTitle) {
		this.tscTitle = tscTitle;
	}

	public String getTscDocunments() {
		return this.tscDocunments;
	}

	public void setTscDocunments(String tscDocunments) {
		this.tscDocunments = tscDocunments;
	}

	public String getTscPircture() {
		return this.tscPircture;
	}

	public void setTscPircture(String tscPircture) {
		this.tscPircture = tscPircture;
	}

	public Integer getTscPositionid() {
		return this.tscPositionid;
	}

	public void setTscPositionid(Integer tscPositionid) {
		this.tscPositionid = tscPositionid;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttscUnid(unid):").append(tscUnid);
		buffer.append("\ttscState(状态):").append(tscState);
		buffer.append("\ttscOrder(序号):").append(tscOrder);
		buffer.append("\ttscCdate(创建日期):").append(tscCdate);
		buffer.append("\n");
		buffer.append("\ttscUdate(最后修改日期):").append(tscUdate);
		buffer.append("\ttscCuser(创建者):").append(tscCuser);
		buffer.append("\ttscUuser(最后修改者):").append(tscUuser);
		buffer.append("\ttscSourcename(素材名):").append(tscSourcename);
		buffer.append("\n");
		buffer.append("\ttscTitle(素材标题要求):").append(tscTitle);
		buffer.append("\ttscDocunments(素材文案要求):").append(tscDocunments);
		buffer.append("\ttscPircture(素材图片要求):").append(tscPircture);
		buffer.append("\ttscPositionid(投放位id):").append(tscPositionid);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
