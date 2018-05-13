package com.sme.entity;

import com.sme.core.model.BaseObject;

import java.io.Serializable;

import java.util.Date;

/**
 * 开发商基础信息管理(TbcInfo)模型对象
 */
public class TbcInfo extends BaseObject implements Serializable {
	//======================字段列表========================
	/** unid */
	private Integer	tioUnid;

	/** 状态 */
	private String	tioState;

	/** 排序 */
	private Integer	tioOrder;

	/** 类型1开发商 */
	private String	tioType;

	/** 创建时间 */
	private Date	tioCdate;

	/** 创建人 */
	private String	tioCuser;

	/** 更新时间 */
	private Date	tioUdate;

	/** 更新人 */
	private String	tioUuser;

	/** 描述 */
	private String	tioDesc;

	/** 公司名 */
	private String	tioName;

	/** 省份 */
	private String	tioProvince;

	/** 市 */
	private String	tioCity;

	/** 区县 */
	private String	tioDistrict;

	/** 联系人 */
	private String	tioContactname;

	/** 联系方式 */
	private String	tioContactphone;

	public Integer getTioUnid() {
		return this.tioUnid;
	}

	public void setTioUnid(Integer tioUnid) {
		this.tioUnid = tioUnid;
	}

	public String getTioState() {
		return this.tioState;
	}

	public void setTioState(String tioState) {
		this.tioState = tioState;
	}

	public Integer getTioOrder() {
		return this.tioOrder;
	}

	public void setTioOrder(Integer tioOrder) {
		this.tioOrder = tioOrder;
	}

	public String getTioType() {
		return this.tioType;
	}

	public void setTioType(String tioType) {
		this.tioType = tioType;
	}

	public Date getTioCdate() {
		return this.tioCdate;
	}

	public void setTioCdate(Date tioCdate) {
		this.tioCdate = tioCdate;
	}

	public String getTioCuser() {
		return this.tioCuser;
	}

	public void setTioCuser(String tioCuser) {
		this.tioCuser = tioCuser;
	}

	public Date getTioUdate() {
		return this.tioUdate;
	}

	public void setTioUdate(Date tioUdate) {
		this.tioUdate = tioUdate;
	}

	public String getTioUuser() {
		return this.tioUuser;
	}

	public void setTioUuser(String tioUuser) {
		this.tioUuser = tioUuser;
	}

	public String getTioDesc() {
		return this.tioDesc;
	}

	public void setTioDesc(String tioDesc) {
		this.tioDesc = tioDesc;
	}

	public String getTioName() {
		return this.tioName;
	}

	public void setTioName(String tioName) {
		this.tioName = tioName;
	}

	public String getTioProvince() {
		return this.tioProvince;
	}

	public void setTioProvince(String tioProvince) {
		this.tioProvince = tioProvince;
	}

	public String getTioCity() {
		return this.tioCity;
	}

	public void setTioCity(String tioCity) {
		this.tioCity = tioCity;
	}

	public String getTioDistrict() {
		return this.tioDistrict;
	}

	public void setTioDistrict(String tioDistrict) {
		this.tioDistrict = tioDistrict;
	}

	public String getTioContactname() {
		return this.tioContactname;
	}

	public void setTioContactname(String tioContactname) {
		this.tioContactname = tioContactname;
	}

	public String getTioContactphone() {
		return this.tioContactphone;
	}

	public void setTioContactphone(String tioContactphone) {
		this.tioContactphone = tioContactphone;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		buffer.append("\n");
		buffer.append("\ttioUnid(unid):").append(tioUnid);
		buffer.append("\ttioState(状态):").append(tioState);
		buffer.append("\ttioOrder(排序):").append(tioOrder);
		buffer.append("\ttioType(类型1开发商):").append(tioType);
		buffer.append("\n");
		buffer.append("\ttioCdate(创建时间):").append(tioCdate);
		buffer.append("\ttioCuser(创建人):").append(tioCuser);
		buffer.append("\ttioUdate(更新时间):").append(tioUdate);
		buffer.append("\ttioUuser(更新人):").append(tioUuser);
		buffer.append("\n");
		buffer.append("\ttioDesc(描述):").append(tioDesc);
		buffer.append("\ttioName(公司名):").append(tioName);
		buffer.append("\ttioProvince(省份):").append(tioProvince);
		buffer.append("\ttioCity(市):").append(tioCity);
		buffer.append("\n");
		buffer.append("\ttioDistrict(区县):").append(tioDistrict);
		buffer.append("\ttioContactname(联系人):").append(tioContactname);
		buffer.append("\ttioContactphone(联系方式):").append(tioContactphone);
		return buffer.toString();
	}
	//================== begin ======================

	//================== end ======================
}
