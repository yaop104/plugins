package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.io.Serializable;

import java.util.Date;

/**
 * 字典管理(TdcDictionary)模型对象
 */
public class TdcDictionary extends BaseObject implements Serializable {
	//======================字段列表========================
	/** id */
	private Integer	tdcDictionaryUnid;

	/** 排序 */
	private Integer	tdcDictionaryOrder;

	/** 状态 */
	private String	tdcDictionaryState;

	/** 记录日期 */
	private Date	tdcDictionaryCdate;

	/** 更新日期 */
	private Date	tdcDictionaryUdate;

	/** 记录人 */
	private Integer	tdcDictionaryCuser;

	/** 创建人 */
	private Integer	tdcDictionaryUuser;

	/** 类型1大类2小类 */
	private String	tdcDictionaryType;

	/** 名字 */
	private String	tdcDictionaryName;

	/** 父id */
	private Integer	tdcDictionaryParentid;

	/** 描述 */
	private String	tdcDictionaryDesc;

	private String accName;

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public Integer getTdcDictionaryUnid() {
		return this.tdcDictionaryUnid;
	}

	public void setTdcDictionaryUnid(Integer tdcDictionaryUnid) {
		this.tdcDictionaryUnid = tdcDictionaryUnid;
	}

	public Integer getTdcDictionaryOrder() {
		return this.tdcDictionaryOrder;
	}

	public void setTdcDictionaryOrder(Integer tdcDictionaryOrder) {
		this.tdcDictionaryOrder = tdcDictionaryOrder;
	}

	public String getTdcDictionaryState() {
		return this.tdcDictionaryState;
	}

	public void setTdcDictionaryState(String tdcDictionaryState) {
		this.tdcDictionaryState = tdcDictionaryState;
	}

	public Date getTdcDictionaryCdate() {
		return this.tdcDictionaryCdate;
	}

	public void setTdcDictionaryCdate(Date tdcDictionaryCdate) {
		this.tdcDictionaryCdate = tdcDictionaryCdate;
	}

	public Date getTdcDictionaryUdate() {
		return this.tdcDictionaryUdate;
	}

	public void setTdcDictionaryUdate(Date tdcDictionaryUdate) {
		this.tdcDictionaryUdate = tdcDictionaryUdate;
	}

	public Integer getTdcDictionaryCuser() {
		return this.tdcDictionaryCuser;
	}

	public void setTdcDictionaryCuser(Integer tdcDictionaryCuser) {
		this.tdcDictionaryCuser = tdcDictionaryCuser;
	}

	public Integer getTdcDictionaryUuser() {
		return this.tdcDictionaryUuser;
	}

	public void setTdcDictionaryUuser(Integer tdcDictionaryUuser) {
		this.tdcDictionaryUuser = tdcDictionaryUuser;
	}

	public String getTdcDictionaryType() {
		return this.tdcDictionaryType;
	}

	public void setTdcDictionaryType(String tdcDictionaryType) {
		this.tdcDictionaryType = tdcDictionaryType;
	}

	public String getTdcDictionaryName() {
		return this.tdcDictionaryName;
	}

	public void setTdcDictionaryName(String tdcDictionaryName) {
		this.tdcDictionaryName = tdcDictionaryName;
	}

	public Integer getTdcDictionaryParentid() {
		return this.tdcDictionaryParentid;
	}

	public void setTdcDictionaryParentid(Integer tdcDictionaryParentid) {
		this.tdcDictionaryParentid = tdcDictionaryParentid;
	}

	public String getTdcDictionaryDesc() {
		return this.tdcDictionaryDesc;
	}

	public void setTdcDictionaryDesc(String tdcDictionaryDesc) {
		this.tdcDictionaryDesc = tdcDictionaryDesc;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttdcDictionaryUnid(id):").append(tdcDictionaryUnid);
		buffer.append("\ttdcDictionaryOrder(排序):").append(tdcDictionaryOrder);
		buffer.append("\ttdcDictionaryState(状态):").append(tdcDictionaryState);
		buffer.append("\ttdcDictionaryCdate(记录日期):").append(tdcDictionaryCdate);
		buffer.append("\n");
		buffer.append("\ttdcDictionaryUdate(更新日期):").append(tdcDictionaryUdate);
		buffer.append("\ttdcDictionaryCuser(记录人):").append(tdcDictionaryCuser);
		buffer.append("\ttdcDictionaryUuser(创建人):").append(tdcDictionaryUuser);
		buffer.append("\ttdcDictionaryType(类型1大类2小类):").append(tdcDictionaryType);
		buffer.append("\n");
		buffer.append("\ttdcDictionaryName(名字):").append(tdcDictionaryName);
		buffer.append("\ttdcDictionaryParentid(父id):").append(tdcDictionaryParentid);
		buffer.append("\ttdcDictionaryDesc(描述):").append(tdcDictionaryDesc);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
