package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.io.Serializable;

import java.util.Date;

/**
 * 标签管理(TagTag)模型对象
 */
public class TagTag extends BaseObject implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 3797559229496504228L;

	//======================字段列表========================
	/** id */
	private Integer	tagTagUnid;

	/** 排序 */
	private Integer	tagTagOrder;

	/** 状态 */
	private String	tagTagState;

	/** 记录日期 */
	private Date	tagTagCdate;

	/** 更新日期 */
	private Date	tagTagUdate;

	/** 记录人 */
	private Integer	tagTagCuser;

	/** 创建人 */
	private Integer	tagTagUuser;

	/** 类型1 */
	private String	tagTagType;

	/** 名字 */
	private String	tagTagName;

	/** 描述 */
	private String	tagTagDesc;

	public Integer getTagTagUnid() {
		return this.tagTagUnid;
	}

	public void setTagTagUnid(Integer tagTagUnid) {
		this.tagTagUnid = tagTagUnid;
	}

	public Integer getTagTagOrder() {
		return this.tagTagOrder;
	}

	public void setTagTagOrder(Integer tagTagOrder) {
		this.tagTagOrder = tagTagOrder;
	}

	public String getTagTagState() {
		return this.tagTagState;
	}

	public void setTagTagState(String tagTagState) {
		this.tagTagState = tagTagState;
	}

	public Date getTagTagCdate() {
		return this.tagTagCdate;
	}

	public void setTagTagCdate(Date tagTagCdate) {
		this.tagTagCdate = tagTagCdate;
	}

	public Date getTagTagUdate() {
		return this.tagTagUdate;
	}

	public void setTagTagUdate(Date tagTagUdate) {
		this.tagTagUdate = tagTagUdate;
	}

	public Integer getTagTagCuser() {
		return this.tagTagCuser;
	}

	public void setTagTagCuser(Integer tagTagCuser) {
		this.tagTagCuser = tagTagCuser;
	}

	public Integer getTagTagUuser() {
		return this.tagTagUuser;
	}

	public void setTagTagUuser(Integer tagTagUuser) {
		this.tagTagUuser = tagTagUuser;
	}

	public String getTagTagType() {
		return this.tagTagType;
	}

	public void setTagTagType(String tagTagType) {
		this.tagTagType = tagTagType;
	}

	public String getTagTagName() {
		return this.tagTagName;
	}

	public void setTagTagName(String tagTagName) {
		this.tagTagName = tagTagName;
	}

	public String getTagTagDesc() {
		return this.tagTagDesc;
	}

	public void setTagTagDesc(String tagTagDesc) {
		this.tagTagDesc = tagTagDesc;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttagTagUnid(id):").append(tagTagUnid);
		buffer.append("\ttagTagOrder(排序):").append(tagTagOrder);
		buffer.append("\ttagTagState(状态):").append(tagTagState);
		buffer.append("\ttagTagCdate(记录日期):").append(tagTagCdate);
		buffer.append("\n");
		buffer.append("\ttagTagUdate(更新日期):").append(tagTagUdate);
		buffer.append("\ttagTagCuser(记录人):").append(tagTagCuser);
		buffer.append("\ttagTagUuser(创建人):").append(tagTagUuser);
		buffer.append("\ttagTagType(类型1):").append(tagTagType);
		buffer.append("\n");
		buffer.append("\ttagTagName(名字):").append(tagTagName);
		buffer.append("\ttagTagDesc(描述):").append(tagTagDesc);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
